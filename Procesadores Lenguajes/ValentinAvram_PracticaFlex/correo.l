/**************************** correo.l ***********************************/
/*Hecho por Valentín G. Avram Aenachioei*/
/***** Zona de las declaraciones o definiciones *****/
%{
    int num_correos=0;
%}
/* Definiciones regulares */

letra		[a-zA-Z]
digito		[0-9]


/***** Zona de las reglas *****/
%%
{letra}{digito}{digito}{letra}{5}\@"uco.es"	{printf("' %s ' se reconoce como correo electronico",yytext);num_correos++;}
({letra}|{digito})*(@)*(.)*						{printf("' %s ' no se reconoce como correo electronico",yytext);}
%%


/***** Zona de funciones auxiliares *****/

extern FILE *yyin;

int main(int argc, char *argv[])
{
    /*Comprobacion del numero de argumentos y de la correcta apertura del fichero de entrada*/
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

    /*Cierre del fichero de entrada*/
    fclose(yyin);

    /*Se muestra el conteo de emails reconcidos*/
    printf("\nSe han reconocido %d direcciones de correo.\n",num_correos);

  return 0;
}