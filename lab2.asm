%include "io64.inc"

section .data
    msg_div     db "Введите a и b:", 0
    msg_func    db "Введите a, x, b, c:", 0
    msg_even    db "Введите число:", 0
    msg_file    db "Введите размер файла (байты):", 0
    msg_rgb     db "Введите R G B:", 0

section .bss
    a resq 1
    b resq 1
    x resq 1
    c resq 1
    r resq 1
    g resq 1
    bl resq 1

section .text
global main

main:

; ---------- 5.1 Частное и остаток ----------
    PRINT_STRING msg_div
    NEWLINE
    GET_DEC 8, [a]
    GET_DEC 8, [b]

    mov rax, [a]
    cqo
    idiv qword [b]

    PRINT_STRING "Частное: "
    PRINT_DEC 8, rax
    NEWLINE
    PRINT_STRING "Остаток: "
    PRINT_DEC 8, rdx
    NEWLINE
    NEWLINE

; ---------- 5.2 y = ax^2 + bx + c ----------
    PRINT_STRING msg_func
    NEWLINE
    GET_DEC 8, [a]
    GET_DEC 8, [x]
    GET_DEC 8, [b]
    GET_DEC 8, [c]

    mov rax, [x]
    imul rax, rax        ; x^2
    imul rax, [a]        ; a*x^2

    mov rbx, [x]
    imul rbx, [b]        ; b*x
    add rax, rbx
    add rax, [c]

    PRINT_STRING "y = "
    PRINT_DEC 8, rax
    NEWLINE
    NEWLINE

; ---------- 5.3 Четное / нечетное ----------
    PRINT_STRING msg_even
    NEWLINE
    GET_DEC 8, rax

    and rax, 1
    xor rax, 1           ; 1 если четное, 0 если нет

    PRINT_STRING "Результат: "
    PRINT_DEC 1, rax
    NEWLINE
    NEWLINE

; ---------- 5.4 Байты в килобайты ----------
    PRINT_STRING msg_file
    NEWLINE
    GET_DEC 8, rax

    shr rax, 10          ; деление на 1024

    PRINT_STRING "Килобайты: "
    PRINT_DEC 8, rax
    NEWLINE
    NEWLINE

; ---------- 5.5 RGB -> число ----------
    PRINT_STRING msg_rgb
    NEWLINE
    GET_DEC 8, [r]
    GET_DEC 8, [g]
    GET_DEC 8, [bl]

    mov rax, [r]
    shl rax, 16
    mov rbx, [g]
    shl rbx, 8
    add rax, rbx
    add rax, [bl]

    PRINT_STRING "Цвет: "
    PRINT_HEX 8, rax
    NEWLINE

    xor rax, rax
    ret