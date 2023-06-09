#include "algoritmo.hpp"
#include "serietemporal.hpp"
#include "punto.hpp"
#include "recta.hpp"
#include <string.h>
#include <vector>

using namespace std;

void minimoValorVector(vector<long double> &v, long double &minimo, int &posicion)
{
    minimo = v[0];
    for (int i = 1; i < v.size(); i++)
    {
        if (v[i] < minimo)
        {
            minimo = v[i];
            posicion = i;
        }
    }
}

void algoritmoMinimizarEMax(SerieTemporal &serieTemporal, int izq, int der, double segPoints, vector<int> &puntosDominantes)
{
    for(int i = 0; i < serieTemporal.numeroPuntosSerieTemporal(); i++)
    {
        puntosDominantes.push_back(i);
    }

    vector<long double> erroresDominantes;
    erroresDominantes.push_back(999);
    int auxpos;
    for(int i = 1; i < puntosDominantes.size()-1; i++)
    {
        long double ISE = serieTemporal.calcularEmaxEntreDosPuntos(i-1, i+1, auxpos);
        erroresDominantes.push_back(ISE);
    }
    erroresDominantes.push_back(999);

    while(puntosDominantes.size() > segPoints)
    {

        long double minError;
        int posicionError; 
        minimoValorVector(erroresDominantes, minError, posicionError);

        puntosDominantes.erase(puntosDominantes.begin() + posicionError);
        erroresDominantes.erase(erroresDominantes.begin() + posicionError);

        if(posicionError -1 != 0)
        {
            erroresDominantes[posicionError-1] = 
            serieTemporal.calcularEmaxEntreDosPuntos(puntosDominantes[posicionError-2], 
            puntosDominantes[posicionError], auxpos);            
        }
        if(puntosDominantes[posicionError] != serieTemporal.numeroPuntosSerieTemporal()-1)
        {
            erroresDominantes[posicionError] = 
            serieTemporal.calcularEmaxEntreDosPuntos(puntosDominantes[posicionError-1], 
            puntosDominantes[posicionError+1], auxpos);            
        }
    }

    for(int i = 0; i < puntosDominantes.size(); i++)
    {
        serieTemporal.dominantePunto(puntosDominantes[i], true);
    }

}

void algoritmoMinimizarISE(SerieTemporal &serieTemporal, int izq, int der, double segPoints, vector<int> &puntosDominantes)
{
    for(int i = 0; i < serieTemporal.numeroPuntosSerieTemporal(); i++)
    {
        puntosDominantes.push_back(i);
    }

    vector<long double> erroresDominantes;
    erroresDominantes.push_back(999);
    for(int i = 1; i < puntosDominantes.size()-1; i++)
    {
        long double ISE = serieTemporal.calcularIseEntreDosPuntos(i-1, i+1);
        erroresDominantes.push_back(ISE);
    }
    erroresDominantes.push_back(999);

    while(puntosDominantes.size() > segPoints)
    {

        long double minError;
        int posicionError; 
        minimoValorVector(erroresDominantes, minError, posicionError);

        puntosDominantes.erase(puntosDominantes.begin() + posicionError);
        erroresDominantes.erase(erroresDominantes.begin() + posicionError);

        if(posicionError -1 != 0)
        {
            erroresDominantes[posicionError-1] = 
            serieTemporal.calcularIseEntreDosPuntos(puntosDominantes[posicionError-2], 
            puntosDominantes[posicionError]);            
        }
        if(puntosDominantes[posicionError] != serieTemporal.numeroPuntosSerieTemporal()-1)
        {
            erroresDominantes[posicionError] = 
            serieTemporal.calcularIseEntreDosPuntos(puntosDominantes[posicionError-1], 
            puntosDominantes[posicionError+1]);            
        }
    }

    for(int i = 0; i < puntosDominantes.size(); i++)
    {
        serieTemporal.dominantePunto(puntosDominantes[i], true);
    }
}

void metodo1()
{
    cout <<"Introduzca el numero de puntos de la serie segmentada: ";
    double segPoints; 
    cin >> segPoints;

    cout << "Introduzca el nombre del archivo de texto: ";
    string nombreArchivo;
    cin >> nombreArchivo;
    char *nombreFicheroEntrada = new char[nombreArchivo.length() + 1];
    strcpy(nombreFicheroEntrada, nombreArchivo.c_str());

    
    cout <<"Introduzca el nombre del archivo de salida: ";
    string nombreArchivoSalida;
    cin >> nombreArchivoSalida;
    char *nombreFicheroSalida = new char[nombreArchivoSalida.length() + 1];
    strcpy(nombreFicheroSalida, nombreArchivoSalida.c_str());
    SerieTemporal serieTemporal(nombreFicheroEntrada);
    
    vector<int> puntosDominantes;
    algoritmoMinimizarISE(serieTemporal, 0, serieTemporal.numeroPuntosSerieTemporal() - 1, segPoints, puntosDominantes);

    serieTemporal.guardarSegmentacion(nombreFicheroSalida);

    long double ISE;
    long double error;
    int posicionEMax;

    serieTemporal.erroresSegmentacion(ISE, error, posicionEMax);

    cout <<"Numero de puntos dominantes: "<<serieTemporal.contarPuntosDominantes()<<endl;
    cout <<"ISE: "<<ISE<<endl;
    cout <<"Error maximo: "<<error<<endl;
    cout <<"Punto del error maximo: "<<posicionEMax<<endl;
}   

void metodo2()
{
    cout <<"Introduzca el numero de puntos de la serie segmentada: ";
    double segPoints; 
    cin >> segPoints;

    cout << "Introduzca el nombre del archivo de texto: ";
    string nombreArchivo;
    cin >> nombreArchivo;
    char *nombreFicheroEntrada = new char[nombreArchivo.length() + 1];
    strcpy(nombreFicheroEntrada, nombreArchivo.c_str());

    
    cout <<"Introduzca el nombre del archivo de salida: ";
    string nombreArchivoSalida;
    cin >> nombreArchivoSalida;
    char *nombreFicheroSalida = new char[nombreArchivoSalida.length() + 1];
    strcpy(nombreFicheroSalida, nombreArchivoSalida.c_str());
    SerieTemporal serieTemporal(nombreFicheroEntrada);
    
    vector<int> puntosDominantes;
    algoritmoMinimizarEMax(serieTemporal, 0, serieTemporal.numeroPuntosSerieTemporal() - 1, segPoints, puntosDominantes);

    serieTemporal.guardarSegmentacion(nombreFicheroSalida);

    long double ISE;
    long double error;
    int posicionEMax;

    serieTemporal.erroresSegmentacion(ISE, error, posicionEMax);

    cout <<"Numero de puntos dominantes: "<<serieTemporal.contarPuntosDominantes()<<endl;
    cout <<"ISE: "<<ISE<<endl;
    cout <<"Error maximo: "<<error<<endl;
    cout <<"Punto del error maximo: "<<posicionEMax<<endl;
}