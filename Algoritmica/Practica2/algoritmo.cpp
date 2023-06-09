#include "algoritmo.hpp"
#include "serietemporal.cpp"
#include "punto.cpp"
#include "recta.cpp"
#include <string>

using namespace std;


void algoritmoMetodo1(SerieTemporal &serieTemporal, int izq, int der, double eMaxUser, int posicionEMax)
{
    double eMax;
    eMax = serieTemporal.calcularEmaxEntreDosPuntos(izq, der, posicionEMax);

    if (eMax > eMaxUser)
    {
        serieTemporal.dominantePunto(posicionEMax, true);

        int posicionMaximo1 =0;
        int posicionMaximo2 =0;

        algoritmoMetodo1(serieTemporal, izq, posicionEMax, eMaxUser, posicionMaximo1);
        algoritmoMetodo1(serieTemporal, posicionEMax, der, eMaxUser, posicionMaximo2);
    }
}

void algoritmoMetodo2(SerieTemporal &serieTemporal, int izq, int der, double eMaxUser, int posicionEMax)
{
    double eMax;
    eMax = serieTemporal.calcularEmaxEntreDosPuntos(izq, der, posicionEMax);

    if (eMax > eMaxUser)
    {
        serieTemporal.dominantePunto((izq+der)/2, true);

        int posicionMaximo1 = 0;
        int posicionMaximo2 = 0;

        algoritmoMetodo2(serieTemporal, izq, ((izq+der)/2), eMaxUser, posicionMaximo1);
        algoritmoMetodo2(serieTemporal, ((izq+der)/2), der, eMaxUser, posicionMaximo2);
    }
}

void metodo1()
{
    cout <<"Introduzca el error Maximo deseado: ";
    double eMaxUser; 
    cin >> eMaxUser;

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
    
    int posicionEMax;

    serieTemporal.dominantePunto(0, true);
    serieTemporal.dominantePunto(serieTemporal.numeroPuntosSerieTemporal()-1, true);

    algoritmoMetodo1(serieTemporal, 0, serieTemporal.numeroPuntosSerieTemporal()-1, eMaxUser, posicionEMax);

    serieTemporal.guardarSegmentacion(nombreFicheroSalida);

    long double ISE;
    long double error;

    serieTemporal.erroresSegmentacion(ISE, error, posicionEMax);


    cout <<"Numero de puntos dominantes: "<<serieTemporal.contarPuntosDominantes()<<endl;
    cout <<"ISE: "<<ISE<<endl;
    cout <<"Error maximo: "<<error<<endl;
    cout <<"Punto del error maximo: "<<posicionEMax<<endl;
}   

void metodo2()
{
    cout <<"Introduzca el error Maximo deseado: ";
    double eMaxUser;
    cin >> eMaxUser;

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
    
    int posicionEMax;

    serieTemporal.dominantePunto(0, true);
    serieTemporal.dominantePunto(serieTemporal.numeroPuntosSerieTemporal()-1, true);

    algoritmoMetodo2(serieTemporal, 0, serieTemporal.numeroPuntosSerieTemporal()-1, eMaxUser, posicionEMax);

    serieTemporal.guardarSegmentacion(nombreFicheroSalida);

    long double ISE;
    long double error;

    serieTemporal.erroresSegmentacion(ISE, error, posicionEMax);


    cout <<"Numero de puntos dominantes: "<<serieTemporal.contarPuntosDominantes()<<endl;
    cout <<"ISE: "<<ISE<<endl;
    cout <<"Error maximo: "<<error<<endl;
    cout <<"Punto del error maximo: "<<posicionEMax<<endl;
}