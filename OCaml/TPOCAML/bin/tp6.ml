(* remplacer par votre type *)
type 'a arbin = Feuille of 'a 
   | Noeud of 'a * ('a arbin) * ('a arbin)

let feuille v = Feuille v
let noeud v g d = Noeud(v,g,d)

let rec compter a = match a with
   | Feuille _ -> 1
   | Noeud(_,g,d) -> compter g + compter d

(* TEST *)
(* ceci doit retourner 3 *)
let arbre_test = noeud 12 (feuille 5) (noeud 7 (feuille 6) (feuille 8))
let _ = compter arbre_test
(* END TEST *)

let rec to_list a = match a with
   | Feuille v -> [v]
   | Noeud(v,g,d) -> to_list g @ [v] @ to_list d

(* TEST *)
(* ceci doit retourner [5; 12; 6; 7; 8] *)
let _ = to_list arbre_test
(* END TEST *)

let rec constr l = match l with
   | [] -> feuille "Nil"
   | [v] -> Noeud(v,feuille "Nil",feuille "Nil")
   | v::r -> let (l1,l2) = List.partition (fun x -> x < v) r in
      noeud v (constr l1) (constr l2)

(* TEST *)
(* Ceci doit retourner true *)

let l = ["celeri";"orge";"mais";"ble";"tomate"; "soja"; "poisson"]
(* TEST *)
(* Ceci doit retourner true *)
let _ = List.filter (fun s -> s <> "Nil") (to_list (constr l)) = List.sort compare l
(* END TEST *)

type coord = int * int
type 'a arbinp = (coord * 'a) arbin
let d = 5
let e = 4

let placer a = let rec findleft a = match a with
   | Feuille(_) -> 0
   | Noeud(_,l,_) -> match l with
      | Feuille(_) -> e
      | Noeud(_,_,_) -> 2*e + findleft l
in
   let rec findright a = match a with
   | Feuille(_) -> 0
   | Noeud(_,_,r) -> match r with
      | Feuille(_) -> e
      | Noeud(_,_,_) -> 2*e + findright r
in
   let rec aux a x y = match a with
   | Feuille(v) -> feuille ((x,y),v)
   | Noeud(v,l,r) -> let l' = aux l (x-(findright l)-e) (y+d) in
      let r' = aux r (x+(findleft r)+e) (y+d) in
      noeud ((x,y),v) l' r'
in aux a (findleft a +e) d 

let t =
  noeud 'a'
    (feuille 'j')
    (noeud 'b'
       (noeud 'c'
          (noeud 'd' (feuille 'w') (feuille 'k'))
          (feuille 'z'))
       (feuille 'y'))

(* Pour tester *)
let res = placer t

(* TEST *)
(* Ceci doit retourner true *)
let res = (placer t = noeud ((8, 5), 'a')
     (feuille ((4, 10), 'j'))
     (noeud ((32, 10), 'b')
        (noeud ((24, 15), 'c')
           (noeud ((16, 20), 'd') (feuille ((12, 25), 'w')) (feuille ((20, 25), 'k')))
           (feuille ((28, 20), 'z')))
        (feuille ((36, 15), 'y'))))
(* END TEST *)
