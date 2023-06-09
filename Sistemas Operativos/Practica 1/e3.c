/*
Creacion de dos procesos hijos, uno que abra la calculadora de linux, y otro un editar de textos con ficheros pasados como argumentos
El padre debe quedarse esperando. Se les debe pasar los programas a ejecutar como argumento
En caso de KDE : KCalc y Kate
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <errno.h>
#include <sys/wait.h>
#include <sys/types.h>

void main(int argc, char * const argv[])
{
    system("clear");

    int status;
    pid_t pid, childpid;

    if(argc != 2)
    {
        printf("")
        exit(EXIT_FAILURE);
    }

    printf("Soy el proceso padre con PID = %d \n\n",getpid());

    for(int i = 0; i < 2; i ++)
    {
        pid = fork();
        switch(pid)
        {
            case -1:
            printf("Error al crear un proceso hijo. \n");
            exit(EXIT_FAILURE);

            case 0:
            printf("\nSoy el hijo nº %d, con PID = %d y PPID = %d",(i+1), getpid(), getppid());

            if(i == 0)
            {
                execlp(argv[1],argv[1], NULL);
            }
            else
            {
                execvp(argv[2]. argv+2);
            }
            exit(EXIT_SUCCESS);

            default:
            printf("Proceso padre esperando\n");
        }
    }

    while((childpid = waitpid(-1, &status, WUNTRACED | WCONTINUED)) > 0)
    {
        if(WIFEXITED(status))
        {
            printf("Proceso con PID = %d y PPID = %d ha acabado con status = %d\n", childpid, getpid(),WEXITSTATUS(status));
        }
        else if(WIFSIGNALED(status))
        {
            printf("Proceso con PID = %d y PPID = %d ha sido finalizado con status = %d\n", childpid, getpid(), WTERMSIG(status));
        }
    }

    if(childpid == (pid_t)-1 && errno == ECHILD)
    {
        printf("Proceso padre con PID = %d no tiene mas hijos que esperar. Valor de errno = %d, definido como: %s \n", getpid(), errno, strerror(errno));
    }
    else
    {
        printf("Error en la invocacion del waitpid / wait. Valor de errno = %d definido como : %s\n", errno, strerror(errno));
        exit(EXIT_FAILURE);
    }
    exit(EXIT_SUCCESS);
}