/*
Creacion de un proceso hijo al que se le envia SIGUSR 1 cada 1 segundo.
Al recibir la SIGUSR1, usar una funcion aparte para imprimir una respuesta.
Tras 5 SIGUSR1 recibidos, se acaba.
*/

#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <stdio.h>
#include <errno.h>
#include <string.h>
#include <stdlib.h>

void funcionSignal()
{
    printf("\nSignal recibida por el proceso hijo \n");
}

void main()
{
    system("clear");

    int status;
    pid_t pid, childpid;

    printf("Soy el proceso padre con PID = %d\n\n", getpid());

    pid=fork();

    switch(pid)
    {
        case -1:
        printf("Error en la creacion del proceso hijo\n");
        exit(EXIT_FAILURE);
        
        case 0:
        signal(SIGUSR1, funcionSignal);
        printf("Soy el proceso hijo con PID = %d y PPID = %d \n", getpid(), getppid());
        
        for(int i = 0; i < 5; i ++)
        {
            pause();
        }

        sleep(3);

        default:
        printf("Proceso padre esperando al hijo \n");
        
        for(int i = 0; i < 5; i++)
        {
            sleep(1);
            kill(pid, SIGUSR1); 
        }
        sleep(2);
        kill(pid, SIGKILL);
    }

    while((childid = waitpid(-1, &status, WUNTRACED | WCONTINUED)) >0)
    {
        if(WIFEXITED(status))
        {
            printf("Proceso hijo con PID = %d y PPID = %d finalizado con status = %d\n", childpid, getpid(), WEXITSTATUS(status));
        }
        else if(WIFSIGNALED(status))
        {
            printf("Proceso hijo con PID = %d y PPID = %d ha sido finalizado con status = %d\n", childpid, getpid(), WEXITSTATUS(status));
        }
    }

    if(childpid == (pid_t)-1 && errno ==  ECHILD)
    {
        printf("Proceso padre no tiene mas hijos que esperar. Valor de errno  = %d, definido como: %s\n", errno, strerror(errno));
    }
    else
    {
        printf("Error en la invocacion del wait/waitpid. Valor de errno = %d, definido como: %s \n", errno, strerror(errno));
        exit(EXIT_FAILURE);
    }
    exit(EXIT__SUCCESS);
}