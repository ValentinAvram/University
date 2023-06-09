#include "Calculos.hpp"

using namespace std;

double sumatorio(vector <double> &n, vector <double> &t, double expN, double expT)
{
	double suma = 0;
	for (int i = 0; i < n.size(); i++)
	{
		suma = suma + ((pow(n[i], expN)) * (pow(t[i], expT)));
	}
	return suma;
}

void calcularTiemposEstimadosNlogN(vector <double> &numeroElementos, vector <double> &a, vector <double> &tiemposEstimados)
{
    double a0 = a[0];
    double a1 = a[1];

    for(int i = 0; i < numeroElementos.size(); i++)
    {
        double estTime = (a0) + (a1 * (numeroElementos[i]) * log10(numeroElementos[i]));
        tiemposEstimados.push_back(estTime);
    }
}

double calcularCoeficienteDeterminacion(vector <double> &tiemposReales, vector <double> &tiemposEstimados)
{
	double mediaTiempoReal = (sumatorio(tiemposReales, tiemposEstimados, 1, 0))/tiemposReales.size();
	double mediaTiempoEstimado = sumatorio(tiemposReales, tiemposEstimados, 0, 1)/tiemposReales.size();

	double varianzaReales = 0;
	for (int i = 0; i < tiemposReales.size(); i++)
	{

		varianzaReales = varianzaReales + pow((tiemposReales[i] - mediaTiempoReal),2);
	}
	varianzaReales = varianzaReales/tiemposReales.size();


	double varianzaEstimados = 0;
	for (int i = 0; i < tiemposEstimados.size(); i++)
	{
		varianzaEstimados = varianzaEstimados + pow((tiemposEstimados[i] - mediaTiempoEstimado), 2);
	}
	varianzaEstimados = varianzaEstimados/tiemposEstimados.size();

	double CoeficienteDet = varianzaEstimados / varianzaReales;

	cout << "\nEl coeficiente de Determinacion es " << CoeficienteDet << endl;

	return CoeficienteDet;
}

double calcularTiempoEstimadoNlogN(double &n, vector <double> &a)
{
    double a0 = a[0];
    double a1 = a[1];

    double estTime = a0 + a1 * (n) * log10(n);
 
	return estTime;
}

void ajustePolinomico(vector <double> &n, vector <double> &tiemposReales, vector <double> &a)
{
	vector < vector < double > > A;
	A = vector< vector< double > >(4, vector< double >(4));

	for(int i = 0; i < 4; i++)
	{
		for(int j = 0; j < 4; j++)
		{

			A[i][j] = sumatorio(n, tiemposReales, i+j, 0);
		}
	}

	vector < vector < double > > B;
	B = vector< vector< double > >(4, vector< double >(1));

	for(int i = 0; i < 4; i++)
	{
		B[i][0] = sumatorio(n, tiemposReales, i, 1);
	}

	vector < vector < double > > X;
	X = vector< vector< double > >(4, vector< double >(1));

  	resolverSistemaEcuaciones(A, B, 4, X);

    for(int i = 0; i < X.size(); i++)
    {
        a.push_back(X[i][0]);
    }
}

void ajusteNlogN(vector <double> &numeroElementos, vector <double> &tiemposReales, 
vector <double> &a)
{
	vector < vector < double > > A;
	A = vector< vector< double > >(2, vector< double >(2));
	
	vector <double> zetaValues;

	for(int i = 0; i < numeroElementos.size(); i++)
	{
		zetaValues.push_back(((numeroElementos[i])*(log10(numeroElementos[i]))));
	}

	for(int i = 0; i < 2; i++)
	{
		for(int j = 0; j < 2; j++)
		{

			A[i][j] = sumatorio(zetaValues, tiemposReales, i+j, 0);
		}
	}

	vector < vector < double > > B;
	B = vector< vector< double > >(2, vector< double >(1));

	for(int i = 0; i < 2; i++)
	{
		B[i][0] = sumatorio(zetaValues, tiemposReales, i, 1);
	}

    
	vector < vector < double > > X;
	X = vector< vector< double > >(2, vector< double >(1));

  	resolverSistemaEcuaciones(A, B, 2, X);

    for(int i = 0; i < X.size(); i++)
    {
        a.push_back(X[i][0]);
    }
}

void calcularTiemposEstimadosPolinomico(vector <double> &n, const vector <double> &a, vector
<double> &tiemposEstimados)
{
	double a0 = a[0];
    double a1 = a[1];
	double a2 = a[2];
	double a3 = a[3];


    for(int i = 0; i < n.size(); i++)
    {
        double estTime = a0 + (a1 * n[i]) + (a2 * pow(n[i],2)) + (a3 * pow(n[i],3));
        tiemposEstimados.push_back(estTime);
    }
}

double calcularTiempoEstimadoPolinomico(const double &n, const vector <double> &a)
{
	double a0 = a[0];
    double a1 = a[1];
	double a2 = a[2];
	double a3 = a[3];

    double estTime = a0 + (a1 * (n)) + (a2 * pow(n, 2)) + (a3 * pow(n, 3));
 
	return estTime;
}

void ajusteFactorial(vector <double> &numeroElementos, vector <double> &tiemposReales,
vector <double> &a)
{
	vector < vector < double > > A;
	A = vector< vector< double > >(2, vector< double >(2));
	
	vector <double> zetaValues;

	for(int i = 0; i < numeroElementos.size(); i++)
	{
		zetaValues.push_back(factorial(numeroElementos[i]));
	}

	for(int i = 0; i < 2; i++)
	{
		for(int j = 0; j < 2; j++)
		{

			A[i][j] = sumatorio(zetaValues, tiemposReales, i+j, 0);
		}
	}

	vector < vector < double > > B;
	B = vector< vector< double > >(2, vector< double >(1));

	for(int i = 0; i < 2; i++)
	{
		B[i][0] = sumatorio(zetaValues, tiemposReales, i, 1);
	}

	
	vector < vector < double > > X;
	X = vector< vector< double > >(2, vector< double >(1));

  	resolverSistemaEcuaciones(A, B, 2, X);

	for(int i = 0; i < X.size(); i++)
	{
		a.push_back(X[i][0]);
	}
}

double factorial(double n)
{
	double factorial = 1;

	for(int i = 1; i <= n; i++)
	{
		factorial = factorial * i;
	}

	return factorial;
}

void calcularTiemposEstimadosFactorial(const vector <double> &n, const vector <double> &tiemposReales, const vector <double> &a, vector
<double> &tiemposEstimados)
{
	double a0 = a[0];
	double a1 = a[1];

	for(int i = 0; i < n.size(); i++)
	{
		double zeta = factorial(n[i]);
		double estTime = a0 + a1 * zeta;
		tiemposEstimados.push_back(estTime);
	}
}

//Calcular tiempo estimado factorial
double calcularTiempoEstimadoFactorial(const double &n, const vector <double> &a)
{
	double a0 = a[0];
	double a1 = a[1];

	double zeta = factorial(n);
	double estTime = a0 + a1 * zeta;

	return estTime;
}