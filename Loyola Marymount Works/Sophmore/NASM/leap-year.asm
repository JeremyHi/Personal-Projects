    global main
    extern puts
    section .text

main:
    mov rax, [year]
    cwd
    mov rcx, 400
    div rcx
    test rdx, rdx
    jz _is_leap

    mov rax, [year]
    cwd
    mov rcx, 100
    div rcx
    test rdx, rdx
    jz _no_leap

    mov rax, [year]
    test rax, 3
    jz _is_leap

year:
    dq      1986, 10, 0      

_no_leap:
    db      "This is NOT a leap year", 10, 0            ;print out is not a leap year
    jmp _after

_is_leap:
    db      "This is a leap year", 10, 0                ;print is a leap year
    jmp _after

_after:
    ret