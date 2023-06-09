/*
Creacion de un proceso padre y un proceso hijo, el padre se quedará en sleep()
Para ver que el padre esta en estado zombie, usar ps- a
*/

#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <errno.h>
#include <string.h>

void main (int argc, char * const argv[])
{
    system("clear");

    if(argc != 2)
    {
        printf("Error en el nº de argumentos\n");
        exit(EXIT_FAILURE);
    }

    int status;
    pid_t pid, childpid;

    printf("Soy el proceso padre, mi PID = %d \n", getpid());

    pid = fork();

    switch(pid)
    {
        case -1:
        printf("Error en la creacion del proceso hijo\n");
        exit(EXIT_FAILURE);

        case 0:
        printf("Soy el proceso hijo con PID = %d y PPID = %d\n", getpid(), getppid());
        exit(EXIT_SUCCESS);

        default:
        printf("Soy el proceso padre y voy a esperar 10 segundos\n");
        sleep(10);
    }

    while((childpid = waitpid(-1, &status, WUNTRACED | WCONTINUED)) > 0)
    {
        if(WIFEXITED(status))
        {
            printf("El proceso con PID = %d y PPID = %d ha acabado con status %d\n",getpid(), childpid, WEXITSTATUS(status));
        }
        else if(WTERMSIG(status))
        {
            printf("El proceso con PID = %d y PPID = %d ha sido finalizado con status = %d \n", getpid(), childpid, WTERMSIG(status));
        }
    }

    if(childpid == (pid_t)-1 && errno == ECHILD)
    {
        printf("Proceso padre con PID = %d ya no tiene mas hijos que esperar. Valor de errno = %d, definido como %s\n", getpid(), errno, strerror(errno));
    }
    else
    {
        printf("Error en la invocacion del wait / waitpid \n");
        exit(EXIT_FAILURE);
   }
    exit(EXIT_SUCCESS);
}