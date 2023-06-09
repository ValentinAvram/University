<<
  Asignatura:    Procesadores de Lenguajes

  Titulación:    Ingeniería Informática
  Especialidad:  Computación
  Curso:         Tercero
  Cuatrimestre:  Segundo

  Departamento:  Informática y Análisis Numérico
  Centro:        Escuela Politécnica Superior de Córdoba
  Universidad de Córdoba
 
  Curso académico: 2022 - 2023

  Fichero de ejemplo para interpreter.exe
>>

# Bienvenida

clear_screen;
place(10,10);
print_string('Introduce tu nombre --> ');
read_string(nombre);

clear_screen;
place(10,10);
print_string(' Bienvenido/a << ');
print_string(nombre);
print_string(' >> a \'interpreter.exe\'.');

place(40,10);
print_string('Pulsa una tecla para continuar ');
read_string( pausa);

repeat 

 # Opciones disponibles

 clear_screen;
 place(10,10);
 print_string(' Bienvenido al ejemplo Fibbonacci');

 place(11,10);
 print_string(' Escriba un numero ');

 place(12,10);
 print_string(' Finalizar ---------------> 0 ');

 place(15,10);
 print_string(' Escriba un numero, sera su iteracion de la serie de Fibbonacci ');

 read(opcion);

clear_screen;

a := 0;
b := 1;

for i from 2 to opcion+1 do
  c := a + b;
  a := b;
  b := c;

    print(c);
end_for;

print_string('El resultado es: ');
print(c);


place(40,10);
print_string('Pulsa una tecla para continuar');
read_string( pausa);
read_string( pausa);


 
until (opcion = 0);             

# Despedida final
clear_screen;
place(10,10);
print_string('El programa ha concluido');
clear_screen;
place(0,0);