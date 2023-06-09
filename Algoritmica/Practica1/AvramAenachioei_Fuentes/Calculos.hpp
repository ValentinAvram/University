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

double sumatorio(vector <double> &n, vector <double> &t, double expN, double expT);

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