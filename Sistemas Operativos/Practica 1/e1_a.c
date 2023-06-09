/*
Creación de un proceso padre y n procesos hijos en forma de abanico.
Todos los procesos hijos coexisten a la vez.
*/

#include <sys/types.h>
#include <sys/wait.h>
#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>
#include <unistd.h>

void main(int argc, char * const argv[])
{
    system("clear");
    if(argc != 2)
    {
        printf("Error en el numero de argumentos \n");
        exit(EXIT_FAILURE);
    }

    int n, status;
    n = atoi(argv[1]);
    pid_t pid, childpid;
    printf("Soy el proceso padre con PID = %d, voy a crear %d procesos hijos \n\n", getpid(),n);

    for(int i = 0; i < n; i++)
    {
        pid = fork();

        switch(pid)
        {
            case -1:
            printf("Error al crear el proceso hijo\n");
            exit(EXIT_FAILURE);

            case 0:
            printf("Soy el proceso con PID = %d y PPID = %d\n", getpid(), getppid());
            exit(EXIT_SUCCESS);

            default:
            printf("Esperando a que mis hijos acaben\n");
        }
    }

    while((childpid = waitpid(-1, &status, WUNTRACED | WCONTINUED)) > 0)
    {
        if(WIFEXITED(status))
        {
            printf("PPID = %d, con hijo PID = %d, status = %d \n", getpid(), childpid, WEXITSTATUS(status));
        }
        else if(WIFSIGNALED(status))
        {
            printf("PPID = %d, con hijo PID = %d, status = %d \n", getpid(), childpid, WTERMSIG(status));
        }
    }

    if(childpid == (pid_t)-1 && errno == ECHILD)
    {
        printf("PPID = %d no tiene mas hijos mas hijos que esperar. Valor de errno = %d, definido como: %s \n",getpid(), errno, strerror(errno));
    }
    else
    {
        printf("Error en la invocación del wait o waitpid. Valor de errno = %d, definido como : %s \n", getpid(), errno, strerror(errno));
        exit(EXIT_FAILURE);
    }
    exit(EXIT_SUCCESS);
}