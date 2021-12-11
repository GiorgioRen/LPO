(* Laboratorio di LPO: specifica e implementazione della semantica dinamica di un linguaggio *)

type var_id = Name of string;;

(* AST of expressions *)
type exp = Add of exp*exp | Mul of exp*exp | And of exp*exp | Eq of exp*exp | Pair of exp*exp | Fst of exp | Snd of exp | Sign of exp | Not of exp | Num of int | Bool of bool | Var of var_id 
(* added nodes *)
  | Neq of exp*exp | Range of exp*exp | Bounds of exp;;

(* AST of statements and sequence of statements, mutually recursive *)
type
  stmt = Assign of var_id*exp | Dec of var_id*exp | Print of exp | If of exp*stmt_seq | IfElse of exp*stmt_seq*stmt_seq
(* added node *)
  | For of var_id*exp*stmt_seq

and

  stmt_seq = SingleStmt of stmt | MoreStmt of stmt * stmt_seq;;

(* AST of programs *)
type prog = Prog of stmt_seq;;

(* values *)

(* examples
    Pair(Int 2,Bool false) is  <<2,false>>
    Pair(Int 2,Pair(Int 3,Bool true)) is <<2,<<3,true>>>>
    Range(1,5) is [1:5]
*)

type value = Int of int | Bool of bool | Pair of value*value 
  (* added values *)
  | Range of int*int;;

(* dynamic environments *)

type scope = (var_id * value) list;;
type dynamic_env = scope list;;

exception UndeclaredVariable of var_id;;

exception AlreadyDeclaredVariable of var_id;;

let empty_scope:scope = [];; 

let starting_env:dynamic_env = [empty_scope];; (* just the empty top-level scope *)

let enter_scope : dynamic_env -> dynamic_env = fun env -> empty_scope::env;; (* enters a new nested scope *)

let exit_scope : dynamic_env -> dynamic_env = function (* removes the innermost scope, only needed for the dynamic semantics *)
    _::env -> env
  | [] -> failwith "assertion error";; (* should never happen *)

(* variable lookup *)

(* resolve uses List.mem_assoc 
   examples:
   List.mem_assoc (Name "x") [(Name "x",Int 3);(Name "y",Bool false)]=true;;
   List.mem_assoc (Name "y") [(Name "x",Int 3);(Name "y",Bool false)]=true;;
   List.mem_assoc (Name "z") [(Name "x",Int 3);(Name "y",Bool false)]=false;;
*)

(* resolve : var_id -> dynamic_env -> scope *)

let rec resolve : var_id -> dynamic_env -> scope = fun id -> function
    scope::(env:dynamic_env) -> if(List.mem_assoc id scope) then scope else resolve id env
  | [] -> raise (UndeclaredVariable id);;

(* lookup uses List.assoc 
   examples:
   List.assoc (Name "x") [(Name "x",Int 3);(Name "y",Bool false)]=Int 3;;
   List.assoc (Name "y") [(Name "x",Int 3);(Name "y",Bool false)]=Bool false;;
   List.assoc (Name "z") [(Name "x",Int 3);(Name "y",Bool false)];; raises exception Not_found
*)

let lookup : var_id -> dynamic_env -> value = fun var_id env -> List.assoc var_id (resolve var_id env);;

(* variable declaration *)

(* example: dec x val env1 = env2 means that 'env2' is the new environment after declaring variable 'x' initialized with value 'val' in the environment 'env1' *)  
(* dec uses List.mem_assoc, see the examples above *)

let dec : var_id -> value -> dynamic_env -> dynamic_env = fun id vl -> function
    scope::env -> if(List.mem_assoc id scope) then raise (AlreadyDeclaredVariable id) else ((id,vl)::scope)::env
  | [] -> failwith "assertion error";; (* should never happen *)

(* variable update, only needed for the dynamic semantics *)
(* update uses List.mem_assoc, see the examples above *) 

let rec update : var_id -> value -> dynamic_env -> dynamic_env = fun id vl -> function
    scope::env -> if(List.mem_assoc id scope) then ((id,vl)::scope)::env else scope::update id vl env
  | [] -> raise (UndeclaredVariable id);;

(* dynamic semantics *)

(* dynamic errors *)

exception ExpectingTypeError of string;; (* dynamic conversion error *) 

(* auxiliary functions *)

(* dynamic conversion to int type *)
(* int : value -> int *)

let int = function
    Int i -> i |
    _ -> raise (ExpectingTypeError "int")

(* dynamic conversion to bool type *)
(* bool : value -> bool *)

let bool = function
    Bool b -> b |
    _ -> raise (ExpectingTypeError "bool")

(* pair : value -> value * value *)
(* dynamic conversion to product  type *)

let pair = function
    Pair (e1,e2) -> e1,e2 |
    _ -> raise (ExpectingTypeError "pair");;

(* range : value -> int * int *)
(* dynamic conversion to range  type *)

let range = function
    Range (i1,i2) -> i1,i2 |
    _ -> raise (ExpectingTypeError "range");;

(* implementation of fst and snd operators *)
(* fst : 'a * 'b -> 'a *)

let fst (v1,_) = v1;;

(* snd : 'a * 'b -> 'b *)

let snd (_,v2) = v2;;

(* implementation of the bounds operator *)
(* bounds : int * int -> value *)

let bounds (i1,i2) = Pair(Int i1,Int i2);;

(* auxiliary printing functions *)

(* conversion to string *)

(* to_string : value -> string *)

let rec to_string = function
    Int i -> string_of_int(i) 
  | Bool b -> string_of_bool(b) 
  | Pair(v1,v2) -> "<<" ^ to_string v1 ^ "," ^ to_string v2 ^ ">>"
  (* added value *)
  | Range(i1,i2) -> "[" ^ string_of_int(i1) ^ ":" ^ string_of_int(i2) ^ "]";; 

(* print : value -> unit *)

let rec print vl = print_string (to_string vl);;

(* println : value -> unit *)

let println vl = print_string (to_string vl ^ "\n");;

(* evExp : dynamic_env -> exp -> value *)
(* evExp env exp = val means that expressions 'exp' successfully evaluates to 'val' in the environment 'env' *)

let rec evExp env=function 
    Add(exp1,exp2) -> Int(int(evExp env exp1)+int(evExp env exp2))
  | Mul(exp1,exp2) -> Int(int(evExp env exp1)*int(evExp env exp2))
  | And(exp1,exp2) -> Bool(bool(evExp env exp1)&&bool(evExp env exp2))
  | Eq(exp1,exp2) -> Bool(evExp env exp1=evExp env exp2)
  | Pair(exp1,exp2) -> Pair(evExp env exp1,evExp env exp2)
  | Fst exp -> fst (pair (evExp env exp))
  | Snd exp -> snd (pair (evExp env exp))
  | Sign exp -> Int(-int(evExp env exp))
  | Not exp -> Bool(not (bool(evExp env exp)))
  | Num i -> Int i
  | Bool b -> Bool b
  | Var id -> lookup id env
  (* new nodes *)
  | Neq(exp1,exp2) -> Bool(evExp env exp1!=evExp env exp2)
  | Range(exp1,exp2) -> Range(int(evExp env exp1),int(evExp env exp2))
  | Bounds exp -> bounds (range(evExp env exp));;


(* mutually recursive
   evStmt : dynamic_env -> stmt -> dynamic_env
   evStmtSeq : dynamic_env -> stmt_seq -> dynamic_env
*)

(* evStmt env1 st = env2 means that the execution of statement 'st' successfully returns the new environment 'env2' *)
(* evStmtSeq env1 stSeq = env2 means that the execution of statement sequence 'stSeq' successfully returns the new environment 'env2' *)
(* evStmt and evStmtSeq can possibly write on the standard output *)

let rec evStmt env=function
    Assign(id,exp) -> update id (evExp env exp) env
  | Dec(id,exp) -> dec id (evExp env exp) env
  | Print exp -> let _=println (evExp env exp) in env
  | If(exp,stmt_seq) ->
      if bool(evExp env exp) then  
        let env2=enter_scope env in exit_scope(evStmtSeq env2 stmt_seq) (* note the difference with the static semantics *)
      else env
  | IfElse(exp,stmt_seq1,stmt_seq2) ->
      let env2=enter_scope env in
        if bool(evExp env exp) then  
          exit_scope(evStmtSeq env2 stmt_seq1) (* note the difference with the static semantics *)
        else 
          exit_scope(evStmtSeq env2 stmt_seq2) (* note the difference with the static semantics *)
  (* new node *)
  | For(id,exp,stmt_seq) ->
      (* funzione ausiliaria for_each: for_each i1 i2 env esegue stmt_seq per tutti i valori di id compresi tra i1 (incluso) e i2 (escluso) *)
      let rec for_each : int * int -> dynamic_env -> dynamic_env = fun (start,finish) for_env ->
        if start=finish then for_env (* the range is empty, no loop to execute *) 
        else 
          let env2=enter_scope(update id (Int start) for_env) in (* updates the variable id of the 'for' and enters a new nested scope for executing the block stmt_seq *)
          let env3=exit_scope(evStmtSeq env2 stmt_seq) (* executes the block stmt_seq and exits from its scope *)
          and start2=if start<finish then start+1 else start-1 (* determines the next value start2 of the variable id of the 'for' *)
          in for_each (start2,finish) env3 
          (* fine dichiarazione funzione ausiliaria for_each *)
      in
      let r=range(evExp env exp) and env2=dec id (Int 0) (enter_scope env) (* the variable 'id' of the for statement is declared in a nested scope *) 
      in exit_scope (for_each r env2) (* executes the block 'stmt_seq' for each value of 'id' in range 'r', every time in a nested scope *) 

and

  evStmtSeq env=function 
    SingleStmt stmt -> evStmt env stmt
  | MoreStmt(stmt,stmt_seq) -> evStmtSeq (evStmt env stmt) stmt_seq;;

(* evProg : prog -> unit *)
(* evProg p = () means that program 'p' has been executed successfully, by possibly writing on the standard output *)

let evProg = function Prog stmt_seq -> let _=evStmtSeq starting_env stmt_seq in ();;

(* a test *)

let stmt1=Dec(Name "x",Num 0);;

let stmt2=For(Name "x",Range(Num 10,Num 20),MoreStmt(Assign(Name "x",Add(Var(Name "x"),Num 2)),MoreStmt (Print(Var(Name "x")),MoreStmt(Dec(Name "x",Bool true),SingleStmt(Print(And(Var(Name "x"),Bool true)))))));;

let stmt3=Print(Add(Var(Name "x"),Num 1));;

let prog1=Prog(MoreStmt(stmt1,MoreStmt(stmt2,SingleStmt stmt3)));;

evProg prog1;;
