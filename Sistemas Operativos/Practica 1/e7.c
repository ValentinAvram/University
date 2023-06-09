/*
Creacion de un programa que este permanentemente a la esperea de una SIGUSR1 y al capturarla imprimir su PID.
Dejar el programa corriendo de fondo.

Creacion de otro programa que reciba como argumento el PID de un programa, y le envie una SIGUSR1 a dicho programa.
Tras enviarle el SIGUSR1, esperara 1 sec y enviar SIGKILL
*/
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

void main()
{
    system("clear");

    int n = atoi(argv[1]);
    int status;
    pid_t pid, childpid;

    if(argc != 2)
    {
        printf("Error en el numero de argumentos \n");
        exit(EXIT_FAILURE);
    }

    printf("Le enviaremos la signal al proceso con PID = %d\n", n);

    status = kill(n, SIGUSR1);

    if(!status)
    {
        printf("Signal enviada con exito! \n");    }
    }
    else
    {
        printf("Error al enviar la signal. Valor de errno  = % d, definido como: %s\n", errno, strerror(errno));
    }

    sleep(1);

    status = kill(n, SIGKILL);

    if(!status)
    {
        printf("Signal enviada con exito! \n");    }
    }
    else
    {
        printf("Error al enviar la signal. Valor de errno  = % d, definido como: %s\n", errno, strerror(errno));
    }
    exit(EXIT_SUCCESS);
}