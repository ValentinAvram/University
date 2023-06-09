# Guía de Prácticas de SSOO

Guia para realizar todos los ejercicios practicos de Sistemas Operativos.

## Procesos

Lo primero es incluir las librerias necesarias:
```
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <string.h.h>
#include <errno.h.h>
#include <sys/types.h>
#include <sys/wait.h>
```

Una vez dentro del main, se deben crear las variables de "control":
```
int status;
pid_t pid, childpid;
```

Para crear nuevos procesos (ya sea dentro o fuera de un bucle):
```
pid = fork();
Devuelve -1 y modifica el valor de "errno" en caso de error.
```

Los procesos se identifican con las siguientes funciones:
```
getpid(); // Para ver el PID del propio proceso
getppid(); // Para ver el PID del proceso padre
```

Para esperar a un proceso podemos usar dos funciones:

```
pid_t wait();

pid_t waitpid(pid_t pid, &status, opciones);

Las opciones pueden ser, pudiendo usarse varias a la vez:
 - WNOHANG, llamada no bloqueante
 - WUNTRACED, informa del estado de procesos detenidos
 - WCONTINUED, indica si un proceso ha sido reanudado

Ambas funciones devuelven -1 y modifican la variable "errno" en caso de error.
```

Otros datos, como el usuario o el entorno de ejecución se pueden obtener con:
```
pid_t getuid();

pid_t getnev();
```

Para ejecutar un proceso, hay varias funciones distintas de la familia exec():
```
- execl(path, arg1, ..., argN, NULL);
- execlp(file, arg1, ..., argN, NULL);
- execv(path, argv[]);
- execvp(path, argv[]);

Siendo path la ruta completa del proceso a ejecutar, 
file el nombre partiendo del path del proceso que invoca el exec(),
y argv[] un vector de cadenas acabado en cero o NULL.
```

Para acabar con un proceso:
```
 - exit(status)
Siendo status el valor numérico, EXIT_SUCCESS, o EXIT_FAILURE;
```

Bucle por el cual el proceso padre recoge a todos sus procesos hijos cuando acaben:
```
while((childpid = waitpid(-1, &status, WUNTRACED | WCONTINUED)) > 0)
{
    if(WIFEXITED(status))
    {
        // Proceso ha acabado con estado WEXITSTATUS(status);
    }
    else if(WIFSIGNALED(status))
    {
        // Proceso ha acabado por la señal WTERMSIG(status);
    }
    else if(WIFSTOPPED(status))
    {
        // Proceso se ha detenido con estado WSTOPSIG(status);
    }
}

if(childpid == (pid_t)-1 && errno == ECHILD)
{
    // Proceso padre no tiene mas hijos que esperar
}
else
{
    // Error en wait / waitpid
}

Este bucle puede estar dentro o fuera del bucle de creación de procesos hijos.
Si esta dentro, se crean procesos de uno en uno.
Si esta fuera, se crean todos los procesos, y se van cerrando cuando corresponda.
```

## Señales

Para trabajar con señales entre procesos, lo primero es añadir las librerias necesarias:
```
#include <signal.h>
```

Para capturar señales, es decir, quedarse a la espera de recibir una señal:
```
signal(SIG, funcion);

 - SIG es la señal que esperamos
 - funcion es la función que se ejecutará cuando se reciba la señal SIG
```

Hay varios MACROS de señales que enviar / recibir:
```
 - SIGTERM, SIGKILL // Finalizar el proceso
 - SIGSTOP // Para la ejecución del proceso
 - SIGCONT // Continua con la ejecución de un proceso detenido anteriormente
 - SIGALRM // Para que el proceso reciba la señal tras un tiempo prefijado
 - SIGUSR1 y SIGUSR2 // Estan a decisión del programador
```

Para enviar señales:
```
kill(pid, SIG);

 - pid es el PID del proceso al que se le envía 
 - SIG es la macro de la señal a enviar
```

Se pueden enviar alarmas, que tras un tiempo determinado, se envía una señal SIGALRM al propio proceso,
el cual puede capturarla a traves de signal();
```
alarm(num_segundos);
```

## Hilos

Para trabajar con hilos, es necesario incluir la librería:
```
#include <pthread.h>
```

Para compilar cualquier programa que use hilos / hebras, es necesario incluir "-lpthread":
```
gcc file.c -o file.exe -lpthread
```

Se debe crear un vector de hebras (o una sola hebra):
```
pthread_t vectorDeHebras[n];
```

Para crear una hebra, con su correspondiente funcion:
```
phtread_create(&(vectorDeHebras[n]), NULL, (void *)funcion, NULL)

 - 1º NULL : Nivel de núcleo, cada hebra se trata como un proceso independiente.
 - funcion : Función que ejecutará la hebra la ejecutarse.
 - 2º NULL : Puntero al parametro que se le pasará a la funcion start_routine.

Devolverá un valor distinto de cero en caso de error.
Se puede trabajar con una sola hebra, en lugar de un vector.
```

Esperar a la finalización de una hebra:
```
pthread_join(vectorDeHebras[i], (void *)&ret)

 - &ret : Variable que almacena el valor devuelto por la función que ejecuta la hebra.
```

Identificar una hebra:
```
pthread_self();

Se debe almacenar en un %lu, long unsigned.
```

Finalizar hebra y devolver resultados:
```
pthread_exit((void *) ret);

La hebra devolverá el valor ret. Puede ser NULL.
Si se usa exit(), se acaban todas las hebras asociadas. No usar.
```

Señal a una hebra desde el proceso llamador:
```
pthread_kill(vectorDeHebras[n], MACRO);

 - MACRO : Macro de la señal 
Cada hebra puede asociar una función manejadora a una señal concreta, 
pero la acción de la recepción de una señal se lleva a cabo a nivel de proceso. 
```

## Semáforos binarios (mutex)

Un mutex solo puede liberarse por el proceso que lo adquirió. Tiene dos estados: abierto y cerrado.

Creación de un mutex:
```
pthread_mutex_t mutex = PTHREAD_MUTEX_INITIALIZER;

Importante! : Debe crearse como una variable global para que todas las hebras lo puedan usar.
```

Petición de bloqueo de un mutex:
```
pthread_mutex_lock(&mutex);

Devuelve distinto de cero si hubo error.
```

Petición de desbloqueo de un mutex:
```
pthread_mutex_unlock(&mutex);

Devuelve distinto de cero si hubo error.
```

Destrucción de un mutex:
```
pthread_mutex_destroy(&mutex);

Devuelve distinto de cero si hubo error.
```

## Semáforos generales / fuertes (semaphore)

Estructura a la que se le puede asignar un valor inicial no negativo, con una cola de procesos, y al que sólo se puede acceder utilizando dos operaciones atómicas, **wait()** y **signal()**. Se suele inicializar a 1.
Pueden desbloquearse por cualquier otro hilo que no lo haya bloqueado previamente.

La operación **wait()** decrementa el valor del semáforo. Si el valor es negativo, el proceso que ejecuta el wait se bloquea. Si al decrementar el valor es 0 o más, el proceso entra en la sección crítica.

La operación **signal()** incrementa el valor del semáforo. Si el valor es 0 o menos, se debloquea uno de los procesos bloqueados en la operación wait(), usando FIFO.

Las librerías necesarias para trabajar con semaforos fuertes son:
```
#include <semaphore.h>
```

Creación de un semáforo:
```
sem_t semaforo;

Esto no inicializa el hilo, solo lo crea.
Importante! : Debe crearse como una variable global para que todas las hebras lo puedan usar.
```

Inicializar semáforo:
```
sem_init(&semaforo, 0, 1);

 - semaforo : Puntero al semáforo.
 - 0 : Indica que el semáforo solo puede ser usado por hilos del mismo proceso que inicializa el semáforo.
 - 1 : Valor con el que se inicializa el semáforo.

Debe inicializarse en el proceso principal que crea las hebras, 
antes de que las hebras usen los semáforos.
```

Petición de bloqueo del semáforo:
```
sem_wait(&semaforo);

Devuelve distinto de cero en caso de error.
```

Petición de desbloqueo del semáforo:
Indica que el proceso va a salir de la sección crítica y que esta queda libre. La funcion sem_post() desbloquea uno de los procesos bloqueados anteriormente, usando FIFO.
```
sem_post(&semaforo);

Devuelve distinto de cero en caso de error.
```