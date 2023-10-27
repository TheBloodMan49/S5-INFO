(* Examen Ocaml 2020 *)
(* Nom:              *)
(* Prénom:           *)

(** {Nombre en représentation binaire} **)

(* Q1 *)

type bit = B0 | B1 (* remplacer unit par votre définition *)

type bint = bit list

(* Q2  *)

let int_of_bit b = match b with 
  | B0 -> 0
  | B1 -> 1

let _ = int_of_bit B0 = 0 && int_of_bit B1 = 1;;

(* Q3 *)
let int_of_bint l = List.fold_right (fun x acc -> if x = B0 then acc*2 else acc*2+1) l 0

let _ = int_of_bint [B1] = 1 && int_of_bint [B1;B0;B1] = 5;;

(* Q4 *)

let rec count_zeros l = match l with
  | [] -> 0
  | B0::l' -> 1 + count_zeros l'
  | B1::_ -> 0

let _ = count_zeros [B0;B0;B0;B1;B0;B1] = 3;;

(* Q5 *)

let rec count_and_remove l = match l with
  | [] -> ([],0)
  | [B0] -> ([],1)
  | [B1] -> ([B1],0)
  | e::l' -> if e = B0 then (fst (count_and_remove l'),snd (count_and_remove l')+1)
  else (e::l',0)


let _ = count_and_remove [B0;B0;B0;B1;B0;B1] = ([B1;B0;B1], 3);;

(* Q6 *)

let normalise l = List.rev (fst (count_and_remove (List.rev l)))


let _ =
  let n1 = normalise [B0;B1;B1] in
  let n2 = normalise [B0;B1;B1;B0] in
  let n3 = normalise [B0;B1;B1;B0;B0] in
  n1 = n2 && n2 = n3 && n3 = [B0;B1;B1];;


(** {Bibliothèque d'ensembles} *)
type comparison = EQUAL| LESSTHAN| GREATERTHAN

let cmp_int = fun i j -> if i = j then EQUAL else if i < j then LESSTHAN else GREATERTHAN;;

(* Q7 *)

let rec is_sorted cmp l = match l with
  | [] | [_] -> true
  | e::e'::l' -> cmp e e' = LESSTHAN && is_sorted cmp (e'::l')

let _ = (is_sorted cmp_int [1;5;7]) && not (is_sorted cmp_int [1;7;5]);;

(** Q8 *)

let rec add_elt cmp e l = match l with
  | [] -> [e]
  | e'::l' -> match cmp e e' with
    | EQUAL -> l
    | LESSTHAN -> e::l
    | GREATERTHAN -> e'::(add_elt cmp e l')


let _ = add_elt cmp_int 6 [1;5;7] = [1;5;6;7];;

(* Q9 *)

let rec union cmp l1 l2 = match (l1,l2) with
  | ([],l) -> l
  | (l,[]) -> l
  | (e'::l',e''::l'') -> match cmp e' e'' with
    | EQUAL -> e'::(union cmp l' l'')
    | LESSTHAN -> e'::(union cmp l' (e''::l''))
    | GREATERTHAN -> e''::(union cmp (e'::l') l'')


let _ = union cmp_int [1;5;7] [2;5;6] = [1;2;5;6;7];;


(** {Représentation d'ensembles d'entiers} *)

type intset = Empty | Node of intset * bool * intset;;

(* Soit la liste d'entiers binaires l qui représente l'ensemble {0;2;3;4;7}. *)
let l = [ [] ; [B0;B1] ; [B1;B1] ; [B0;B0;B1] ; [B1;B1;B1] ];;
(* L'arbre a reprśente la liste l sous forme de intset *)
let a = Node
          (Node (Node (Empty, false, Node (Empty, true, Empty)), false,
                 Node (Empty, true, Empty)),
           true, Node (Empty, false, Node (Empty, true, Node (Empty, true, Empty))))

(* Q10 *)

let rec cardinal s = match s with
  | Empty -> 0
  | Node (g,b,d) -> if b then 1 + cardinal g + cardinal d else cardinal g + cardinal d

let _ = cardinal Empty = 0 && cardinal a = 5

(* Q11 *)

let rec mem s x = match s,x with
  | Empty,_ -> false
  | Node (_,b,_),[] -> b
  | Node (g,_,d),e::l -> if e = B0 then mem g l else mem d l

let _ = mem a [] && mem a [B0;B0;B1] && not (mem a [B1]);;

(* Q12 *)

let rec singleton l = match l with
  | [] -> Node (Empty, true, Empty)
  | e::l' -> if e = B0 then Node (singleton l', false, Empty) else Node (Empty, false, singleton l')

let _ = singleton [B0;B1] = Node (Node (Empty, false, Node (Empty, true, Empty)), false, Empty);;

(* Q13 *)

let rec add_elt i s = match i,s with
  | [],Empty -> Node (Empty, true, Empty)
  | [],Node (g,_,d) -> Node (g, true, d)
  | e::l, Empty -> if e = B0 then Node (add_elt l Empty, false, Empty) else Node (Empty, false, add_elt l Empty)
  | e::l, Node (g,b,d) -> if e = B0 then Node (add_elt l g, b, d) else Node (g, b, add_elt l d)


let a1 = Node
 (Node (Node (Empty, false, Node (Empty, true, Empty)), false,
   Node (Empty, true, Node (Empty, true, Empty))),
 true, Node (Empty, false, Node (Empty, true, Node (Empty, true, Empty))));;

let _ = add_elt [B1;B1] a = a &&  add_elt [B0;B1; B1] a = a1;;


(* Q14 *)

let rec remove_elt i s = match i,s with
  | _,Empty -> Empty
  | [],Node (g,_,d) -> Node (g, false, d)
  | e::l, Node (g,b,d) -> if e = B0 then Node (remove_elt l g, b, d) else Node (g, b, remove_elt l d)

let _ = remove_elt [] Empty = Empty &&
          remove_elt [B1;B1;B1] Empty = Empty &&
            remove_elt [] a  = Node
          (Node (Node (Empty, false, Node (Empty, true, Empty)), false,
                 Node (Empty, true, Empty)),
           false, Node (Empty, false, Node (Empty, true, Node (Empty, true, Empty)))) &&
              remove_elt [B1;B1;B1] a = 
                Node
                  (Node (Node (Empty, false, Node (Empty, true, Empty)), false,
                         Node (Empty, true, Empty)),
                   true, Node (Empty, false, Node (Empty, true, Node (Empty, false, Empty))));;

(* Q15 *)

let rec is_empty s = match s with
  | Empty -> true
  | Node (g,b,d) -> not b && is_empty g && is_empty d

let _ = is_empty Empty = true  &&
        is_empty (Node(Empty, false, Empty)) = true &&
          is_empty (Node(Empty, true, Empty)) = false &&
            is_empty a = false;;

(* Q16 *)


let rec minimise s = match is_empty s with
  | true -> Empty
  | false -> match s with
    | Node (g,b,d) -> Node (minimise g,b,minimise d)
    | Empty -> Empty

let _ = minimise Empty = Empty &&
          minimise (Node(Empty,false,Empty)) = Empty &&
            minimise
              (Node
                 (Node (Node (Empty, false, Node (Empty, false, Empty)), false,
                     Node (Empty, false, Empty)),
                  false, Node (Empty, false, Node (Empty, true, Node (Empty, false, Empty))))) =
              Node (Empty, false, Node (Empty, false, Node (Empty, true, Empty)));;

(* Q17 *)

let rec union s1 s2 = match s1,s2 with
  | Empty,Empty -> Empty
  | Empty,Node (g,b,d) | Node (g,b,d),Empty -> Node (g,b,d)
  | Node (g1,b1,d1),Node (g2,b2,d2) -> Node (union g1 g2, b1 || b2, union d1 d2)

let u1 =
  (* [ [] ; [B0;B1] ; [B1;B1;B1] ] *)
  Node (Node (Empty, false, Node (Empty, true, Empty)), true,
               Node (Empty, false, Node (Empty, false, Node (Empty, true, Empty))));;
let u2 = 
  (*[[B1;B1];[B0;B0;B1];[B1;B1;B1]] *)
  Node (Node (Node (Empty, false, Node (Empty, true, Empty)), false, Empty),
        false, Node (Empty, false, Node (Empty, true, Node (Empty, true, Empty))));;

let _ = union u1 Empty = u1 && union Empty u2 = u2 && union u1 u2 = a;;


(* Q18 *)

let div2 s = match s with
  | Empty -> Empty
  | Node (g,_,d) -> union g d

let _ =
  div2 Empty = Empty
  && div2 (Node(Empty, true, Empty)) = Empty
  && div2 (Node(Node(Empty, true, Empty),false,Empty)) = Node(Empty, true, Empty)
  && div2 (Node(Empty,false,Node(Empty, true, Empty))) = Node(Empty, true, Empty)
  && div2 a = Node (Node (Empty, false, Node (Empty, true, Empty)), false,
   Node (Empty, true, Node (Empty, true, Empty)));;

(* Q19 *)

let rec elements s = match s with 
  | Empty -> []
  | Node (g,b,d) -> let e = if b then [[]] else [] in
                    e 
                    @ List.map (fun x -> B0::x) (elements g)
                    @ List.map (fun x -> B1::x) (elements d)
    
let _ =
  elements Empty = [] &&
  elements (Node(Empty, true, Empty)) = [[]]  &&
    elements (Node (Node(Empty,true, Empty), false, Empty)) = [[B0]]  &&
      elements (Node(Empty, false, (Node(Empty, true, Empty)))) = [[B1]]  &&
        List.sort compare (elements a) = List.sort compare l;;




