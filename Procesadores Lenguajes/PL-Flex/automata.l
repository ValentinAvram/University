/**************************** automata.l ***********************************/
/*Hecho por Valentín G. Avram Aenachioei*/

%{
    int counter  = 0;
%}

/* Estados del automata*/
%x Q1 Q2 Q3 Q4 Q5

%%

"e "        {
                /* Paso a estado Q1 */
                yymore();
                BEGIN(Q1);
            }

<Q1>"a "     {
                /* Paso a estado Q2 */
                yymore();
                BEGIN(Q2);
            }

<Q2>"d "    {
                /* Paso a estado Q3 */
                yymore();
                BEGIN(Q3);
            }

<Q2>"r "    {
                /* Paso a estado Q1 */
                yymore();
                BEGIN(Q1);
            }
    
<Q3>"i "    {
                /* Paso a estado Q4 */
                yymore();
                BEGIN(Q4);
            }

<Q3>"r "    {
                /* Paso a estado Q1 */
                yymore();
                BEGIN(Q1);
            }

<Q4>"p "    {
                /* Paso a estado Q5 */
                yymore();
                BEGIN(Q5);
            }

<Q4>"r "    {
                /* Paso a estado Q1 */
                yymore();
                BEGIN(Q1);
            }

<Q5>"r "    {
                /* Paso a estado Q1 */
                yymore();
                BEGIN(Q1);
            } 

<Q5>"f"     {
                /* Paso al estado inicial, y se imprime la secuencia */
                printf("Secuencia de fases de desarrollo ' %s ' reconocida\n", yytext);
                BEGIN(INITIAL);

            }

.           {
                yymore();
                counter++; 
            }

\n          {
                /* Se imprime la secuencia desconocida */
                if (counter > 0) {
                    printf("Secuencia desconocida: %s", yytext);
                }
                counter = 0; 
            }
%% 

int main(int argc, char *argv[])
{

    /*Comprobacion del numero de argumentos y de la correcta apertura del archivo*/
    if (argc != 2)
    {
        printf("Uso: %s <archivo de entrada> \n", argv[0]);
        exit(1);
    }
    yyin = fopen(argv[1], "r");
    if (yyin == NULL)
    {
        printf("No se puede abrir el archivo %s para lectura \n", argv[1]);
        exit(1);
    }

    yylex();

    fclose(yyin);
    return 0;
}