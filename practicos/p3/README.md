# Práctico 3 - Agrandar / Achicar

El practico consiste en hacer un programa
en Java que tenga una interfaz grafica
que permita leer una imagen de un archivo (JPG, PNG).
Luego la aplicación tiene 1 operación que es
cambiar el tamaño.
El usuario puede colocar un tamaño arbitrario y puede
ademas indicar si se respeta la relación de tamaño de la imagen.

## Algoritmos 

Lo primero que deben reconocer es si 
la dimensión alto o ancho cambiará hacia 
más chico o más grande.

### Achicar

SE selecciona el píxel correspondiente a la
ubicación de la relación entre el
antiguo y el nuevo tamaño. Por ejemplo,
si tengo un arreglo de 10 enteros y lo achico 
a 3 enteros, entonces tomo el primer píxul y lo
pongo en la primera posición, luego se toma
la relación (10 / 3 = 3) y ya sé que tengo
que saltar los 3 próximos píxeles para recien colocar el 2do píxel en el resultado.

### Agrandar

Aquí se tienen varias opciones
posiblees. La primera y la más fácil es
repetir el píxel las veces que ea
necesario (relacion entre antigua y nueva dimension), pero es la peor que se puee utilizar.

El algoritmo que se ebe utilizar es una aproximación lineal para los píxeles intermedios.
Ejemplo: imagen de 3 pixeles crece
a imagen e 15 pixeles. En el canal rojo tenemos:

20, 50, 60 (para los 3 pixeles)

Entre el primer y segundo pixel tenemos 50-20 = 30 tonos de diferencia.
Y tenemos 15px / 3px = 5px a llenar. Entonces se divide 30 tonos entre 5px = 6.

Esto nos permite saber que los 5 primeros pixeles de la imagen de 15 tendr{an los siguiente valores de rojo:
20, (20+6) = 26, 32, 38, 44