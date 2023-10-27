(* remplacer unit par votre type *)
type 'a narbr = Noeud of 'a * 'a narbr list

let feuille v = Noeud (v, [])
let noeud v l = Noeud (v, l)
let valeur a = match a with
  | Noeud (v, _) -> v
let sous_arbres a = match a with
  | Noeud (_, l) -> l

let a1 = feuille 4
let a2 = noeud 3 [a1; a1]

(* TEST *)
(* Doivent retourner true *)
let _ = valeur a1 = 4
let _ = valeur a2 = 3
let _ = sous_arbres a1 = []
let _ = sous_arbres a2 = [a1;a1]
(* END TEST *)

let rec compter (a:'a narbr) = match a with
| Noeud (_,[]) -> 1
| Noeud (_,l) -> compterl l
and compterl (l: ('a narbr) list) = List.fold_left (fun acc a -> acc + compter a) 0 l
(* TEST *)
(* doit retourner 2 *)
let _ = compter a2
(* END TEST *)

let rec pluslongue (a:'a narbr) = match a with
| Noeud (_,l) -> 1 + pluslonguel l
 and pluslonguel (l: ('a narbr) list) = List.fold_left (fun acc a -> max acc (pluslongue a)) 0 l
let a3 = noeud 8 [a1; a2; a1]
(* TEST *)
(* doit retourner 3 *)
let _ = pluslongue a3
(* END TEST *)

let rec valeurs (a:'a narbr) = match a with
| Noeud (v,[]) -> [v]
| Noeud (v,l) -> v :: valeursl l
 and valeursl (l: ('a narbr) list) = List.fold_left (fun acc a -> acc @ valeurs a) [] l
let a3 = noeud 8 [a1; a2; a1]
(* TEST *)
(* doit retourner [3; 4; 4; 4; 4; 8] *)
let _ = List.sort compare (valeurs a3)
(* END TEST *)

let rec find (p:'a -> bool) (a:'a narbr) = let Noeud(v,l) = a in match p v with
| true -> Some v
| false -> findl p l
and findl (p:'a -> bool) (l:('a narbr) list) = match l with
| [] -> None
| a::q -> match find p a with
  | None -> findl p q
  | Some v -> Some v

let f4 = feuille 4
let f10 = feuille 10
let f12 = feuille 12
let f13 = feuille 13
let f20 = feuille 20
let f21 = feuille 21
let n7 = noeud 7 [ f10; f12; f13 ]
let n3 = noeud 3 [ f4; n7; f20]
let n5 = noeud 5 [ n3; f21 ]

(* TEST *)
(* Ceci doit retourner (Some 20) *)
let _ = find (fun x -> x > 14 && x < 21) n5
(* END TEST *)

type 'a ternaire = TNode of 'a * 'a ternaire * 'a ternaire * 'a ternaire | TFeuille of 'a
let rec ternaire a = match a with
| TFeuille v -> feuille v
| TNode (v, a1, a2, a3) -> noeud v [ternaire a1; ternaire a2; ternaire a3]

let tern = TNode("a",TNode("b",TFeuille "c",TFeuille "d",TFeuille "e"), TFeuille "f" , TFeuille "g")
let ntern = noeud "a" [noeud "b" [feuille "c" ; feuille "d" ;feuille "e"]; feuille "f" ; feuille "g"]
(* TEST *)
(* Ceci doit retourner true *)
let _ = ternaire tern = ntern
(* END TEST *)

let rec egal (a:'a narbr) (b:'a narbr) = match (a,b) with
| (Noeud (v1,l1), Noeud (v2,l2)) -> v1 = v2 && egall l1 l2
and egall (la:('a narbr) list) (lb:('a narbr) list) = match (la,lb) with
| ([],[]) -> true
| (a::q,b::r) -> egal a b && egall q r
| _ -> false

(* TEST *)
(* doit retourner true *)
let _ = egal n5 n5
(* END TEST *)

let rec remplace (a1:'a narbr) (a2:'a narbr) (a:'a narbr) : bool * 'a narbr =
  if egal a1 a then (true, a2)
  else match remplacel a1 a2 (sous_arbres a) with
  | (true, l) -> (true, noeud (valeur a) l)
  | (false, _) -> (false, a)
and remplacel (a1:'a narbr) (a2:'a narbr) (la:('a narbr) list) : bool * (('a narbr) list) = 
  match la with
  | [] -> (false, [])
  | a::q -> match remplace a1 a2 a with
    | (true, a) -> (true, a::q)
    | (false, a) -> match remplacel a1 a2 q with
      | (b, l) -> (b, a::l)

let n42 = noeud 42 [feuille 2048]
let res = noeud 5 [(noeud 3 [f4; n42; f20]); f21]

(* TEST *)
(* ceci doit retourner true *)
let _ = snd (remplace n7 n42 n5) = res
(* END TEST *)

(* This has to list branch values of the main tree *)
let rec listbr (a: 'a narbr) = let Noeud(v,l) = a in match l with
| [] -> [[v]]
| _ -> List.map (fun l -> v::l) (listbrl l)
and listbrl (l: ('a narbr) list) = match l with
| [] -> []
| a::q -> (listbr a) @ (listbrl q)


(* TEST *)
(* doit retourner true *)
let _ =
  let res = [
    [5; 3; 4];
    [5; 3; 7; 10];
    [5; 3; 7; 12];
    [5; 3; 7; 13];
    [5; 3; 20];
    [5; 21]
  ] in
  List.sort compare (listbr n5) =
  List.sort compare res
(* END TEST *)
