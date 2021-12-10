(* 1 *)
(* function 'prod': multiplies list entries and return result *)
let rec prod = function
  | h::t -> t * h
  | _ -> 1;;


(* 2 *)
(* function 'member': il list 'l', true if 'e' exist, false otherwise *)
let rec member e = function
  | h::t -> if e=h || member e t
  | _ -> false;;


(* 3 *)
(* function 'insert': if element 'e' is not in list 'l', adds 'e' to 'l' *)
let rec insert e = function
  | h::t as l -> if h = e then l else h::insert e t
  | _ -> [e];;


(* 4 *)
(* function 'odd': returns the list 'l' with only odd numbers *)
let rec odd = function
  | [] -> []
  | h::t -> if h mod 2 <> 0 then h::odd t else odd t;;


(* 5 *)
(* function 'ord_insert': return sorted list 'l' adding element 'e' if doesn't exist *)
let rec ord_insert el = function
    hd::tl as l -> if el < hd then el::l else if el=hd then l else hd::ord_insert el tl
  | _ -> [el];;


(* 6 *)
(* function 'merge': sorts and merge two lists 'l1' 'l2' *)
let rec merge = function
    hd1::tl1 as l1,(h2::tl2 as l2) -> if hd1<h2 then hd1::merge (tl1,l2) else if hd1=h2 then hd1::merge (tl1,tl2) else h2::merge (l1,tl2)
  | [],l -> l
  | l,_ -> l;;


(***)
(* 7 solution *)
(* function 'curried_merge' *)
let rec curried_merge l1 l2 = match l1,l2 with
    hd1::tl1 as l1,(hd2::tl2 as l2) -> if hd1<hd2 then hd1::merge (tl1,l2) else if hd1=hd2 then hd1::merge (tl1,tl2) else hd2::merge (l1,tl2)
  | [],l -> l
  | l,_ -> l;;


(* tests from teacher *)

prod [] = 1;;
prod [1;2;3;4] = 24;;

member 4 [1;2;3;4];;
not (member 4 [1;2;3]);;

insert 2 [0;1] = [0;1;2];;
insert 2 [0;2;1] = [0;2;1];;

odd [1] = [1];;
odd [1;2;3;4;5] = [1;3;5];;

ord_insert 1 [] = [1];;
ord_insert 1 [-2;0;4]=[-2;0;1;4];;
ord_insert (-5) [-2;0;4]=[-5;-2;0;4];;
ord_insert 10 [-2;0;4]=[-2;0;4;10];;
ord_insert 1 [-2;0;1;4] = [-2;0;1;4];;

merge ([4;5;6],[1;2;3]) = [1;2;3;4;5;6];;
merge ([1;3;5],[2;4;6]) = [1;2;3;4;5;6];;
merge ([1;2;3],[1;2;3]) = [1;2;3];;
curried_merge [4;5;6] [1;2;3] = [1;2;3;4;5;6];;
curried_merge [1;3;5] [2;4;6] = [1;2;3;4;5;6];;
curried_merge [1;2;3] [1;2;3] = [1;2;3];;