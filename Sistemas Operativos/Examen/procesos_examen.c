#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <signal.h>
#include <errno.h>
#include <unistd.h>
#include <string.h>

void signal_func(int signal)
{
    printf("Capturada la signal %d\n", signal);
}

void main(int argc, char * const argv[])
{
    system("clear");
    int status;
    pid_t pid, childpid;

    printf("Proceso padre con PID = %d\n", getpid());

    pid = fork();

    if(pid == -1)
    {
        printf("Error al crear la hebra\n");
        exit(EXIT_FAILURE);
    }

    if(pid == 0)
    {
        printf("Proceso hijo con PID = %d y PPID = %d\n", getpid(), getppid());
        signal(SIGUSR1, signal_func);
        while(1){}
    }
    else
    {
        sleep(1);
        printf("Soy el padre y mando una signal al hijo\n");

        for(int i = 0; i < 3; i++)
        {
            kill(pid, SIGUSR1);
            sleep(1);
        }
        kill(pid, SIGKILL);
    }

    while((childpid = waitpid(-1, &status, WUNTRACED | WCONTINUED)) > 0)
    {
        if(WIFEXITED(status))
        {
            printf("Proceso hijo con PID = %d ha acabado con status = %d\n", childpid, WEXITSTATUS(status));
        }
        else if(WIFSIGNALED(status))
        {
            printf("Proceso hijo con PID = %d ha sido terminado con status = %d\n",childpid, WTERMSIG(status));
        }
    }

    if(childpid == (pid_t)-1 && errno == ECHILD)
    {
        printf("Proceso padre no tiene mas hijos que esperar. Valor de errno = %d, definido como %s\n", errno, strerror(errno));
    }
    else
    {
        printf("Error en la invocacion del wait / waitpid. Valor de errno = %d, definido como %s \n", errno, strerror(errno));
        exit(EXIT_FAILURE);
    }
    exit(EXIT_SUCCESS);
}