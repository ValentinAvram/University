/**************************** analizador.l ***********************************/
/*Hecho por Valentín G. Avram Aenachioei*/

%{

#include "analizador.h"
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

/* Variables auxiliares para las cadenas de caracteres*/
char* auxCadena, *auxComentario;

/* Contadores para las cadenas de caracteres*/
int nCadena = 0, nComentario = 0, inicioComentario = 0;

%}
espacios [ \t\n]+
identificador [a-zA-ZáéíóúÁÉÍÓÚ](_?[a-zA-ZáéíóúÁÉÍÓÚ0-9])*
numeroReal [0-9]+(\.[0-9]*)?
numeroCientifico [0-9]+(\.[0-9]*)?[eE][+-]?[0-9]+
operadorAlfaNum [\|\|]
operadoresRelacionales [<|<=|>|>=|==|<>]
operadoresLogicos [#o|#y|#no]

/* Se usan dos estados para las cadenas de caracteres y los comentarios*/
%x CADENA COMENTARIO
%option yylineno

%%
{espacios} { /* Ignorar espacios en blanco */ ;}

{identificador} {   
                    /* Se pasa a minuscula la cadena para comprobar si es una palabra reservada*/
                    char *cadena = yytext; 
                    minuscula(cadena);
                    
                    /* Se comprueba si es una palabra reservada*/
                    if(checkReservada(cadena) == 0)
                    {
                        printf("Linea: %d. Identificador reconocido: %s \n",yylineno, yytext);
                    }
                    else
                    {
                        printf("Linea: %d. Palabra reservada reconocido: %s --> token %d\n",yylineno, cadena, checkReservada(cadena));
                    }
                }

{numeroReal} { /* Se reconoce un numero real */
    printf("Linea: %d. Numero real reconocido: %s \n",yylineno, yytext);}

{numeroCientifico} { /* Se reconoce un numero en notacion cientifica */
    printf("Linea: %d. Numero en notacion cientifica reconocido: %s \n",yylineno, yytext);}


"'" {
    /* Se empieza a reconoce una cadena de caracteres, se pasa al estado CADENA*/
	BEGIN(CADENA);
}

<CADENA>"'" {
    /* Se reconoce una cadena de caracteres*/
	printf("Linea: %d. Cadena reconocida: '%s' \n",yylineno, auxCadena);
    
    /* Se libera la memoria reservada para la cadena*/
    free(auxCadena);

    /* Se vuelve al estado inicial*/
	auxCadena = NULL;
	nCadena = 0;
	BEGIN(INITIAL);
}

<CADENA>"\\'" {
    /* Al reconocer \\' se sigue reconociendo la cadena de caracteres*/
	nCadena ++;
	auxCadena = realloc(auxCadena, nCadena * sizeof(char));
	auxCadena[nCadena - 1] = yytext[1];
}

<CADENA>.|\n {
    /* Se reconoce el resto de la cadena de caracteres al encontrar un salto de linea*/
	nCadena ++;
	auxCadena = realloc(auxCadena, nCadena * sizeof(char));
	auxCadena[nCadena - 1] = yytext[0];
}

":=" {printf("Linea: %d. Operador asignacion reconocido: %s \n",yylineno, yytext);}
"+" {printf("Linea: %d. Operador suma reconocido: %s \n",yylineno, yytext);}
"++" {printf("Linea: %d. Operador incremento reconocido: %s \n",yylineno, yytext);}
"-" {printf("Linea: %d. Operador resta reconocido: %s \n",yylineno, yytext);}
"--" {printf("Linea: %d. Operador decremento reconocido: %s \n",yylineno, yytext);}
"*" {printf("Linea: %d. Operador producto reconocido: %s \n",yylineno, yytext);}
"/" {printf("Linea: %d. Operador division reconocido: %s \n",yylineno, yytext);}
"//" {printf("Linea: %d. Operador division entera reconocido: %s \n",yylineno, yytext);}
"%" {printf("Linea: %d. Operador modulo reconocido: %s \n",yylineno, yytext);}
"^" {printf("Linea: %d. Operador potencia reconocido: %s \n",yylineno, yytext);}
"||" {printf("Linea: %d. Operador concatenacion reconocido: %s \n",yylineno, yytext);}
"<" {printf("Linea: %d. Operador menor que reconocido: %s \n",yylineno, yytext);}
"<=" {printf("Linea: %d. Operador menor o igual que reconocido: %s \n",yylineno, yytext);}
">" {printf("Linea: %d. Operador mayor que reconocido: %s \n",yylineno, yytext);}
">=" {printf("Linea: %d. Operador mayor o igual que reconocido: %s \n",yylineno, yytext);}
"==" {printf("Linea: %d. Operador igual que reconocido: %s \n",yylineno, yytext);}
"<>" {printf("Linea: %d. Operador distinto que reconocido: %s \n",yylineno, yytext);}
"#o" {printf("Linea: %d. Operador disyuncion logica reconocido: %s \n",yylineno, yytext);}
"#y" {printf("Linea: %d. Operador conjuncion logica reconocido: %s \n",yylineno, yytext);}
"#no" {printf("Linea: %d. Operador negacion logica reconocido: %s \n",yylineno, yytext);}


;   {printf("Linea: %d. Token reconocido: ;\n",yylineno);}
\(  {printf("Linea: %d. Token reconocido: (\n",yylineno);}
\)  {printf("Linea: %d. Token reconocido: )\n",yylineno);}

[\!\!].* { /* Se reconoce un comentario de una linea*/
    printf("Linea: %d. Comentario de una linea reconocido: ' %s '\n",yylineno,yytext);}

"<<" {
    /* Se empieza a reconocer un comentario de varias lineas, se pasa al estado COMENTARIO*/
    nComentario = 2;
    
    /* Se guarda la linea de inicio del comentario*/
    inicioComentario = yylineno;

    auxComentario = malloc(nComentario * sizeof(char));
    auxComentario[0] = yytext[0];
    auxComentario[1] = yytext[1];

    /* Se pasa al estado COMENTARIO*/
    BEGIN(COMENTARIO);
}

<COMENTARIO>">>" {
    nComentario += strlen(yytext);
    
    /* Se reserva memoria para el comentario*/
    auxComentario = realloc(auxComentario, nComentario * sizeof(char));
    auxComentario[nComentario - 2] = yytext[0];
    auxComentario[nComentario - 1] = yytext[1];
    auxComentario[nComentario] = '\0';

    /* Se reconoce el comentario de varias lineas*/
    printf("Linea %d a %d. Comentario de varias lineas reconocido: \n' %s '\n",inicioComentario, yylineno, auxComentario);

    /* Se libera la memoria reservada para el comentario*/
    free(auxComentario);
    auxComentario = NULL;
    nComentario = 0;

    /* Se vuelve al estado inicial*/
    BEGIN(INITIAL);
}

<COMENTARIO>"<<"(.|\n) {
    /* Se reconoce un comentario de varias lineas anidado. Se considera un error*/
    nComentario += strlen(yytext);
    printf("Linea: %d. Error. No se permiten los comentarios anidados.\n", yylineno);
}

<COMENTARIO>.|\n {
    /* Se reconoce el resto del comentario de varias lineas*/
    nComentario += strlen(yytext);
    auxComentario = realloc(auxComentario, nComentario * sizeof(char));
    auxComentario[nComentario - 1] = yytext[0];
}

. { /* Se reconoce un componente lexico erroneo*/
    printf("\nLinea: %d. Error. Componente lexico erroneo: %s \n\n",yylineno, yytext);}
%%

extern FILE *yyin;

int main(int argc, char *argv[])
{
    /*Se comprueba el numero de argumentos y la correcta apertura del archivo de entrada*/
    if (argc != 2)
    {
        printf("Error. Introduzca el nombre del archivo de entrada \n");
        exit(1);
    }

    yyin = fopen(argv[1], "r");
    if (yyin == NULL)
    {
        printf("No se puede abrir el archivo %s para lectura \n", argv[1]);
        exit(1);
    }

    yylex();

    /*Se cierra el archivo de entrada*/
    fclose(yyin);
    fclose(yyout);

    return 0;
}

int checkReservada(char* cadena)
{
    /* Comprobar que la cadena no es una palabra reservada*/
    char palabrasReservadas[][18] = 
    {
        "inicio",
        "fin",
        "leer",
        "escribir",
        "si",
        "entonces",
        "si_no",
        "fin_si",
        "mientras",
        "hacer",
        "fin_mientras",
        "repetir",
        "hasta_que",
        "para",
        "desde",
        "hasta",
        "paso",
        "fin_para",
    };  

    int i = 0;
    for(i; i < 18; i++)
    {
        if(strcmp(cadena, palabrasReservadas[i]) == 0)
        {
            /* Devolver el token de la palabra reservada*/
            return 257+i;
        }
    }

    return 0;
}

int minuscula(char* cadena)
{
    /* Pasar la cadena a minusculas*/
    int i = 0;
    for(i; i < strlen(cadena); i++)
    {
        cadena[i] = tolower(cadena[i]);
    }

    return 0;
}