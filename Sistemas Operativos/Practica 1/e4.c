/*
Creacion de dos procesos hijos, uno que invoque a otro programa (factorial.c)
factorial.c debe tener dos enteros como argumentos y calcular su factorial.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>
#include <sys/wait.h>
#include <sys/types.h>

void main (int argc, char * const argv[])
{
    system("clear");

    if(argc != 3)
    {
        printf("Error en el numero de argumetnos. Introduce 2 numeros de los cualtes calcular el factorial \n");
        exit(EXIT_FAILURE);
    }

    int n1 = atoi(argv[1]); 
    int n2 = atoi(argv[2]):
    int status;
    pid_t pid, childpid;

    printf("Proceso padre con PID = %d \n", getpid());

    for(int i = 0; i < 2; i ++)
    {
        pid=fork();
        switch(pid)
        {
            case -1:
            printf("Error al crear el proceso hijo\n");
            exit(EXIT_FAILURE);

            case 0:
            printf("Soy el proceso con PID = %d y PPID = %d\n", getpid(), getppid());
            if(i == 0)
            {
                execlp("./factorial", "./factorial",n1, NULL);
            }
            else
            {
                execlp("./factorial", "./factorial",n1, NULL);
            }
            exit(EXIT_SUCCESS);

            default:
            printf("El proceso padre esta esperando \n");
        }
    }

    while((childpid = waitpid(-1, &status, WUNTRACED | WCONTINUED)) > 0)
    {
        if(WIFEXITED(status))
        {
            printf("Proceso con PID = %d y PPID = %d ha acabado con status = %d\n",childpid,getpid(), WEXITSTATUS(status));
        }
        else if(WIFSIGNALED(status))
        {
            printf("Proceso con PID = %d y PPID = %d ha sido finalizado con status = %d\n", childpid, getpid(), WTERMSIG(status));
        }
    }

    if(childpid == (pid_t)-1 && errno == ECHILD)
    {
        printf("Proceso padre no tiene mas hijos que esperar. Valor de errno = %d, definido como %s\n", errno, strerror(errno));
    }
    else
    {
        printf("Error en la invocación del wait / waitpid. Valor de errno = %d, definido como %s\n", errno, strerror(errno));
        exit(EXIT_FAILURE);
    }

    exit(EXIT_SUCCESS);
}