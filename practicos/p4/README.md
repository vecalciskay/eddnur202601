# Practico 4 - Pizarra en Red

El objetivo es hacer un programa
que permite colocar figuras en un
panel y que permite enviar estas figuras
a otra instancia de este programa
por red.

## Protocolo

Esto no pueden cambiarlo y debe etar 
implementado tal como eta indicado

### Cuadrado
El programa que desea enviar un 
cuadrado al otro programa debe hacer:

```
>>> FIGURA
<<< OK
>>> CUADRADO 60 100 30 80
<<< OK
```
Los argumentos de CUADRADO son:
x, y, ancho, alto.

### Circulo
El programa que desea enviar un
circulo al otro programa debe hacer:

```
>>> FIGURA
<<< OK
>>> CIRCULO 60 100 50
<<< OK
```
Los argumentos de CIRCULO son:
x, y, diametro.

### Lista de figuras
La primera ve que nos conectamos 
a otra instancia del programa
necesitamos conocer la lista 
actual comlpeta que tiene esa
instancia.

```
>>> LISTA
<<< 2
<<< CIRCULO 60 100 50
<<< CUADRADO 60 100 30 80
>>> OK
```
El numero 2 que nos envian al principio 
es el numero de figuras que se
van a recibir.

### Saludo
Cuando un cliente se conecta
el cliente debe indicar que
conoce el protocolo

```
>>> HOLA
<<< OK
```
### Fin de comunicación
Cuando el cliente se desconecta 
envia un mensaje al servidor
```
>>> CHAU
<<< OK
```

## List de figuras

El programa mantiene dos listas 
de figuras, una que es la lista
de figuras del usuario en ese
momento y la otra lista de figuras
es la del usuario que se conecta.

## Desarrollo del programa

1. El programa se lanza (instancia A)
2. El usuario dibuja una o dos figuras
3. Ejecutar el programa una segunda vez (instancia B)
4. Desde la instancia B saludar
5. Solicitar desde la instancia B la lista
6. Leer la lista completa del servidor
7. Luego el servidor pide la lista que pueda tener el cliente
8. En ese momento cualquiera de las dos instancias puede dibujar una figura que será enviada al cliente
9. Cuando alguno de los dos se quiere desconectar, ejecuta protocolo de salida o desconexión

## Requisitos
1. La lista utiliza una instancia de la Lista vista en clases.
2. Logs
3. Mejoras de interfaz (dibujos del otro en otro color)


## Fecha de presentacion
Vierne 15 de mayo