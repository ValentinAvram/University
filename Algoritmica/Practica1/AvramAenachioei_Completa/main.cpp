#include "Quicksort.cpp"
#include "DeterminanteIterativo.cpp"
#include "DeterminanteRecursivo.cpp"

using namespace std;

void printMenu()
{
    cout << "Por favor, introduzca una de las opciones permitidas" << endl;
    cout << "1 - Algoritmo Quicksort" << endl;
    cout << "2 - Calculo de un determinante de forma iterativa" << endl;
    cout << "3 - Calculo de un determinante de forma recursiva" << endl;
}

int main()
{
    system("clear");
    printMenu();

    int opc;
    cin >> opc;

    switch(opc)
    {
        case 1:
        system("clear");
        algoritmoQuicksort();
        break;

        case 2:
        system("clear");
        DeterminanteIterativo();
        break;

        case 3:
        system("clear");
        algoritmoRecursivo();
        break;

        default:
        system("clear");
        cout << "Opcion invalida. Por favor, introduzca una opcion valida " <<endl;
        
        printMenu();

        cin >> opc; 

        break;     
    }
}