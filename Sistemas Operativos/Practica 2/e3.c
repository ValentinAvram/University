#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <pthread.h>

typedef struct
{
    int *vector;
    int nEle;
} vInfo;

int * reservarMemoria(int nEle)
{
    int *vector;
    vector = (int *) malloc (nEle * sizeof(int));
    return vector;
}

void rellenaVector(int *vector, int nEle)
{
    srand(time(NULL));
    for(int i = 0; i < nEle; i++)
    {
        vector[i] = ((rand()%9) +1);
    }
}

void imprimeVector (int * vector, int nEle)
{
    printf("\n[");
    printf("%d", vector[0]);

    for(int i = 1; i < nEle; i++)
    {
        printf(", %d", vector[i]);
    }  
    printf("]\n\n");
}

vInfo * crearHijos(int * vParent, int nThreads)
{
    int nEle = 10 / nThreads;
    vInfo * vChilds = (vInfo*) malloc(nThreads * sizeof(vInfo));

    for(int i = 0; i < nThreads; i++)
    {
        vChilds[i].vector = reservarMemoria(nEle);
        vChilds[i].nEle = nEle;
        int k = 0;

        for(int j = (i*nEle); j < ((i*nEle)+nEle); j++)
        {
            vChilds[i].vector[k] = vParent[j];
            k++;
        }

        printf("El vector hijo es: \n");
        imprimeVector(vChilds[i].vector, vChilds->nEle);
    }
    return vChilds;
}

int numOk(int nVec)
{
    if(nVec == 2 || nVec == 5)
    {
        return 1;
    }
    return 0;
}

void * th_sum(void *d)
{
    int *suma = malloc(sizeof(int));
    *suma = 0;
    vInfo *v;
    v = (vInfo*)d;

    for(int i = 0; i < v->nEle; i++)
    {
        *suma += v-> vector[i];
    }

    printf("La suma de los elementos de este vector es : %d \n", *suma);
    pthread_exit((void **) suma);
}

void main (int argc, char const * argv[])
{
    system("clear");

    if(argc < 2 || (numOk(atoi(argv[1])) == 0))
    {
        printf("Error en los argumentos. El numero de hebras debe ser 2 o 5.\n");
        exit(EXIT_FAILURE);
    }

    int nThreads = atoi(argv[1]);
    
    vInfo *vParent = malloc(sizeof(vInfo));
    vParent -> nEle = 10;

    vParent -> vector = reservarMemoria(vParent -> nEle);
    rellenaVector(vParent -> vector, vParent -> nEle);
    printf("El vector padre es : \n");
    imprimeVector(vParent -> vector, vParent -> nEle); 

    vInfo * vChilds = crearHijos(vParent->vector, nThreads);

    pthread_t thread[nThreads];
    int *ret, suma=0;

    for(int i = 0; i < nThreads; i++)
    {
        if(pthread_create(&(thread[i]), NULL, (void*) th_sum, (void*) vChilds+ (sizeof(vInfo)*i)))
        {
            printf("Error al crear las hebras\n");
            exit(EXIT_FAILURE);
        }
    }

    for(int i = 0; i < nThreads; i++)
    {
        if(pthread_join(thread[i], (void**) &ret))
        {
            printf("Error al recoger las hebras \n");
            exit(EXIT_FAILURE);
        }
        
        suma += *ret;
    }

    printf("La suma de los numeros de todos los vectores es : %d\n", suma);
    exit(EXIT_FAILURE);
}