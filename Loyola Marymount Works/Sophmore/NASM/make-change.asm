        global main
        extern printf
        extern atoi     
        section .text

main:
        push rbx                        

        mov rdi, [rsi + 8]              ;taking in argument (8 bits)
        call atoi                       ;convert (rdi) to int to (rax)
calculate:
        mov rsi, 25
        xor rdx, rdx                    ;set to 0 to make sure its clear
        div rsi                         ;(rax) contains quotient and (rdx) the remainder
        mov r12, rdx                    ;store rdx as a presevered statement
        mov rsi, rax                    ;move quotient (rax) into rsi to print
        mov rdi, complete_Q             ;test to print the quotient
        call printf                     ;prints the output
        
        xor rdx, rdx
        mov rax, r12                    ;move the remainder into r12
        mov rsi, 10
        div rsi
        mov r12, rdx
        mov rsi, rax                    ;move quotient (rax) into rsi to print
        mov rdi, complete_D             ;test to print the quotient
        call printf

        xor rdx, rdx
        mov rax, r12                    ;move the remainder into r12
        mov rsi, 5
        div rsi
        mov r12, rdx
        mov rsi, rax                    ;move quotient (rax) into rsi to print
        mov rdi, complete_N             ;test to print the quotient
        call printf

        xor rdx, rdx
        mov rax, r12                    ;move the remainder into r12
        mov rsi, 1
        div rsi
        mov r12, rdx
        mov rsi, rax                    ;move quotient (rax) into rsi to print
        mov rdi, complete_P             ;test to print the quotient
        call printf

        pop rbx
        ret

complete_Q:
        db "Your program needs %d quarters.", 10, 0
complete_D:
        db "Your program needs %d dimes.", 10, 0
complete_N:
        db "Your program needs %d nickels.", 10, 0
complete_P:
        db "Your program needs %d pennies.", 10, 0