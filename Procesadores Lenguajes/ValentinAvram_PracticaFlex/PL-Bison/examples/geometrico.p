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
place(15,10);
print_string('Ejemplo realizado con la estructura Case, usando constantes!');

place(40,10);
print_string('Pulsa una tecla para continuar ');
read_string( pausa);


repeat 

 # Opciones disponibles

 clear_screen;
 place(10,10);
 print_string(' Area Rectangulo --> 1 ');

 place(11,10);
 print_string(' Area Circulo ----> 2 ');

 place(12,10);
 print_string(' Area Triangulo --> 3 ');

 place(13,10);
 print_string(' Finalizar ---------------> 0 ');

 place(16,10);
 print_string(' Elige una opcion ');

 read(opcion);

 clear_screen;

 case (opcion)
    value 1:
        place(10,10);
        print_string('Introduce la base del rectangulo --> ');
        read(base);
        place(11,10);
        print_string('Introduce la altura del rectangulo --> ');
        read(altura);
        area := base * altura;
        place(12,10);
        print_string('El area del rectangulo es ');
        print(area);
        print_string('\n');
    
    value 2:
        place(10,10);
        print_string('Introduce el radio del circulo --> ');
        read(radio);
        area := PI * radio * radio;
        place(11,10);
        print_string('El area del circulo es ');
        print(area);
        print_string('\n');

    value 3:
        place(10,10);
        print_string('Introduce la base del triangulo --> ');
        read(base);
        place(11,10);
        print_string('Introduce la altura del triangulo --> ');
        read(altura);
        area := base * altura / 2;
        place(12,10);
        print_string('El area del triangulo es ');
        print(area);
        print_string('\n');
    
    default:
        place(10,10);
        print_string('Saliendo del programa');
        print_string('\n');

end_case;
                            

 place(40,10); 
 print_string('\n Pulse una tecla para continuar --> ');
 read_string(pausa);
 read_string(pausa);

until (opcion = 0);             

# Despedida final
clear_screen;
place(10,10);
print_string('El programa ha concluido');
clear_screen;
place(0,0);
