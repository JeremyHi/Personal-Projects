(* Name: Jeremy Hitchcock
   Email: jhitchco@lion.lmu.edu
   Student ID: 962880074

   Others With Whom I Discussed Things: Tyler Colson

   Other Resources I Consulted: http://stackoverflow.com/questions/* (many extensions)
   
*)

exception TODO;;

(* Part 1: Dictionaries.

   A dictionary (sometimes also called a map or key-value store) is a
   data structure that associates keys with values (or maps keys to
   values). A dictionary supports two main operations: put, which adds
   a new key-value pair to a given dictionary; and get, which looks up
   the value associated with a given key in a given dictionary.  If
   the given key is already associated with some value in the
   dictionary, then put should replace the old key-value pair with the
   new one.  To handle the case when the given key is not mapped to
   some value in the dictionary, get will return an option,
   i.e. either the value None or the value Some v, where v is the
   value associated with the given key in the dictionary.

   Our implementation of a dictionary is as an "association list",
   i.e. a list of pairs. Implement put, and get for association lists.
   As an example of how this representation of dictionaries works, the
   dictionary that maps "hello" to 5 and has no other entries is
   represented as [("hello",5)].
 *)

type ('k, 'v) dict = ('k * 'v) list;;

(* Problem 1:

   Implement get and put for dict.

   Example:
     (let kvs = put "hello" 5 [] in
      let kvs = put "hi" 7 kvs in
      [get "hello" kvs; get "hi" kvs; get "hey" kvs])
     = [Some 5; Some 7; None]
 *)

let rec put k v d = ((k,v)::d);;

let rec get (k : 'k) (d : ('k,'v) dict) : 'v option =
  match d with
  | [] -> None
  | (key, value)::rest -> if k = key then Some value else get k rest
;;

(* Part 2: A simple calculator language.

   In this part we will use OCaml's built-in data types to represent a simple calculator language of integers and booleans. This language is similar to a small subset of OCaml itself. Below is the abstract syntax representation of the language:
 *)

(* There are 5 operators in the language, corresponding to OCaml's +, -, *, =, and > operators.
 *)

type op = Plus | Minus | Times | Eq | Gt;;

(* The expressions consist of integer literals (..., -2, -1, 0, 1, 2, ...), boolean literals (true, false), variables (with the same naming convention as OCaml's variables), binary operations, and conditionals. Conditionals contain three expression fields: the first is the condition, the second is the "then" branch, and the third is the "else" branch.
 
   Examples:

   |--------------+------------------------------------------------------|
   | Expression   | Representation                                       |
   |==============+======================================================|
   | 1 + 2 * 3    | BinOp (Int 1, Plus, BinOp (Int 2, Times, Int 3))     |
   |--------------+------------------------------------------------------|
   | 2 * x + y    | BinOp (BinOp (Int 2, Times, Var "x"), Plus, Var "y") |
   |--------------+------------------------------------------------------|
   | if x = y     | IfThenElse (BinOp (Var "x", Eq, Var "y"),            |
   | then true    |             Bool true,                               |
   | else z       |             Var "z")                                 |
   |--------------+------------------------------------------------------|
 *)
  
type expr = Int of int
          | Bool of bool
          | Var of string
          | BinOp of (expr * op * expr)
          | IfThenElse of (expr * expr * expr);;

(* A program is zero or more variable declarations, followed by an expression.  A declaration consists of a string (the variable name), an expression (the definition of the variable), and the rest of the program. *)
  
type pgm = Decl of (string * expr * pgm)
         | Expr of expr;;

(* Problem 2: detect references to undeclared variables.

   This problem is in two parts. In the first part, you will return a
   list of all the variables that are referenced in an expression. It
   is ok if a name occurs multiple times in the resulting list.

   Examples:
     var_names (Int 5) = []
     var_names (BinOp (Var "x", Plus, Var "y")) = ["x";"y"]
     var_names (IfThenElse (Var "x", Var "y", Var "x")) = ["x";"y";"x"]

   In the second part, you will the return a list of variable names
   that are undeclared references in a program. You should use your
   implementation of var_names. Note that for a declaration, the name
   is considered declared in the rest of the program, but not in its
   definition. 

   Note: do not use (e1 != e2). Use (e1 <> e2) or (not (e1 = e2))
   instead.

   Examples:
     undeclared_names (Decl ("x", Int 5, Expr (Var "x"))) = []
     undeclared_names (Decl ("x", Int 5, Expr (Var "y"))) = ["y"]
     undeclared_names (Decl ("x", Var "x", Expr (Var "x"))) = ["x"]
*)

let rec var_names (e : expr) : string list =
  match e with
    | BinOp (exp,op,exp2)      -> let x = var_names exp in x@var_names exp2
    | IfThenElse (exp,exp1,exp2) -> let x = var_names exp in 
                                  let y = var_names exp1 in x@(y@var_names exp2)
    | Var x                 -> x::[]
    | _                     -> []
;;

let rec undeclared_names (p : pgm) : string list =  
  match p with
  | Expr x             -> var_names x
  | Decl (str,exp,prg) -> (var_names exp)@(List.filter (fun x -> x <> str) (undeclared_names prg))
;;

(* Problem 3: Convert expressions and programs into equivalent OCaml
   code (as a string).

   Again, this is in two parts. The first part will convert
   expressions to OCaml. Always wrap BinOp expressions in parentheses
   to avoid precedence issues.

   Examples:
     ocaml_of_expr (BinOp (Var "x", Plus, Int 5)) = "(x + 5)"
     ocaml_of_expr (IfThenElse (Bool true, Var "x", Var "y")) = "if true then x else y"

   You may use the following built-in functions: string_of_int,
   string_of_bool, and the string concatenation operator (^).

   The second part converts programs to OCaml. Use local let
   expressions for declarations.

   Example:
     ocaml_of_pgm (Decl ("x", Int 5, Expr (Var "x"))) = "let x = 5 in x"

 *)
  
let rec ocaml_of_expr e =
  match e with
  | BinOp (exp,op,exp2)        -> "(" ^ ocaml_of_expr exp ^ match op with
                                                    | Plus  -> " + "
                                                    | Minus -> " - "
                                                    | Times -> " * "
                                                    | Eq    -> " = "
                                                    | Gt    -> " > ";
                                                    ^ ocaml_of_expr exp2 ^ ")"
  | IfThenElse (exp,exp1,exp2) -> "If " ^ ocaml_of_expr exp ^ " then " ^                               ocaml_of_expr exp1 ^ " else " ^ ocaml_of_expr exp2
  | Int x                      -> string_of_int x
  | Bool x                     -> string_of_bool x
  | Var x                      -> x
;;
                                
let rec ocaml_of_pgm p =
  match p with
    | Decl (str,exp,prg) -> "let " ^ str ^ " = " ^ ocaml_of_expr exp ^ " in " ^ ocaml_of_pgm prg
    | Expr x -> ocaml_of_expr x
;;

(* Problem 4: Type-checking.

   In this problem you will implement a type checker for our language.
   To do this, we need to define the possible types of expressions, of
   which there are two: integers and booleans. The user-defined type
   "ty" defines these two cases. We also need to define a type
   environment, which keeps tracks of the types of declared
   variables. For this, we will use a dictionary whose keys are
   strings (variable names) and whose values are types. 

   In the first part, you will type check expressions. Since type
   checking may fail, tc_expr will return a (ty option). In case of a
   type error, tc_expr should return None.

   The type rules are as follows:

   1) The type of integer literal expressions is TyInt.
   2) The type of boolean literal expressions is TyBool.
   3) The type of a variable is given by the type environment.
      It is a type error if there is no type assigned to the
      variable in the environment.
   4) The type of addition, subtraction, and multiplication 
      expressions is TyInt. The type of the comparison expressions 
      is TyBool. All binary operators require their operands type
      check and have the type TyInt.
   5) The type of a conditional expression is the same as the type of its
      branches. Both the "then" and "else" branch expressions must type
      check, and must have the same type. The condition expression must have
      type TyBool.

   Examples:
     tc_expr [] (Int 5) = Some TyInt
     tc_expr [("x",TyInt)] (Var "x") = Some TyInt
     tc_expr [] (Var "x") = None
     tc_expr [("x",TyInt)] (IfThenElse (BinOp (Int 4, Eq, Var "x"), Var "x", Int 5)) = Some TyInt
 *)
  
type ty = TyInt | TyBool;;
type type_env = (string, ty) dict;;

let rec tc_expr (tyEnv : type_env) (e : expr) : ty option =
  match e with
  | Int x                      -> Some TyInt
  | Bool x                     -> Some TyBool
  | Var x                      -> get x tyEnv
  | BinOp (exp,op,exp2)        -> if (tc_expr tyEnv exp) = (tc_expr tyEnv exp2) then (match op with
                | Plus  -> Some TyInt
                | Minus -> Some TyInt
                | Times -> Some TyInt
                | Eq    -> Some TyBool
                | Gt    -> Some TyBool) else None
  | IfThenElse (cnd,thn,els) -> (match (tc_expr tyEnv cnd) with
                                | Some TyBool -> (match tc_expr tyEnv thn,tc_expr tyEnv els with
                                                  | Some TyBool,Some TyBool -> Some TyBool
                                                  | Some TyInt,Some TyInt -> Some TyInt
                                                  | _,_  -> None)
                                | _ -> None)
;;

(* 
  For part two, implement type checking of programs. Programs themselves have no type, so the result is just a boolean.

  To type check an expression program, simply type check the expression. To type check a declaration, first type check the definition of the variable, then record the type of the variable in the type environment, and finally type check the remainder of the program under the new type environment. If either check fails, the program does not type check.

   Examples:
     tc_pgm [] (Decl ("x", Int 5, Expr (Var "x"))) = true
     tc_pgm [] (Decl ("x", Int 5, Expr (Var "y"))) = false
 *)
            
let rec tc_pgm (tyEnv : type_env) (pgm : pgm) : bool =
  match pgm with
  | Expr exp -> (match tc_expr tyEnv exp with
                | Some TyInt   -> true
                | Some TyBool  -> true
                | _            -> false)
  | Decl (str,exp,prg) -> (match (tc_expr tyEnv exp) with
                          | Some TyInt  -> tc_pgm (put str (TyInt) tyEnv) prg
                          | Some TyBool -> tc_pgm (put str (TyBool) tyEnv) prg
                          | _           -> false)
;;

(* Problem 5.

   Evaluation computes an expression and returns its *value*, which
   for our language is either an integer or a boolean. For this
   problem you will implement evaluation of expressions and programs.
   
   We will introduce a new kind of environment, which maps variables
   to their values. 

   Evaluation may fail, in one of several possible ways:
   - a variable has no value defined in the environment
   - one of the operands of a binary operator evaluates to a boolean
   - the condition expression of an if-then-else evaluates to an integer

   We report errors using the option type. In each case, eval_expr
   should return None.

   Example:
     eval_expr [] (BinOp (Int 5, Plus, Int 7)) = Some (VInt 12)
     eval_expr [("x", VBool true)] (IfThenElse (Var "x", Int 1, Int 0)) = Some (VInt 1)

 *)


  
type value = VInt of int
           | VBool of bool;;
type env = (string,value) dict;;

let rec eval_expr (env : env) (e : expr) : value option =
  match e with
  | Int x -> Some (VInt x)
  | Bool x -> Some (VBool x)
  | Var x -> get x env
  | BinOp (exp,op,exp2) -> (match eval_expr env exp, eval_expr env exp2 with
                            | Some (VInt x), Some (VInt y) -> (match op with
                                                              | Plus -> Some (VInt (x+y))
                                                              | Minus -> Some (VInt (x-y))
                                                              | Times -> Some (VInt (x*y))
                                                              | Eq -> Some (VBool (x=y))
                                                              | Gt -> Some (VBool (x>y)))
                            | _,_ -> None)
  | IfThenElse (cnd,thn,els) -> (match eval_expr env cnd with
                                | Some (VBool cnd) -> (match eval_expr env thn, eval_expr env els with
                                                      | Some (VInt thn), Some (VInt els) -> if cnd then Some (VInt thn) else Some (VInt els)
                                                      | _,_  -> None)
                                | _ -> None)
;;

(* Next, extend evaluation to programs. If evaluation succeeds, the
   result will be contain the final environment paired the result of
   the last expression. Otherwise, return None.

   Note: a program should type check if and only if it evaluates
   without an error.

   Examples:
     eval_pgm [] (Decl ("x", Int 5, Expr (BinOp (Var "x", Plus, Var "x")))) = Some ([("x", VInt 5)], VInt 10)

 *)

let rec eval_pgm (env : env) (p : pgm) : (env * value) option =
  match p with
  | Expr x -> (match (eval_expr env x) with
              | Some (VInt x) -> Some (env, VInt x)
              | Some (VBool x) -> Some (env, VBool x)
              | _ -> None)
  | Decl (str,exp,pgm) -> (match eval_expr env exp with
                          | Some (VInt x) -> eval_pgm (put str (VInt x) []) pgm
                          | Some (VBool x) -> eval_pgm (put str (VBool x) []) pgm
                          | _ -> None)
;;