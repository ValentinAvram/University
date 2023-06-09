#!/bin/bash

#cat << _end_ | gnuplot
set terminal postscript eps color
set output "graficoQuicksort.eps"
set key right bottom
set title "Algoritmo Quicksort"
set xlabel "Numero Elementos"
set ylabel "Tiempo (ms)"
plot 'datosFinalesQuicksort.txt' using 1:2 t "Tiempos reales" w l, 'datosFinalesQuicksort.txt' using 1:3 t "Tiempos estimados" w l 
#_end_
