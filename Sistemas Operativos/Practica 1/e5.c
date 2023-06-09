/*
Capturar una signal de alarma e imprimir "RING", primero a los 5 segundos, luego a los 3.
Tras 4 signals, se acaba.
*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <signal.h>

int i = 0;

void funcionSignal()
{
    printf("SIGNAL RECIEVED\n");
    i ++;

    if(i == 4)
    {
        kill (getpid(), SIGKILL);
    }
}

void main()
{
    //La funcion pause() pausa la ejecucion del programa hasta que suene una alarma.
    system("clear");
    signal(SIGALRM, RING);

    alarm(5);
    pause();

    alarm(3);
    pause();

    while(1)
    {
        alarm(1);
        pause();
    }
    exit(EXIT_SUCCESS);
}