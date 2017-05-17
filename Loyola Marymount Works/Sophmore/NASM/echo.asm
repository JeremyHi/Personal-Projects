        extern  printf
        extern  strlen
        extern  reverse_range_in_place
        section .text
main:
        push    rbx
        dec     rdi

loop:
        push    rdi                     ; save registers that puts uses - argc
        push    rsi                     ; argv

        add     rsi, 8
        mov     rdi, [rsi]              ; the argument string to display
        push    rdi
        call    strlen
        pop     rdi
        dec     rax
        mov     rdx, rax                ;length of string argv

        xor     rsi,  rsi               ;beinging of string
        push    rdi                     ;rdi = string
        call    reverse_range_in_place
        pop     rdi

        mov     rsi, rdi
        mov     rdi, complete
        xor     rax, rax
        call    printf                  ; print it

        pop     rsi                     ; restore registers puts used
        pop     rdi

        add     rsi, 8                  ; point to next argument
        dec     rdi                     ; count down
        jnz     loop                    ; if not done counting keep going

        pop     rbx

        ret

complete:
        db      "%s"