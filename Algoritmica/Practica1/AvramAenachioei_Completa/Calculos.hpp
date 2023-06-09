#ifndef CALCULOS_HPP
#define CALCULOS_HPP

#include <iostream>
#include <algorithm>  
#include <vector> 
#include <cmath>
#include <cstdlib>
#include <fstream> 

using namespace std;

void ajusteNlogN(const vector <double> &numeroElementos, const vector <double> &tiemposReales, 
vector <double> &a);

void resolverSistemaEcuaciones(vector < vector < double > > A, vector < vector < double > > B, int n, 
vector < vector < double > > &X);

double sumatorio(vector <double> &n, vector <double> &t, double expN, double expT);

void inicializarInversa(vector < vector < double > >  &inversa);

void trianguloInferior(vector < vector < double > > &A, vector < vector < double > > &inversa);

bool  eliminarCero(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x);

void hacerCerosColumna(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x);

void combinarFilas(vector < vector < double > > &matriz, unsigned int filaOrigen, double factor, unsigned int filaDestino);

double productoDiagonal(const vector < vector < double > > &matriz);

void trianguloSuperior(vector < vector < double > > &A, vector < vector < double > > &inversa);

int eliminarCero2(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x);

void hacerCerosColumna2(vector < vector < double > > &A, vector < vector < double > > &inversa, unsigned int x);

void obtenerUnidad(vector < vector < double > > &A, vector < vector < double > > &inversa);

void multiplicarMatrices(const vector < vector < double > > &m1, const vector < vector < double > > &m2, vector < vector < double > > &producto);

void calcularTiemposEstimadosNlogN(const vector <double> &numeroElementos, const vector <double> &a, vector <double> &tiemposEstimados);

double calcularCoeficienteDeterminacion(const vector <double> &tiemposReales, const vector <double> &tiemposEstimados);

double calcularTiempoEstimadoNlogN(const double &n, vector <double> &a);

void ajustePolinomico(const vector <double> &n, const vector <double> &tiemposReales, vector <double> &a);

void calcularTiemposEstimadosPolinomico(const vector <double> &n, const vector <double> &a, vector
<double> &tiemposEstimados);

double calcularTiempoEstimadoPolinomico(const double &n, const vector <double> &a);

void ajusteFactorial(vector <double> &numeroElementos, vector <double> &tiemposReales, vector <double> &a);

double factorial(double n);

void calcularTiemposEstimadosFactorial(const vector <double> &n, const vector <double> &tiemposReales, const vector <double> &a, vector
<double> &tiemposEstimados);

double calcularTiempoEstimadoFactorial(const double &n, const vector <double> &a);
#endif