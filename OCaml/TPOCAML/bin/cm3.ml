let rec mul a b = if a = 0 then 0 else b + mul (a-1) b

let rec exp a b = if b = 0 then 1 else a * exp a (b-1)

let _ = exp 2 4

let rec iterate fc (a, b) def = if b = 0 then def else fc a (iterate fc (a, b-1) def)

let exp a b = iterate (fun x y -> x*y) (a, b) 1

let _ = exp 2 4
