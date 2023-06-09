#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>

/*
Crea dos numeros aleatorios, los suma y los imprime.
Devuelve el valor de la suma con "pthread_exit"
*/
void * th_rand()
{
    float x = (float) rand() / (float) RAND_MAX;
    float y = (float) rand() / (float) RAND_MAX;
    
    float *z = malloc(sizeof(float));
    *z = x + y;
    printf("X = %f\nY = %f\nZ= %f\n\n",x,y,*z);

    pthread_exit((void *)z);
}

void main(int argc, char const * argv[])
{
    if(argc < 2)
    {
        printf("Error en el numero de argumentos\n");
        exit(EXIT_FAILURE);
    }

    srand(time(NULL));

    int n = atoi(argv[1]);
    pthread_t thread[n];
    float *ret, suma = 0;

    /*
    Bucle de creacion de hebras, que ejecutaran la funcion "th_rand"
    */
    for(int i = 0; i < n; i++)
    {
        if(pthread_create(&(thread[i]), NULL, (void *) th_rand, NULL))
        {
            printf("Error al crear la hebra \n");
            exit(EXIT_FAILURE);
        }
    }

    /*
    Bucle de cierre de hebras, que recogera el valor que retorna la funcion que han 
    ejecutado las hebra y lo almacena en "&ret"
    */
    for(int i = 0; i < n; i ++)
    {
        if(pthread_join(thread[i], (void *) &ret))
        {
            printf("Error al recoger la hebra\n");
            exit(EXIT_FAILURE);
        }
        suma += *ret;    
    }

    printf("El valor de la suma total es = %f\n", suma);
    exit(EXIT_SUCCESS);
}