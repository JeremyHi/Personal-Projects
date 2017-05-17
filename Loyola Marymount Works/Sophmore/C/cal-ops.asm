	global gcd
	global add
	global subtraction
	section .text

gcd:
    cmp     rsi, 0                	; y == 0?
    jne     loop                  	; if not, go do work
    mov     rax, rdi              	; otherwise, just return x
    ret 	

loop:
    mov     rax, rdi 
    xor     rdx, rdx              	; rdx:rax <- x
    div     rsi                   	; rdx <- remainder
    mov     rdi, rsi              	; y
    mov     rsi, rdx              	; x % y
    call    gcd                   	; gcd(y, x % y)
    ret

add:
	mov		rax, rdi						
	mov 	rdx, rsi
	add 	rax, rdx
	ret

subtraction:
	mov		rax, rdi						
	mov 	rdx, rsi
	sub  	rax, rdx
	ret