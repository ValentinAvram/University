/**************************** control.l ***********************************/
/*Hecho por Valentín G. Avram Aenachioei*/

%{
    int dia, mes, year, hora1, minuto1, hora2, minuto2;
    float totalHoras = 0;
    int counter = 0, numFechas = 0;
    int controlFecha = 1;

%}

/*Uso dos estados para luego poder contabilizar las horas*/
%x HORA1 HORA2

%%

^([0-9]{2})-([0-9]{2})-([0-9]{4})  {
                /* Reconoce la fecha y almaceno el dia, mes y año */
                dia = strtol(yytext, NULL, 10);
                mes = strtol(yytext + 3, NULL, 10);
                year = strtol(yytext + 6, NULL, 10);

                /*Comprobar fecha correcta*/
                if(comprobarFecha(dia, mes, year) == 0)
                {
                    controlFecha = 0;
                }

                /*Paso al estado HORA1 para reconocer la primera hora*/
                BEGIN(HORA1);
            }


<HORA1>[ ]([0-9]{2}):([0-9]{2}) {
                /* Reconoce la primera hora */
                hora1 = strtol(yytext + 1, NULL, 10);
                minuto1 = strtol(yytext + 4, NULL, 10);

                /* Ingresamos en el estado HORA2 para reconocer la segunda hora */
                BEGIN(HORA2);
            }


<HORA2>[ ]([0-9]{2}):([0-9]{2}) {
                /* Reconoce la segunda hora */
                hora2 = strtol(yytext + 1, NULL, 10);
                minuto2 = strtol(yytext + 4, NULL, 10);
                
                /*Comprobamos que la hora 2 sea mayor que la hora 1*/
                int difHoras = (hora2 - hora1) + (minuto2 - minuto1) / 60.0;

                if(difHoras < 0)
                {
                    /*Si la hora 2 es menor que la hora 1, incorrecto y se vuelven a inicializar las variables*/
                    printf("Formato de fecha  '%02d-%02d-%d %02d:%02d %02d:%02d' incorrecto \n", dia, mes, year, hora1, minuto1, hora2, minuto2);
                    dia = mes = year = hora1 = minuto1 = hora2 = minuto2 = 0;
                    BEGIN(INITIAL);
                }
                else if(controlFecha == 0)
                {
                    /*Si la fecha es incorrecta, se indica y se vuelven a inicializar las variables*/
                    printf("Formato de fecha  '%02d-%02d-%d %02d:%02d %02d:%02d' incorrecto \n", dia, mes, year, hora1, minuto1, hora2, minuto2);
                    dia = mes = year = hora1 = minuto1 = hora2 = minuto2 = 0;
                    controlFecha = 1;
                    BEGIN(INITIAL);
                }
                else if(controlFecha == 1 && difHoras >= 0)
                {
                    /*Si la fecha es correcta, se indica y se vuelven a inicializar las variables*/
                    /*Se almacenan el numero de dias y el total de horas*/
                    printf("Formato de fecha  '%02d-%02d-%d %02d:%02d %02d:%02d' correcto \n", dia, mes, year, hora1, minuto1, hora2, minuto2);
                    totalHoras += difHoras;
                    numFechas++;
                    dia = mes = year = hora1 = minuto1 = hora2 = minuto2 = 0;
                    BEGIN(INITIAL);
                }
            }

.           {
                /*Se usa yymore para almacenar la cadena de caracteres que no reconoce*/
                yymore();
                counter++; 
            }

\n          {
                if (counter > 0) {
                    /*Eliminar el salto de linea*/
                    yytext[counter] = '\0';
                    printf("Formato de fecha  '%s' incorrecto\n", yytext);
                }
                /*Reiniciar el contador*/
                counter = 0; 
            }
%%


extern FILE *yyin;

int main(int argc, char *argv[])
{
    /*Comprobar el numero de argumentos y la correcta apertura del archivo*/
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

    /*Imprimir el numero de fechas y la media de horas*/
    printf("\nLa media de horas es: %.2f \n", totalHoras/ numFechas);
    printf("\nEl total de horas es: %.2f \n", totalHoras);

    return 0;
}

int comprobarFecha(int dia, int mes, int year)
{
    int diasMes[12] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    int bisiesto = 0;

    /*Comprobar año bisiesto*/
    if (year % 4 == 0)
    {
        bisiesto = 1;
    }

    /*Comprobar mes*/
    if (mes < 1 || mes > 12)
    {
        return 0;
    }

    /*Comprobar dia del respectivo mes*/
    if (dia < 1 || dia > diasMes[mes - 1])
    {
        /*Comprobar febrero bisiesto*/
        if (dia == 29 && mes == 2 && bisiesto == 1)
        {
            return 1;
        }
        else
        {
            return 0;  
        }
    }
    
    return 1;
}