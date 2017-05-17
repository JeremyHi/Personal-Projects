        global  main
        extern  puts
        extern  printf
        extern  atoi

        section  .text

main:
        cmp     rdi, 2              ; error checking
        jne     error1              ; jump if aguments != 1
        mov     rdi, [rsi+8]        ; move arguement into rdi
        call    atoi                ; convert to int
        test    rax, 3              ; test if mod 4
        jz      testTwo
        jmp     notLeapYear

testTwo:
        mov     rcx, 100            ; store 100
        push    rax                 ; store rax in stack
        div     rcx                 ; divide by 10
        test    rdx, rdx            ; check if rdx has a remainder
        jz      testThree
        jmp     isLeap

testThree:
        pop     rax
        mov     rcx, 400            ; store 400
        push    rax                 ; get rax again
        div     rcx                 ; devide the year by 400
        test    rdx, rdx            ; test for remainder
        jz      isLeap
        jmp     notLeapYear

notLeapYear:
        pop     rax                 
        mov     edi, nLeap
        call    puts
        jmp     done

error1:
        mov     edi, badArgs
        call    puts
        jmp     done 

isLeap:
        pop     rax
        mov     edi, iLeap
        call    puts
        jmp     done

done:
        ret

badArgs:
        db      "Requires exactly one argument", 5, 0

nLeap:
        db      "Not a leap year", 5, 0

iLeap:
        db      "Is a leap year", 5, 0

debug:
    db "rax: %d", 10, 0