type coul = Coeur | Trefle | Pique | Carreau
type haut = Sept | Huit | Neuf | Dix | Valet | Dame | Roi | As
type carte = Carte of haut * coul

let coul c = let Carte(_, coul) = c in coul
let haut c = let Carte(haut, _) = c in haut

let haut_of_int i = match i with
  | 7 -> Sept
  | 8 -> Huit
  | 9 -> Neuf
  | 10 -> Dix
  | 11 -> Valet
  | 12 -> Dame
  | 13 -> Roi
  | 14 -> As
  | _ -> failwith "haut_of_int"

(* TEST *)
(* doit retourner Dame *)
let _ = haut_of_int 12
(* END TEST *)

let coul_of_string s = match s with
  | "Coeur" -> Coeur
  | "Trefle" -> Trefle
  | "Pique" -> Pique
  | "Carreau" -> Carreau
  | _ -> failwith "coul_of_string"

(* TEST *)
(* doit retourner Pique *)
let _ = coul_of_string "Pique"
(* END TEST *)

let carte i s = Carte(haut_of_int i, coul_of_string s)

(* TEST *)
(* ces tests doivent retourner true *)
let _ = (haut (carte 8 "Trefle")) = Huit
let _ = (coul (carte 14 "Trefle")) = Trefle
(* END TEST *)

let string_of_carte c = let Carte(haut, coul) = c in
  (match haut with
    | Sept -> "7"
    | Huit -> "8"
    | Neuf -> "9"
    | Dix -> "10"
    | Valet -> "Valet"
    | Dame -> "Dame"
    | Roi -> "Roi"
    | As -> "As") 
    ^ " de " ^
  (match coul with
   | Coeur -> "Coeur"
   | Trefle -> "Trefle"
   | Pique -> "Pique"
   | Carreau -> "Carreau")

(* TEST *)
(* ceci doit retourner la cha�ne "Valet de Pique" *)
let res = string_of_carte (carte 11 "Pique")

(* ceci doit retourner la cha�ne "9 de Trefle" *)
let res = string_of_carte (carte 9 "Trefle")
(* END TEST *)

let random_carte () = carte (Random.int 8 + 7) (match Random.int 4 with
  | 0 -> "Coeur"
  | 1 -> "Trefle"
  | 2 -> "Pique"
  | _ -> "Carreau")

(* TEST *)
let res = random_carte ()
(* END TEST *)

(*ajouter une carte absente de la liste*)
let rec ajtcarte l = let c = random_carte () in
  if List.mem c l then ajtcarte l else c::l

(* TEST *)
(* ceci doit retourner true *)
let res =
  let l1 = ajtcarte [] in
  let l2 = ajtcarte l1 in
  match l1,l2 with
  | [c],[c1; c2] -> c = c2 && c1 <> c2
  | _ -> false
(* END TEST *)

let rec faitjeu n = if n = 0 then [] else ajtcarte (faitjeu (n-1))

(* TEST *)
let res = faitjeu 32

let rec reduc l = if List.length l < 3 then l
else let x = (List.hd (List.hd l), (List.hd (List.nth l 2))) in match x with
  | (Carte(haut,coul), Carte(haut2,coul2)) when (haut = haut2) || (coul = coul2) -> 
      ((List.nth l 1)@(List.hd l))::(List.tl (List.tl l))
  | _ -> (List.hd l)::(reduc (List.tl l))

let p1 = [carte 14 "Trefle";  carte 10 "Coeur" ]
let p2 = [carte 7 "Pique";    carte 11 "Carreau" ]
let p3 = [carte 14 "Carreau"; carte 8 "Pique" ]
let p4 = [carte 7 "Carreau";  carte 10 "Trefle" ]

let p'1 = p2@p1

(* TEST *)
(* ceci doit retourner true *)
let _ = (reduc [p1; p2; p3; p4]) = [p'1; p3; p4]
(* END TEST *)

let rec reussite l = if (reduc l) = l then l else reussite (reduc l)

let p''1 = p3@p'1
(* TEST *)
(* ceci doit retourner true *)
let res = (reussite [p1; p2; p3; p4]) = [p''1; p4]
(* END TEST *)

(* Copiez la ligne suivante (avec le #) dans le toplevel (fen�tre du bas) et
   tapez Entr�e
#load "graphics.cma";;
*)
open Graphics

let b = white
let n = black
let r = red

let carr   = [| [|b;b;b;b;b;r;b;b;b;b;b|];
                [|b;b;b;b;r;r;r;b;b;b;b|];
                [|b;b;b;r;r;r;r;r;b;b;b|];
                [|b;b;r;r;r;r;r;r;r;b;b|];
                [|b;r;r;r;r;r;r;r;r;r;b|];
                [|b;b;r;r;r;r;r;r;r;b;b|];
                [|b;b;b;r;r;r;r;r;b;b;b|];
                [|b;b;b;b;r;r;r;b;b;b;b|];
                [|b;b;b;b;b;r;b;b;b;b;b|] |]

let tref   = [| [|b;b;b;b;b;n;n;b;b;b;b|];
                [|b;b;b;b;n;n;n;n;b;b;b|];
                [|b;b;b;b;n;n;n;n;b;b;b|];
                [|b;b;n;n;b;n;n;b;n;n;b|];
                [|b;n;n;n;n;n;n;n;n;n;n|];
                [|b;n;n;n;n;n;n;n;n;n;n|];
                [|b;b;n;n;b;n;n;b;n;n;b|];
                [|b;b;b;b;b;n;n;b;b;b;b|];
                [|b;b;b;b;n;n;n;n;b;b;b|] |]

let coeu   = [| [|b;b;r;r;b;b;b;r;r;b;b|];
                [|b;r;r;r;r;b;r;r;r;r;b|];
                [|b;r;r;r;r;r;r;r;r;r;b|];
                [|b;r;r;r;r;r;r;r;r;r;b|];
                [|b;b;r;r;r;r;r;r;r;b;b|];
                [|b;b;b;r;r;r;r;r;b;b;b|];
                [|b;b;b;b;r;r;r;b;b;b;b|];
                [|b;b;b;b;b;r;b;b;b;b;b|];
                [|b;b;b;b;b;r;b;b;b;b;b|] |]


let piqu   = [| [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;n;n;n;b;b;b;b|];
                [|b;b;n;n;n;n;n;n;n;b;b|];
                [|b;n;n;n;n;n;n;n;n;n;b|];
                [|b;n;n;n;n;n;n;n;n;n;b|];
                [|b;n;n;n;b;n;b;n;n;n;b|];
                [|b;b;b;b;b;n;b;b;b;b;b|];
                [|b;b;b;b;n;n;n;b;b;b;b|] |]

let draw_haut h = match h with
  | Sept -> draw_string " 7"
  | Huit -> draw_string " 8"
  | Neuf -> draw_string " 9"
  | Dix -> draw_string "10"
  | Valet -> draw_string " V"
  | Dame -> draw_string " D"
  | Roi -> draw_string " R"
  | As -> draw_string " A"

let draw_coul c l coul = match coul with
  | Carreau -> draw_image (make_image carr) c (l+2)
  | Trefle -> draw_image (make_image tref) c (l+2)
  | Coeur -> draw_image (make_image coeu) c (l+2)
  | Pique -> draw_image (make_image piqu) c (l+2)

let draw_carte ca =
  let (c,l) = current_point() in
  draw_haut (haut ca); draw_coul (c+12) l (coul ca); moveto c (l+14)

let draw_pile l = List.iter draw_carte (List.rev l); let (c,_) = current_point() in moveto (c+27) 0

(* TEST *)
(*
let () = Graphics.open_graph ""
let _ = draw_pile p''1

let () = Graphics.close_graph ()
*)
(* END TEST *)

let draw_jeu j = List.iter draw_pile j

let draw_reussite () = let jeu = List.map (fun l -> [l]) (faitjeu 32) in 
  Graphics.open_graph " 1000x1000";
  draw_jeu jeu ;
  let rec loop j = 
    let a = read_key () in
    if a = 'q' then Graphics.close_graph () else
      let k = reduc j in
      Graphics.clear_graph ();
      Graphics.moveto 0 0;
      draw_jeu k;
      loop k
    in loop jeu


(* TEST *)
let res = draw_reussite ()
(* END TEST *)
