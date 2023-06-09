/*
Programa en un bucle infinito usado para el ejercicio 7.
*/

#include <unistd.h>
#include <stdio.h>
#include <stdlib.h>
#include <signal.h>

void funcionSignal()
{
    printf("\n Mi PID = %d\n",getpid());
}

void main()
{
    system("clear");
    
    pintf("Mi PID = %d\n");
    signal(SIGUSR, funcionSignal);
   
    while(1)
    {
        pause();
    }
    exit(EXIT_SUCCESS);
}