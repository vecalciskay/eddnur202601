# Practico 2

El objetivo es realizar un juego 
en Java.

El juego consiste en que al
comenzar comienzan a caer
al azar circulos desde la 
parte superior de la pantalla.
Con el ratón tratar de hacer clic en 
los circulos en movimiento para eliminarlos.

Caerán 20 circulos desde la parte
superior y el programa contará
los puntos que ha obtenido
el usuario al final.

## Arquitectura
Clase Espacio que contiene a todos los circulos
Clase Circulo 

Panel subclase de JPanel que 
dibuja el Espacio cada vez
que algun circulo cambia de posicion
o es eliminado.

El circulo se puede dar cuenta 
cuando lo han eliminado y debe
notificar.

## Threads
Al comenzar el jeugo comienza un thread
que crea los circulos y para cada
uno de ellos crea los threads que
hacen que los circulos se muevan.

## Fecha de presentacion

Viernes 10 de abril