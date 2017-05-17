	global main
	section .text
main:
	push 	rsi
	mov     rax, 1                  ; system call 1 is write
    mov     rdi, 1                  ; file handle 1 is stdout
    mov     rsi, message            ; address of string to output
    mov     rdx, 17                 ; number of bytes
    syscall

    pop 	rsi						;use rsi for the second argument
    mov		rax, 86					;86 = code for link
    mov rdi, [rsi + 8]				;link 0 is oldname of argument
    ;mov rsi, [rsi + 16]				;link 1 is newname of argument
    syscall
    ret							

message:
	db		"You done it.", 10