%include "io.inc"

section .data
    msg_div     db "Введите a и b:", 0
    msg_q       db "Частное: ", 0
    msg_r       db "Остаток: ", 0

    msg_func    db "Введите a, x, b, c:", 0
    msg_y       db "y = ", 0

    msg_even    db "Введите число:", 0
    msg_res     db "Результат: ", 0

    msg_file    db "Введите размер файла (байты):", 0
    msg_kb      db "Килобайты: ", 0

    msg_rgb     db "Введите R G B:", 0
    msg_color   db "Цвет (HEX): ", 0

section .bss
    a  resd 1
    b  resd 1
    x  resd 1
    c  resd 1
    r  resd 1
    g  resd 1
    bl resd 1

section .text
global main

main:

; ---------- 5.1 Частное и остаток ----------
    PRINT_STRING msg_div
    NEWLINE
    GET_DEC 4, [a]
    GET_DEC 4, [b]

    mov eax, [a]
    cdq
    idiv dword [b]

    PRINT_STRING msg_q
    PRINT_DEC 4, eax
    NEWLINE
    PRINT_STRING msg_r
    PRINT_DEC 4, edx
    NEWLINE
    NEWLINE

; ---------- 5.2 y = a*x^2 + b*x + c ----------
    PRINT_STRING msg_func
    NEWLINE
    GET_DEC 4, [a]
    GET_DEC 4, [x]
    GET_DEC 4, [b]
    GET_DEC 4, [c]

    mov eax, [x]
    imul eax, eax
    imul eax, [a]

    mov ebx, [x]
    imul ebx, [b]
    add eax, ebx
    add eax, [c]

    PRINT_STRING msg_y
    PRINT_DEC 4, eax
    NEWLINE
    NEWLINE

; ---------- 5.3 Четное / нечетное ----------
    PRINT_STRING msg_even
    NEWLINE
    GET_DEC 4, eax

    and eax, 1
    xor eax, 1

    PRINT_STRING msg_res
    PRINT_DEC 4, eax
    NEWLINE
    NEWLINE

; ---------- 5.4 Байты → килобайты ----------
    PRINT_STRING msg_file
    NEWLINE
    GET_DEC 4, eax

    shr eax, 10

    PRINT_STRING msg_kb
    PRINT_DEC 4, eax
    NEWLINE
    NEWLINE

; ---------- 5.5 RGB → число ----------
    PRINT_STRING msg_rgb
    NEWLINE
    GET_DEC 4, [r]
    GET_DEC 4, [g]
    GET_DEC 4, [bl]

    mov eax, [r]
    shl eax, 16
    mov ebx, [g]
    shl ebx, 8
    add eax, ebx
    add eax, [bl]

    PRINT_STRING msg_color
    PRINT_HEX 4, eax
    NEWLINE

    xor eax, eax
    ret