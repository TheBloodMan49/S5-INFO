(* ----------------------------- opal.ml START ------------------------------ *)
module LazyStream = struct
  type 'a t = Cons of 'a * 'a t Lazy.t | Nil
  let of_stream stream =
    let rec next stream =
      try Cons(Stream.next stream, lazy (next stream))
      with Stream.Failure -> Nil
    in
    next stream
  let of_string str = str |> Stream.of_string |> of_stream
  let of_channel ic = ic |> Stream.of_channel |> of_stream
  let of_function f =
    let rec next f =
      match f () with
      | Some x -> Cons(x, lazy (next f))
      | None -> Nil
    in
    next f
end
let implode l = String.concat "" (List.map (String.make 1) l)
let explode s =
  let l = ref [] in
  String.iter (fun c -> l := c :: !l) s;
  List.rev !l
let (%) f g = fun x -> g (f x)
type 'token input = 'token LazyStream.t
type ('token, 'result) parser = 'token input -> ('result * 'token input) option
let parse parser input =
  match parser input with
  | Some(res, _) -> Some res
  | None -> None
let return x input = Some(x, input)
let (>>=) x f =
  fun input ->
    match x input with
    | Some(result', input') -> f result' input'
    | None -> None
let (<|>) x y =
  fun input ->
    match x input with
    | Some _ as ret -> ret
    | None -> y input
let rec scan x input =
  match x input with
  | Some(result', input') -> LazyStream.Cons(result', lazy (scan x input'))
  | None -> LazyStream.Nil
let mzero _ = None
let any = function
  | LazyStream.Cons(token, input') -> Some(token, Lazy.force input')
  | LazyStream.Nil -> None
let satisfy test = any >>= (fun res -> if test res then return res else mzero)
let eof x = function LazyStream.Nil -> Some(x, LazyStream.Nil) | _ -> None
let (=>) x f = x >>= fun r -> return (f r)
let (>>) x y = x >>= fun _ -> y
let (<<) x y = x >>= fun r -> y >>= fun _ -> return r
let (<~>) x xs = x >>= fun r -> xs >>= fun rs -> return (r :: rs)
let rec choice = function [] -> mzero | h :: t -> (h <|> choice t)
let rec count n x = if n > 0 then x <~> count (n - 1) x else return []
let between op ed x = op >> x << ed
let option default x = x <|> return default
let optional x = option () (x >> return ())
let rec skip_many x = option () (x >>= fun _ -> skip_many x)
let skip_many1 x = x >> skip_many x
let rec many x = option [] (x >>= fun r -> many x >>= fun rs -> return (r :: rs))
let many1 x = x <~> many x
let sep_by1 x sep = x <~> many (sep >> x)
let sep_by x sep = sep_by1 x sep <|> return []
let end_by1 x sep = sep_by1 x sep << sep
let end_by x sep = end_by1 x sep <|> return []
let chainl1 x op =
  let rec loop a = (op >>= fun f -> x >>= fun b -> loop (f a b)) <|> return a in
  x >>= loop
let chainl x op default = chainl1 x op <|> return default
let rec chainr1 x op =
  x >>= fun a -> (op >>= fun f -> chainr1 x op >>= f a) <|> return a
let chainr x op default = chainr1 x op <|> return default
let rec unary op x =
  (op >>= fun f -> unary op x >>= fun a -> return (f a)) <|> x
let exactly x = satisfy ((=) x)
let one_of  l = satisfy (fun x -> List.mem x l)
let none_of l = satisfy (fun x -> not (List.mem l x))
let range l r = satisfy (fun x -> l <= x && x <= r)
let space     = one_of [' '; '\t'; '\r'; '\n']
let spaces    = skip_many space
let newline   = exactly '\n'
let tab       = exactly '\t'
let upper     = range 'A' 'Z'
let lower     = range 'a' 'z'
let digit     = range '0' '9'
let letter    = lower  <|> upper
let alpha_num = letter <|> digit
let hex_digit = range 'a' 'f' <|> range 'A' 'F'
let oct_digit = range '0' '7'
let lexeme x = spaces >> x
let token s =
  let rec loop s i =
    if i >= String.length s
    then return s
    else exactly s.[i] >> loop s (i + 1)
  in
  lexeme (loop s 0)
(* ------------------------------ opal.ml END ------------------------------- *)

type formula =
  | True
  | False
  | P of string
  | Not of formula
  | And of formula * formula
  | Or of formula * formula
  | Imp of formula * formula
  | Iff of formula * formula

type valuation = (string * bool) list

(* ------------------------------ parsing START ------------------------------- *)
let reserved = [
  "true";
  "false";
]

let ident = (spaces >> letter <~> many alpha_num) => implode >>= function
  | s when List.mem s reserved -> mzero
  | s -> return s

let parens = between (token "(") (token ")")
let not_op = token "~" >> return (fun p -> Not p)
let and_op = token "." >> return (fun p1 p2 -> And(p1, p2))
let or_op = token "+" >> return (fun p1 p2 -> Or(p1, p2))
let imp_op = token "=>" >> return (fun p1 p2 -> return (Imp(p1, p2)))
let iff_op = token "<=>" >> return (fun p1 p2 -> return (Iff(p1, p2)))
let atom = (ident => (fun s -> P s))
       <|> (token "true" >> return True)
       <|> (token "false" >> return False)

let rec formula input = (chainr1 imp_formula iff_op) input
and imp_formula input = (chainr1 or_formula imp_op) input
and or_formula input = (chainl1 and_formula or_op) input
and and_formula input = (chainl1 not_formula and_op) input
and not_formula input = (unary not_op primitive_formula) input
and primitive_formula input = (parens formula <|> atom) input
(* ------------------------------ parsing END ------------------------------- *)
(* ------------------------------ printing START ------------------------------- *)
let tab = 2

let false_str  = "\xe2\x8a\xa5"
let true_str   = "\xe2\x8a\xa4"
let not_str    = "\xc2\xac"
let and_str    = "\xe2\x88\xa7"
let or_str     = "\xe2\x88\xa8"
let imp_str    = "\xe2\x87\x92"
let iff_str    = "\xe2\x87\x94"

open Printf

let rec formula0 () = function
  | False -> false_str
  | True  -> true_str
  | P p -> p
  | p -> sprintf "(%a)" formula5 p

and formula1 () = function
  | Not p -> sprintf "%s%a" not_str formula1 p
  | p -> formula0 () p

and formula2 () = function
  | And (p, q) ->
    sprintf "%a %s %a"
      formula2 p
      and_str
      formula2 q
  | p -> formula1 () p

and formula3 () = function
  | Or (p, q)  ->
    sprintf "%a %s %a"
      formula3 p
      or_str
      formula3 q
  | p -> formula2 () p

and formula4 () = function
  | Imp (p, q) ->
    sprintf "%a %s %a"
      formula4 p
      imp_str
      formula4 q
  | p -> formula3 () p

and formula5 () = function
  | Iff (p, q) ->
    sprintf "%a %s %a"
      formula5 p
      iff_str
      formula5 q
  | p -> formula4 () p
(* ------------------------------ printing END ------------------------------- *)
(* ------------------------------ testing START ------------------------------- *)
let assert_equals expected tested = expected = tested

let test l =
  let ok = ref 0 in
  List.iter
    (fun (name, f) ->
      if f () then
        incr ok
      else
        Printf.printf "TEST [%s] FAILED\n" name
    )
    l;
  Printf.printf "%d/%d tests passed\n" !ok (List.length l)
(* ------------------------------ testing END ------------------------------- *)

let string_to_formula s =
  let input = LazyStream.of_string s in
  match parse formula input with
  | Some ans -> ans
  | None -> raise @@ invalid_arg @@ Printf.sprintf "[%s] is not a valid formula" s

let formula_to_string fm = formula5 () fm

let get_value (p : string) (v : valuation) : bool =
  List.assoc p v

let rec eval (fm : formula) (v : valuation) : bool =
  match fm with
  | True -> true
  | False -> false
  | P p -> get_value p v
  | Not p -> not (eval p v)
  | And (p, q) -> eval p v && eval q v
  | Or (p, q) -> eval p v || eval q v
  | Imp (p, q) -> not (eval p v) || eval q v
  | Iff (p, q) -> eval p v = eval q v

let test_eval () =
  test [
      ("eval (p1 => ~(p2 + p1 . p3)) with (p1:false, p2:false, p3:true)",
       fun () -> assert_equals true
                   (eval (string_to_formula "p1 => ~(p2 + p1 . p3)") ["p1", false; "p2", false; "p3", true]));
      ("eval (p1 => ~(p2 + p1 . p3)) with (p1:true, p2:true, p3:true)",
       fun () -> assert_equals false
                   (eval (string_to_formula "p1 => ~(p2 + p1 . p3)") ["p1", true; "p2", true; "p3", true]));
      ("eval (p1 <=> ~p2) with (p1:true, p2:true)",
       fun () -> assert_equals false
                   (eval (string_to_formula "p1 <=> ~p2") ["p1", true; "p2", true]));
      ("eval (p1 <=> ~p2) with (p1:true, p2:false)",
       fun () -> assert_equals true
                   (eval (string_to_formula "p1 <=> ~p2") ["p1", true; "p2", false]));
      ("eval (p1 . ~p2 + (~~~p3 => false)) with (p1:true, p2:false, p3:true)",
       fun () -> assert_equals true
                   (eval (string_to_formula "p1 . ~p2 + (~~~p3 => false)") ["p1", true; "p2", false; "p3", true]));
      ("eval (p1 . ~p2 + (~~~p3 => false)) with (p1:false, p2:false, p3:true)",
       fun () -> assert_equals true
                   (eval (string_to_formula "p1 . ~p2 + (~~~p3 => false)") ["p1", false; "p2", false; "p3", true]));
      ("eval (false) with (p1:false, p2:false, p3:true)",
       fun () -> assert_equals false
                   (eval (string_to_formula "false") ["p1", false; "p2", false; "p3", true]));
      ("eval (p) with (p:false)",
       fun () -> assert_equals false
                   (eval (string_to_formula "p") ["p", false]));
      ("eval (p) with (p:true)",
       fun () -> assert_equals true
                   (eval (string_to_formula "p") ["p", true]));
      ("eval (c1 <=> (~c1 . ~c2)) with (c1:true, c2:false)",
       fun () -> assert_equals false
                   (eval (string_to_formula "c1 <=> (~c1 . ~c2)") ["c1", true; "c2", false]));
    ]

let atoms (fm : formula) : string list = let rec aux f = 
  match f with
  | True | False -> []
  | P p -> [p]
  | Not p -> aux p
  | And (p, q) | Or (p, q) | Imp (p, q) | Iff (p, q) -> aux p @ aux q
in List.sort_uniq compare (aux fm)


let test_atoms () =
  test [
      ("atoms (p1 => ~(p2 + p1 . p3))",
       fun () -> assert_equals ["p1"; "p2"; "p3"]
                   (string_to_formula "p1 => ~(p2 + p1 . p3)" |> atoms |> List.sort compare));
      ("atoms (false)",
       fun () -> assert_equals []
                   (string_to_formula "false" |> atoms |> List.sort compare));
      ("atoms (c1 <=> (~c1 . ~c2))",
       fun () -> assert_equals ["c1"; "c2"]
                   (string_to_formula "c1 <=> (~c1 . ~c2)" |> atoms |> List.sort compare));
      ("atoms (~(p <=> q <=> r <=> s))",
       fun () -> assert_equals ["p"; "q"; "r"; "s"]
                   (string_to_formula "~(p <=> q <=> r <=> s)" |> atoms |> List.sort compare));
      ("atoms (~(p => q <=> r + s))",
       fun () -> assert_equals ["p"; "q"; "r"; "s"]
                   (string_to_formula "~(p => q <=> r + s)" |> atoms |> List.sort compare));
      ("atoms (p . p . ~p + p => true)",
       fun () -> assert_equals ["p"]
                   (string_to_formula "p . p . ~p + p => true" |> atoms |> List.sort compare));
    ]

let tautology (fm : formula) : bool =
  let valu = List.map (fun x -> (x,false)) (atoms fm) in
  let rec aux v =
    match v with
    | [] -> eval fm []
    | (_, true)::_ -> true
    | (p, false)::t -> eval fm v && eval fm ((p, true)::t) && aux (t@[(p, true)])
  in aux valu
let test_tautology () =
  test [
      ("tautology (p1 => ~(p2 + p1 . p3))",
       fun () -> assert_equals false
                   (tautology (string_to_formula "p1 => ~(p2 + p1 . p3)")));
      ("tautology (p => q => p)",
       fun () -> assert_equals true
                   (tautology (string_to_formula "p => q => p")));
      ("tautology (true)",
       fun () -> assert_equals true
                   (tautology (string_to_formula "true")));
      ("tautology (false)",
       fun () -> assert_equals false
                   (tautology (string_to_formula "false")));
      ("tautology (~(p . q) <=> ~p + ~q)",
       fun () -> assert_equals true
                   (tautology (string_to_formula "~(p . q) <=> ~p + ~q")));
      ("tautology ((p + q + ~s) <=> ~(q . r . s))",
       fun () -> assert_equals false
                   (tautology (string_to_formula "(p + q + ~s) <=> ~(q . r . s)")));
      ("tautology (~~p <=> p)",
       fun () -> assert_equals true
                   (tautology (string_to_formula "~~p <=> p")));
      ("tautology (p . ~p <=> false)",
       fun () -> assert_equals true
                   (tautology (string_to_formula "p . ~p <=> false")));
      ("tautology (p)",
       fun () -> assert_equals false
                   (tautology (string_to_formula "p")));
    ]

let print_valuation (valuation : valuation) : unit =
  List.iter
    (fun (p, v) ->
      printf "%s:%b " p v)
    valuation;
  print_newline ()

let find_truth (f : valuation -> unit) (fm : formula) : unit =
  let rec aux v left = match left with
    | [] -> if eval fm v then f v else ()
    | p::t -> aux ((p, true)::v) t; aux ((p, false)::v) t
  in aux [] (atoms fm)

let test_find_truth () =
  let count () =
    let n = ref 0 in
    (fun _ -> incr n),
    (fun () -> !n)
  in
  test [
      ("find_truth (c1 <=> ~c1 . ~c2)",
       let inc, res = count () in
       fun () -> assert_equals 1
                   (string_to_formula "c1 <=> ~c1 . ~c2" |> find_truth inc; res ()));
      ("find_truth (p + q)",
       let inc, res = count () in
       fun () -> assert_equals 3
                   (string_to_formula "p + q" |> find_truth inc; res ()));
      ("find_truth (p . q)",
       let inc, res = count () in
       fun () -> assert_equals 1
                   (string_to_formula "p . q" |> find_truth inc; res ()));
      ("find_truth (p <=> q)",
       let inc, res = count () in
       fun () -> assert_equals 2
                   (string_to_formula "p <=> q" |> find_truth inc; res ()));
      ("find_truth (p . ~p)",
       let inc, res = count () in
       fun () -> assert_equals 0
                   (string_to_formula "p . ~p" |> find_truth inc; res ()));
      ("find_truth ((p + q + ~s) <=> ~(q . r . s))",
       let inc, res = count () in
       fun () -> assert_equals 12
                   (string_to_formula "(p + q + ~s) <=> ~(q . r . s)" |> find_truth inc; res ()));
    ]

let rec nnf (f : formula) : formula  =
  match f with
  | Or(p,q) -> Or(nnf p, nnf q)
  | And(p,q) -> And(nnf p, nnf q)
  | Imp (p, q) -> Or (Not (nnf p), nnf q)
  | Iff (p, q) -> Or (And (nnf p, nnf q), And (Not (nnf p), Not (nnf q)))
  | Not True -> False
  | Not False -> True
  | Not (Not p) -> nnf p
  | Not (And (p, q)) -> Or (nnf (Not p), nnf (Not q))
  | Not (Or (p, q)) -> And (nnf (Not p), nnf (Not q))
  | Not (Imp (p, q)) -> And (nnf p, nnf (Not q))
  | Not (Iff (p, q)) -> Or (And (nnf p, nnf (Not q)), And (Not (nnf p), nnf q))
  | _ -> f

let test_nnf () =
  let rec well_formed = function
    | And (p, q)
    | Or (p, q) -> well_formed p && well_formed q
    | Imp _
    | Iff _
    | Not (Not _)
    | Not (And _)
    | Not (Or _)
    | Not (Imp _)
    | Not (Iff _) -> false
    | _ -> true
  in
  test [
      ("nnf (c1 <=> ~c1 . ~c2)",
       fun () -> assert_equals true
                   (string_to_formula "c1 <=> ~c1 . ~c2" |> nnf |> well_formed));
      ("nnf (~((p + q + ~s) <=> ~(q . r . s)))",
       fun () -> assert_equals true
                   (string_to_formula "~((p + q + ~s) <=> ~(q . r . s))" |> nnf |> well_formed));
      ("nnf (p => q => r => p)",
       fun () -> assert_equals true
                   (string_to_formula "p => q => r => p" |> nnf |> well_formed));
      ("nnf (~~~~~~~~~~~~~~true)",
       fun () -> assert_equals true
                   (string_to_formula "~~~~~~~~~~~~~~true" |> nnf |> well_formed));
      ("nnf (~~~~(~~~p<=>q.~(a+b)))",
       fun () -> assert_equals true
                   (string_to_formula "~~~~(~~~p<=>q.~(a+b))" |> nnf |> well_formed));
      ("nnf (false + q . ~r + ~t)",
       fun () -> assert_equals true
                   (string_to_formula "false + q . ~r + ~t" |> nnf |> well_formed));
    ]

let simplify formula =
  let open List in
  let setify l = sort_uniq Stdlib.compare l in
  let union =
    let rec union l1 l2 =
      match l1, l2 with
      | [], l2 -> l2
      | l1, [] -> l1
      | (h1::t1 as l1), (h2::t2 as l2) ->
         if h1 = h2 then h1::(union t1 t2)
         else if h1 < h2 then h1::(union t1 l2)
         else h2::(union l1 t2) in
    fun l1 l2 -> union (setify l1) (setify l2)
  in
  let rec all_pairs f l1 l2 =
    match l1 with
    | h1::t1 ->
       fold_right (fun x acc -> f h1 x :: acc) l2 (all_pairs f t1 l2)
    | [] -> []
  in
  let distribute s1 s2 =
    all_pairs union s1 s2
    |> setify
  in
  let rec pure_cnf fm =
    match fm with
    | And (p, q) -> union (pure_cnf p) (pure_cnf q)
    | Or (p, q)  -> distribute (pure_cnf p) (pure_cnf q)
    | _ -> [[fm]]
  in
  let negative = function
    | Not p -> true
    | _ -> false
  in
  let positive lit = not @@ negative lit in
  let negate = function
    | Not p -> p
    | p -> Not p
  in
  let intersect =
    let rec intersect l1 l2 =
      match (l1,l2) with
      | ([],l2) -> []
      | (l1,[]) -> []
      | ((h1::t1 as l1),(h2::t2 as l2)) ->
         if h1 = h2 then h1::(intersect t1 t2)
         else if h1 < h2 then intersect t1 l2
         else intersect l1 t2 in
    fun l1 l2 -> intersect (setify l1) (setify l2)
  in
  let image f s =
    map f s
    |> setify
  in
  let trivial lits =
    let pos, neg = partition positive lits in
    intersect pos (image negate neg) <> []
  in
  let psubset =
    let rec subset l1 l2 =
      match l1, l2 with
      | [], l2 -> true
      | l1, [] -> false
      | h1::t1, h2::t2 ->
         if h1 = h2 then subset t1 t2
         else if h1 < h2 then false
         else subset l1 t2
    and psubset l1 l2 =
      match l1, l2 with
      | l1, [] -> false
      | [], l2 -> true
      | h1::t1, h2::t2 ->
         if h1 = h2 then psubset t1 t2
         else if h1 < h2 then false
         else subset l1 t2
    in
    fun s1 s2 -> psubset (setify s1) (setify s2)
  in
  let list_conj l =
    match l with
    | [] -> True
    | h :: t ->
       fold_left (fun acc x -> And (acc, x)) h t
  in
  let list_disj l =
    match l with
    | [] -> False
    | h :: t ->
       fold_left (fun acc x -> Or (acc, x)) h t
  in
  let non p x = not @@ p x in
  let simplified_cnf fm =
    if fm = False then [[]]
    else if fm = True then []
    else
      let cjs =
        filter (non trivial) (pure_cnf fm)
      in
      filter (fun c -> not @@ exists (fun c' -> psubset c' c) cjs) cjs
  in
  let cnf fm = map list_disj (simplified_cnf fm) |> list_conj in
  cnf formula


(*
  A1 asserts that A1 and A2 are both knaves.
   Can you tell the type of each natives?
 *)

(* If I am a knight there is gold on the island.
   Can you tell the type of the native and if there is gold
   on the island?
 *)

(* Either I am a knave or there is gold on the island. *)

(* I am a knave and there is no gold on the island. *)

(*
  The native made the following two assertions:
  1) There is gold on this island.
  2) If there is gold here, then there also is silver.
  Can you tell the type of the native and if there is gold,
  silver on the island?
 *)

(*
  Suppose there is a trial on this island, and two witnesses A1 and A2 make
  the following statements:
  A1: If A2 and I are both knights, then the defendant is guilty.
  A2: A1 is a knight.
 *)

(*
  Suppose there is a trial on this island, and two witnesses A1 and A2 make
  the following statements:
  A1: If A2 is a knight then the defendant is innocent.
  A2: If A1 is a knave, then the defendant is innocent.
 *)

(*
  Suppose there is a trial on this island, and two witnesses A1 and A2 make
  the following statements:
  A1: If either of us is a knight, then the defendant is guilty.
  A2: If either of us is a knave, then the defendant is guilty.
 *)

(*
  Suppose there is a trial on this island, and two witnesses A1 and A2 make
  the following statements:
  A1: If I am a knight and A2 is a knave, then the defendant is guilty.
  A2: That is not true!
 *)


(*
  Female knights lie and female knaves tell the truth, whereas male knights
  tell the truth and male knaves lie.
  We are given that A1 and A2 are a married heterosexual couple, and they write
  the following statements:
  A1: My spouse is a knight.
  A2: We are both knights.
  From this, is it possible to tell both which one is the husband, and which
  knight-knave type each is?
 *)

(*
  If this sentence is true then Santa Claus exists.
 *)
