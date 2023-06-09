/*Realice un programa que reciba por linea de argumentos el numero de hilos que se van a crear.
Antes de crear los hilos imprima el vector inicial (puede usar una variable global).
Los hilos generaran un numero aleatorio entre 0 y 9 que añadiran a una posicion aleatoria entre 0 y 2
Una vez recogidas todas las hebras imprima el vector resultado.
*/

#include <stdlib.h>
#include <stdio.h>
#include <sys/types.h>
#include <pthread.h>
#include <semaphore.h>

//Declaracion de variable global
int vector[3]={0,0,0};//

//Declaracion e inicializacion del semaforo
sem_t semaforo;

//Funcion a la que accederan las hebras
void*funcion()
{
    int valor_aleatorio = rand()%10;
    int posicion_aleatoria = rand()%3;

    printf("Soy la hebra %lu y voy a insertar el valor %d\n", pthread_self(), valor_aleatorio);
    printf("Soy la hebra %lu y voy a insertar en la posicion %d\n\n", pthread_self(), posicion_aleatoria);

    //Esta es la region critica, pues el vector es accesible por todos los hilos y se pueden dar circunstancias que lo corrompan
    sem_wait(&semaforo);
    vector[posicion_aleatoria] = vector[posicion_aleatoria] + valor_aleatorio;
    sem_post(&semaforo);

    pthread_exit(NULL);
}

int main(int argc, char * argv[])
{
    system("clear");

    if(argc!=2)
    {
        perror("Error en la linea de argumentos");
        exit(EXIT_FAILURE);
    }

    sem_init(&semaforo, 0, 1);

    int n_hilos = atoi(argv[1]);

    pthread_t hilo[n_hilos];

    srand(time(NULL));

    for(int i=0; i<3; i++)
    {
        printf("V[%d]=%d\t",i,vector[i]);
    }

    printf("\n\n");

    for(int i=0; i<n_hilos; i++)
    {
        if(pthread_create(&hilo[i], NULL, (void*)funcion, NULL))
        {
            printf("Error al crear la hebra\n");
            exit(EXIT_FAILURE);
        }
    }

    for(int i=0; i<n_hilos;i++)
    {
        if(pthread_join(hilo[i],NULL))
        {
            printf("Error al recoger la hebra\n");
            exit(EXIT_FAILURE);
        }
    }

    for(int i=0; i<3; i++)
    {
        printf("V[%d]=%d\t",i,vector[i]);
    }
    printf("\n");

    sem_destroy(&semaforo);

    return 0;
}