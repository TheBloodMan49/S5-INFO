(* Mohamed Aziz Chahbi et Paul Gasnier *)

let rec appartient e l = match l with 
  | [] -> false
  | a::_ when a = e -> true
  | _::l' -> appartient e l'

(* TEST *)
(* Ceci doit retourner false *)
let res = appartient 4 [1;2;3]
(* END TEST *)

let rec concatl l1 l2 = match l1 with 
  | [] -> l2
  | a::l1' -> a::(concatl l1' l2)

(* TEST *)
(* Ceci doit retourner [1;2;3;4;5;6] *)
let res = concatl [1;2;3] [4;5;6]
(* END TEST *)

let rec debliste l n = match (l,n) with 
  | (_,0) | ([],_) -> []
  | (a::l',_) -> a::(debliste l' (n-1))

(* TEST *)
(* Ceci doit retourner [1; 2; 3] *)
let res = debliste [1;2;3;4;5;6;7] 3
(* END TEST *)

let rec remplace x y l = match l with 
  | [] -> []
  | a::l' when a = x -> y::(remplace x y l')
  | a::l' -> a::(remplace x y l')

(* TEST *)
(* Ceci doit retourner [1; 42; 3; 42; 5] *)
let res = remplace 2 42 [1;2;3;2;5]
(* END TEST *)

let rec rang_opt e l = match l with 
| [] -> None
| a::_ when a = e -> Some 0
| _::l' -> match rang_opt e l' with
    | None -> None
    | Some i -> Some (1+i)

(* TEST *)
(* Ceci doit retourner Some 1 *)
let res = rang_opt 2 [3;2;1]
(* Ceci doit retourner None *)
let res = rang_opt 0 [3;2;1]
(* END TEST *)

type 'a nemptyl = Elt of 'a | Cons of 'a * 'a nemptyl
let rec of_list l = if l = [] then None else match l with 
  | [] -> assert false
  | a::l' -> match of_list l' with
    | None -> Some (Elt a)
    | Some l'' -> Some (Cons (a,l''))

(* TEST *)
(* Ceci doit retourner None *)
let res = of_list []
(* Ceci doit retourner Some(Cons(3,Cons(2,(Cons(Elt 1))))) *)
let res = of_list [3;2;1]
(* END TEST *)

let rec to_list l = match l with 
  | Elt a -> [a]
  | Cons (a,l') -> a::(to_list l')
(* TEST *)
(* Ceci doit retourner [1;0] *)
let res = to_list (Cons (1, Elt 0))
(* END TEST *)

let rec entete l l1 = match (l,l1) with 
  | ([],_) -> true
  | (a::l',b::l1') when a=b -> entete l' l1'
  | _ -> false

(* TEST *)
(* Ceci doit retourner true *)
let res = entete [1;2;3] [1;2;3;2;5]
(* END TEST *)

let rec sousliste l l1 = match l1 with 
  | [] -> false
  | _::l1' -> if entete l l1 then true else sousliste l l1'

(* TEST *)
(* Ceci doit retourner true *)
let res = sousliste [2;3;2] [1;2;3;2;5]
(* END TEST *)

let oter l l1 = if entete l l1 then let rec aux l' l1' =
  match (l',l1') with 
    | ([],_) -> l1'
    | (_::l'',_::l1'') -> aux l'' l1''
    | _ -> assert false
  in aux l l1
  else l1

(* TEST *)
 (* Ceci doit retourner [2; 5] *)
 let res = oter [1;2;3] [1;2;3;2;5]
(* END TEST *)

let rec all_pairs l1 l2 = match l1 with
  | [] -> []
  | a::l' -> List.map (fun x -> (a,x)) l2 @ all_pairs l' l2

(* TEST *)
(* Ceci doit retourner [(1,3); (1,4); (1,5) ; (2,3) ; (2;4) ; (2;5)] *)
let res = all_pairs [1;2] [3;4;5]
(* END TEST *)

let rec insert ki e l = match l with 
  | [] -> [(ki,[e])]
  | (ki',l')::l'' when ki = ki' -> (ki,e::l')::l''
  | (ki',l')::l'' -> (ki',l')::(insert ki e l'')

(* TEST *)
(* Ceci doit retourner [(true, [5;3;1]);(false,[4;2])] *)
let res = insert true 5 [(true, [3;1]);(false, [4;2])]
(* Ceci doit retourner [(true, [5;3;1]);(false,[2])] *)
let res = insert false 2 [(true, [3;1])]
(* END TEST *)

let rec group_by f l = match l with 
  | [] -> []
  | a::l' -> insert (f a) a (group_by f l')

(* TEST *)
(* Ceci doit retourner [(1, [(1,2);(1,4)]);(3,[(3,5)])] *)
let res = group_by fst [(1,2) ; (3,5) ; (1,4)]
(* END TEST *)
