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

(* 3 *)
(* function 'insert': if element 'e' is not in list 'l', adds 'e' to 'l' *)
let rec insert e = function
  | [] -> [e]
  | h::t as l -> if h = e then l else h::insert e t;;

(* 4 *)
(* function 'odd': returns the list 'l' with only odd numbers *)
let rec odd = function
  | [] -> []
  | h::t -> if h mod 2 <> 0 then h::odd t else odd t;;


(* ---------------------------------------- *)

(* 5 *)
(* function 'ord_insert': return sorted list 'l' adding element 'e' if doesn't exist *)
(* TODO: *)
let rec sort l =
  match l with
    | [] -> []
    | h::t -> insert h (sort t)
  and insert e l =
  match l with
    | [] -> [e]
    | h::t -> if e <= h then e::l else h::insert e t;;

let rec ord_insert e = function
  | [] -> []
  | h::t as l -> if member e l then sort l else sort (insert e l);;


let rec ord_insert e = function
  | [] -> [e]
  | h::t as l -> if member e l then l else if e < h then e::l else ord_insert e t;;


(* 6 *)
(* function 'merge': sorts and merge two lists 'l1' 'l2' *)
let merge l1 l2 = function














(* *)
