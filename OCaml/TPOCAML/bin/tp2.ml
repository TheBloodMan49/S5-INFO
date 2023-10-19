(* TP2. Mettez vos noms ici. *)

(* Compléter *)
let rec pair n = if n = 0 then true else pair (pred (pred n))
and impair n = not (pair n)

(* TEST *)
let res = pair 12 (* true *)

let res = impair 12

(* false *)
(* END TEST *)

(* Compléter *)
let rec sigma (a, b) = if a > b then 0 else a + sigma (a+1, b)

(* TEST *)
let res = sigma (-2, 4)

(* 7 *)
(* END TEST *)

(* Compléter *)
let rec sigma2 f (a, b) = if a > b then 0 else f a + sigma2 f (a+1, b)

(* TEST *)
let res = sigma2 (fun x -> 2 * x) (-2, 4)

(* 14 *)
(* END TEST *)

(* Compléter *)
let rec sigma3 (f, fc) i acc (a, b) = if a <= b then fc (f a) (sigma3 (f, fc) i acc (a+i, b)) else acc

(* TEST *)
let res = sigma3 ((fun x -> 2 * x), fun v acc -> v + acc) 2 0 (2, 6)

(* 24 *)
(* END TEST *)

(* TEST *)
let res = sigma3 ((fun x -> x * x), fun x acc -> x :: acc) 2 [] (0, 10)

(* [0; 4; 16; 36; 64; 100] *)
(* END TEST *)

(* Compléter *)
let rec sigma4 (f, fc) (p, fi) acc a = if not (p a) then fc (f a) (sigma4 (f, fc) (p,fi) acc (fi a)) else acc

(* TEST *)
let res =
  sigma4
    ((fun x -> 2 * x), fun v acc -> v + acc)
    ((fun v -> v > 6), fun v -> v + 2)
    0
    2

(* 24 *)
(* END TEST *)

(* Compléter *)
let cum f (a, b) dx = sigma4 (f, (fun x acc -> x+.acc)) ((fun x -> x > b), (fun x -> x+.dx)) 0. a

(* TEST *)
let res = cum (fun x -> 2. *. x) (0.2, 0.7) 0.2

(* 2.4 *)
(* END TEST *)

(* Compléter *)
let integre f (a, b, dx) = cum f (a, b) dx *. dx

(* TEST *)
let res = integre (fun x -> 1. /. x) (1., 2., 0.001)

(* 0.693897243059959257 *)
(* END TEST *)

(* Compléter *)
let rec maxi f (a, b) p = if b-.a < p then f a else
  let x = a+.0.3*.(b-.a) in
  let y = a+.0.6*.(b-.a) in
  if f x < f y then maxi f (x, b) p else
  maxi f (a, y) p

(* TEST *)
let res = maxi (fun x -> 1. -. (x *. x)) (0., 2.) 0.0001
(* 1. *)
(* END TEST *)
