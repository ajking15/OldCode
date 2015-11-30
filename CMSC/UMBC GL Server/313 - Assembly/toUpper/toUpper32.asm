TITLE toUpper32						(main.asm)

; Description:
; Receives a string and a character from console, 
; Searches in the string and changes the character into uppercase 
; Writes the new string into console

; Revision date:
; 08/08/08

INCLUDE Irvine32.inc
BUFLEN EQU 256

.data
enterStrMessage BYTE "Please Enter the String (max length 1
.28 char, ends with Enter):",0dh,0ah,0
enterCharMessage BYTE "Please Enter the Char to change to upper:",0dh,0ah,0
resultMessage BYTE "Result String:",0dh,0ah,0
newLine BYTE 0dh,0ah,0

buf BYTE BUFLEN DUP(0)
newstr BYTE BUFLEN DUP (0)

rlen DWORD ?

.code
main PROC
	call Clrscr

	;write first message:
	mov	 edx,OFFSET enterStrMessage
	call  WriteString

	;Read the string:
	mov  ecx,BUFLEN
     mov  edx, OFFSET buf
	call ReadString

        ; Loop for upper case conversion
        ; assuming rlen > 0
        ;

	mov     [rlen], eax
L1_init:
        mov     ecx, [rlen]             ; initialize count
        mov     esi, OFFSET buf                ; point to start of buffer
        mov     edi, OFFSET newstr             ; point to start of new string

L1_top:
        mov     al, [esi]               ; get a character
        inc     esi                     ; update source pointer
        cmp     al, 'a'                 ; less than 'a'?
        jb      L1_cont 
        cmp     al, 'z'                 ; more than 'z'?
        ja      L1_cont
        and     al, 11011111b           ; convert to uppercase

L1_cont:
        mov     [edi], al               ; store char in new string
        inc     edi                     ; update dest pointer
        dec     ecx                     ; update char count
        jnz     L1_top                  ; loop to top if more chars
L1_end:

     ;Write the Result String
	mov	edx,OFFSET resultMessage
	call WriteString
	mov	edx,OFFSET newstr
	call WriteString

	mov	edx,OFFSET newLine
	call WriteString

	exit
main ENDP

END main