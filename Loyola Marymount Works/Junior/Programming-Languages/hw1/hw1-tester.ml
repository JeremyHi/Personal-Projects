(* ==================== HERE BE DRAGONS ====================

   This file define a simple testing framework that you can use to
   test your programs. You don't need to understand any of this code
   to complete your homework.

 *)

(* This declares a new kind of exception, called TODO.  I've put these
   as placeholders below.
 *)

exception TODO;;

(* load the homework *)
#use "hw1.ml";;
  
(* general-purpose test function *)  
let test nm f i o = 
  print_string (nm ^ " ... ");
  let msg =
    try 
      if f i = o
      then "OK"
      else "FAILED"
    with
    | TODO -> "TODO"
    | e    -> "ERROR: " ^ Printexc.to_string e
  in                
  print_string (msg ^ "\n");;

(* Problem 1: change *)
test "change 37" change 37 [1; 1; 0; 2];;

(* Problem 2: is_perm_123 *)
test "is_perm_123 [4]" is_perm_123 [4] false;;
  
(* Problem 3: string_of_digit *)
test "string_of_digit 5" string_of_digit 5 "5";;
  
(* Problem 4: my_string_of_int *)
test "my_string_of_int 1234" my_string_of_int 1234 "1234";;

(* Problem 5: any *)
test "any [false;false;true;false]" any [false;false;true;false] true;;
test "any [false]" any [false] false;;
    
(* Problem 6: all *)
test "all [true;true;true]" all [true;true;true] true;;
test "all [true;true;false;true]" all [true;true;false;true] false;;
  
(* Problem 7: add_last_2 *)
test "add_last_2 [1;2;3]" add_last_2 [1;2;3] [1;2;3;5];;

(* Problem 8: fibs *)
test "fibs 5" fibs 5 [0;1;1;2;3];;
