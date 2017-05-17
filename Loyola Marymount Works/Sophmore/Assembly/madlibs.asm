		global main
		extern madlib_by_numbers
		extern printf
		extern strlen

		section .text

main:
		push 	rbx					; preserve rbx
		mov 	r12, [rsi + 8]		; store out template into r12
		sub 	rdi, 2				; args - 2
		mov 	r13, rdi			; put new # args into r13
		push 	rsi					; preserve rsi
		xor 	rsi, rsi			; set rsi to 0, start index
		mov 	rdi, r12			
		mov 	rsi, r13
		pop 	rdx					; remember pointer
		add 	rdx, 16				
		call 	madlib_by_numbers	; call our madlib function
		mov 	rsi, rax
		mov 	rdi, output 		; prepare for printing
		xor 	rax, rax	
		call 	printf 				; print our output
		pop 	rbx
		ret
output:
		db 		"%s", 5, 0