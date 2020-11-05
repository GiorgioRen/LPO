(* funzione prod che moltiplica i valori di una lista *)
let rec prod = function
    [] -> 1
  | h::t -> h * (prod t);;
