let rec split v l = if l = [] then ([], []) else
  let (l1,l2) = split v (List.tl l) in  
  if List.hd l < v then ((List.hd l)::l1, l2) else (l1, (List.hd l)::l2)

(* TEST *)
(* doit retourner [-12; 1; 3], [12; 27; 7; 8; 6; 12; 42] *)
let res1,res2 = split 4 [12; 27; -12; 7; 8; 1; 3; 6; 12; 42]
(* END TEST *)

let rec qs l = if List.length l <= 1 then l else
  let (l1,l2) = split (List.hd l) (List.tl l) in
  (qs l1) @ ((List.hd l)::(qs l2))

(* TEST *)
(* doit retourner [-12; 1; 3; 4; 6; 7; 8; 12; 12; 27; 42] *)
let res = qs [4; 12; 27; -12; 7; 8; 1; 3; 6; 12; 42]
(* END TEST *)

let rec jqastable x f = if f x <> x then jqastable (f x) f else x

(* TEST *)
(* doit retourner 1 *)
let res = jqastable 13 (fun x -> if (x = 1) then 1 else if (x mod 2 = 1) then 3 * x + 1 else x / 2)
(* END TEST *)

let rec unebulle l = if List.length l <= 1 then l else
  if List.hd l > List.nth l 1 then (List.nth l 1)::(unebulle ((List.hd l)::(List.tl (List.tl l))))
  else (List.hd l)::(unebulle (List.tl l))

(* TEST *)
(* doit retourner [4; 12; -12; 7; 8; 1; 3; 6; 27; 12; 42] *)
let res = unebulle [4; 12; 27; -12; 7; 8; 1; 3; 6; 42; 12]
(* END TEST *)

let tribulle l = jqastable l unebulle

(* TEST *)
(* doit retourner [-12; 1; 3; 4; 6; 7; 8; 12; 12; 27; 42] *)
let res = tribulle [4; 12; 27; -12; 7; 8; 1; 3; 6; 12; 42]
(* END TEST *)

let rec merge ll = if ll = [] then [] else
  (List.hd ll)@(merge (List.tl ll))

(* TEST *)
(* doit retourner [1;2;3;5] *)
let res = merge [[1];[2;3];[5]]
(* END TEST *)

let create f k = let rec aux i acc = if i = 0 then acc
  else aux (i-1) ((f i)::acc) in aux k []

(* TEST *)
(* doit retourner [2; 3; 4; 5] *)
let res = create (fun x -> x+1) 4
(* END TEST *)

let rec insert j ll = match ll with
  | [] -> []
  | l::ll -> (j::l)::(insert j ll)

(* TEST *)
(* doit retourner [[1;3;5];[1;7;3;9];[1];[1;6]]*)
let res = insert 1 [[3;5];[7;3;9];[];[6]]
(* END TEST *)

let partition n = let rec loop m k = 
  match (m,k) with
  | (0,0) -> [[]]
  | (_,0) -> []
  | (a,b) when a < b -> loop a a
  | (a,b) -> merge (create (fun x -> insert x (loop (a-x) x)) b) in
  loop n n

(* TEST *)
(* doit retourner une liste contenant [5], [4;1], [3;2], [3;1;1], [2;2;1],
   [2;1;1;1], [1;1;1;1;1] dans un ordre arbitraire *)
let res = partition 5
(* END TEST *)

let rec kieme k l = List.nth (qs l) k


(* TEST *)
(* doit retourner 8 *)
let res = kieme 7 [4; 12; 27; -12; 7; 8; 1; 3; 6; 12; 42]
(* END TEST *)
