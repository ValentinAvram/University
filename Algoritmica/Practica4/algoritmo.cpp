#include "algoritmo.hpp"
#include "punto.hpp"
#include "recta.hpp"
#include <string.h>
#include "reloj.cpp"

using namespace std;


void segmentacionMinISE(SerieTemporal &serie, int segPoints)
{
    int N = serie.numeroPuntosSerieTemporal();
    int M = segPoints;

    vector< vector<double> > errores(N, vector<double>(N));
    vector< vector<int> > padres(N, vector<int>(N));

    errores[0][0] = 0;
    for(int i = 0; i < N; i++)
    {
        errores[i][0] = 9999;
    }

    for(int i = 1; i < N; i++)
    {
        for(int j = 1; j < M; j++)
        {
            if(i < j)
            {
                errores[i][j] = 9999;
            }
            else
            {
                errores[i][j] = 9999;
                for(int k = 0; k < i; k++)
                {
                    double error = errores[k][j-1] + serie.calcularIseEntreDosPuntos(k, i);
                    if(error < errores[i][j])
                    {
                        errores[i][j] = error;
                        padres[i][j] = k;
                    }
                }
            }
        }
    }

    vector<int> puntosDominantes;
    
    int aux = padres[N-1][M-1];
    puntosDominantes.push_back(aux); 

    for(int i = M-1; i > 0; i--)
    {
        puntosDominantes.push_back(padres[aux][i]);
        aux = padres[aux][i];
    }
    puntosDominantes.push_back(N-1);

    for(int i = 0; i < puntosDominantes.size(); i++)
    {
        serie.dominantePunto(puntosDominantes[i], true);
    }
}

void metodo1()
{
    cout <<"Introduzca el numero de puntos de la serie segmentada: ";
    double segPoints; 
    cin >> segPoints;

    cout << "Introduzca el nombre del archivo de entrada: ";
    string nombreArchivo;
    cin >> nombreArchivo;
    char *nombreFicheroEntrada = new char[nombreArchivo.length() + 1];
    strcpy(nombreFicheroEntrada, nombreArchivo.c_str());

    cout << "Introduzca el nombre del archivo de salida: ";
    string nombreArchivoSalida;
    cin >> nombreArchivoSalida;
    char *nombreFicheroSalida = new char[nombreArchivoSalida.length() + 1];
    strcpy(nombreFicheroSalida, nombreArchivoSalida.c_str());

    Clock reloj;
    double tiempoEjec;
    
    SerieTemporal serie(nombreFicheroEntrada);

    reloj.start();
    segmentacionMinISE(serie, segPoints);       
    
    if(reloj.isStarted())
    {
        reloj.stop();
        tiempoEjec = reloj.elapsed();
    }

    serie.guardarSegmentacion(nombreFicheroSalida);

    long double ISE, eMax;
    int posicionEMax;

    serie.erroresSegmentacion(ISE, eMax, posicionEMax);

    cout << "Tiempo de ejecucion: " << tiempoEjec << " milisegundos" << endl;
    cout << "ISE: " << ISE << endl;
    cout << "eMax: " << eMax << endl;
    cout << "Posicion eMax: " << posicionEMax << endl;

}   

void metodo2()
{
    cout<<"No implementado\n";
}