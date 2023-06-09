/**************************** figura.l ***********************************/
/*Hecho por Valentín G. Avram Aenachioei*/
/***** Zona de las declaraciones o definiciones *****/
%{
int num_triangulos=0;
int num_cuadrados=0;
int num_circulos=0;
%}
/* Definiciones regulares */

letra		[a-zA-Z]
digito		[0-9]
coordenada  \({digito}+,{digito}+[ ]{digito}+,{digito}+\)

/***** Zona de las reglas *****/
%%
"triangulo"[ ]{coordenada}[ ]?{coordenada}[ ]?{coordenada}	{
    /*Se reconoce la figura triangulo y se actualiza el contador de triangulos.*/
    printf("%s : Se reconoce como triangulo.\n",yytext); ++num_triangulos;
    }

"circulo"[ ]{coordenada}[ ]?{digito},{digito}	{
    /*Se reconoce la figura circulo y se actualiza el contador de circulos.*/
    printf("%s : Se reconoce como circulo.\n", yytext); ++num_circulos;
    }

"cuadrado"[ ]{coordenada}{coordenada}	    {
    /*Se reconoce la figura cuadrado si no hay espacio entre las coordenadas*/
    /*Se tendría que haber usado estados para denotar las diferencias*/
    comprobarDiagonal(yytext,0);
    }

"cuadrado"[ ]{coordenada}[ ]{coordenada}	{
    /*Se reconoce la figura cuadrado si hay espacio entre las coordenadas*/
    comprobarDiagonal(yytext,1);
    }

.*      { /* Se reconoce cualquier otra cadena como no figura */
        printf("%s : No se reconoce como figura.\n",yytext);}
\n   {/*Se ignoran las lineas vacias*/;}
%%


/***** Zona de funciones auxiliares *****/

extern FILE *yyin;

int main(int argc, char *argv[])
{
    /*Comprobación del numero de argumentos y de la correcta apertura del archivo.*/
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

    /* Se cierra el archivo de entrada */
    fclose(yyin);

    /* Se muestra el conteo final*/
    printf("\nSe han reconocido %d triangulos, %d cuadrados y %d circulos.\n", num_triangulos, num_cuadrados, num_circulos);

  return 0;
}

int comprobarDiagonal(char* cadena, int opc)
{
    /* No reconoce bien el que las posiciones sean diagonales. Será que no lo entiendo yo.*/
    /* Si se reconoce la estructura del cuadrado, se analiza cada valor de la componente */
    int x1, x2, x3, x4, y1, y2, y3, y4;

    if(opc == 0)
    {
        sscanf(cadena, "cuadrado(%d,%d %d,%d)(%d,%d %d,%d)", &x1, &x2, &x3, &x4, &y1, &y2, &y3, &y4);
    }
    else if (opc == 1)
    {
        sscanf(cadena, "cuadrado(%d,%d %d,%d) (%d,%d %d,%d)", &x1, &x2, &x3, &x4, &y1, &y2, &y3, &y4);
    }

    if((x1 == y1 && x2 == y2) || (x3 == y3 && x4 == y4))
    {
        printf("%s : No se reconoce como cuadrado. Vertices no diagonalmente opuestos.\n", cadena);
    }
    else
    {   
        printf("%s : Se reconoce como cuadrado.\n", cadena); ++num_cuadrados;
    }
    return 0;
}