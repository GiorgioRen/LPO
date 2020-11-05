(* 1 *)
(* function 'prod': multiplies list entries and return result *)
let rec prod = function
  | [] -> 1
  | h::t -> h * (prod t);;

(* 2 *)
(* function 'member': il list 'l', true if 'e' exist, false otherwise *)
let rec member e = function
  | [] -> false
  | h::t -> if h = e then true else member e t;;

(* 3 [x] *) 
(* function 'insert': if element 'e' is not in list 'l', adds 'e' to 'l' *)
let rec insert e = function
  | [] -> []@[e]
  | h::t as l  -> if h = e then l else insert e t;;

let rec insert e l = function
  |
  | l as h::t -> if h = e then l else insert e t;;

(* 4 [x] *)
(* function 'odd': returns the list 'l' with only odd numbers *)
let rec odd = function
  | [] -> []
  | h::t -> if h % 2 = 0 then
