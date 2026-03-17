# Practico 1 - Cartas

Programar una aplicación de consola que tenga la 
funcionalidad general siguiente:

* Representar una baraja de 52 cartas en un arreglo.
* Inicializar el arreglo en su forma original ordenada.
* Barajar el arreglo usando un algoritmo.
* Calcular métricas cuantitativas comparando la baraja mezclada con el orden original.
* Interpretar los resultados y escribir una breve conclusión.

## Condiciones

* La carta debe ser un objeto de la clase Carta
  * palo: Corazones, Diamantes, Tréboles, Picas 
  * valor: A, 2, 3, ..., 10, J, Q, K 
  * posición original: 0 a 51

Para calcular la distancia se puede utilizar:

distancia = |posiciónActual - posiciónOriginal|

Luego calcular:

* distancia mínima
* distancia máxima 
* distancia promedio

## Presentación

El práctico será presentado el martes 24 de Marzo de 2026

Preguntas en la revisión:
* Cómo saben si está realmente mezclada la baraja?
* Cuán bien mezclada está la baraja?
* Cuales son los criterios que ha encontrado para saber si la baraja está bien mezclada?
* Escriba en papel y lapiz el algoritmo para mezclar (lo que está dentro del for)

````
Ac 2c ....Qc Kc
1   2     12 13

Ac coloco en pos 13 y Kc va a 1
   Kc 2c ....Qc Ac
Or 1   2     12 13
Ac 13  2     12 1
````