# Plan de desarrollo - Practico 1: Cartas

## Objetivo

Desarrollar una aplicacion de consola que represente una baraja de 52 cartas, la inicialice en orden, la mezcle con un algoritmo, calcule metricas comparando el orden mezclado contra el orden original e imprima una interpretacion breve de los resultados.

## Condiciones del trabajo

El programa debe cumplir estas condiciones:

* Cada carta debe ser un objeto de la clase `Carta`.
* La clase `Carta` debe guardar:
  * `palo`: Corazones, Diamantes, Treboles, Picas.
  * `valor`: A, 2, 3, ..., 10, J, Q, K.
  * `posicionOriginal`: numero entre 0 y 51.
* La baraja debe representarse en un arreglo de 52 cartas.
* La distancia de una carta se calcula con:

```text
distancia = |posicionActual - posicionOriginal|
```

* Se deben calcular:
  * distancia minima.
  * distancia maxima.
  * distancia promedio.
* Se debe escribir una conclusion breve sobre que tan mezclada quedo la baraja.

## Ubicacion y paquete raiz

El codigo fuente del practico se creara dentro de la carpeta `src/main`.

En un proyecto Maven, la carpeta `src/main/java` es la raiz de codigo fuente. Por eso el paquete raiz para todas las clases del practico sera:

```java
package nur.prog3.practicos.p1;
```

Por lo tanto, las clases deben ubicarse en una estructura de carpetas equivalente al paquete:

```text
src/main/java/nur/prog3/practicos/p1/
```

Las clases principales del practico, como `Carta` y la clase con el metodo `main`, deben declarar ese paquete al inicio del archivo.

No se debe usar un paquete que empiece con `java`, por ejemplo `java.nur.prog3.practicos.p1`, porque `java.*` esta reservado por la plataforma Java y la JVM no permite ejecutar clases de aplicacion en ese espacio de nombres.

## Paso 1: Definir la estructura del programa

Crear una aplicacion de consola con al menos estas partes:

* Clase `Carta`: representa una carta individual.
* Clase principal del programa: contiene el metodo `main`.
* Funciones o metodos auxiliares para:
  * crear la baraja ordenada.
  * mostrar la baraja.
  * mezclar la baraja.
  * calcular las distancias.
  * mostrar los resultados.
  * generar una conclusion.

## Paso 2: Crear la clase Carta

Definir la clase `Carta` con los atributos requeridos:

```java
package nur.prog3.practicos.p1;

class Carta {
    String palo;
    String valor;
    int posicionOriginal;
}
```

Agregar un constructor para crear cartas de forma clara:

```java
Carta(String palo, String valor, int posicionOriginal)
```

Agregar un metodo `toString()` para mostrar la carta en pantalla. Por ejemplo:

```text
A de Corazones
10 de Picas
K de Diamantes
```

## Paso 3: Inicializar los datos base

Definir dos arreglos auxiliares:

```java
String[] palos = {"Corazones", "Diamantes", "Treboles", "Picas"};
String[] valores = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
```

Estos arreglos permiten construir las 52 cartas combinando cada palo con cada valor.

## Paso 4: Crear la baraja ordenada

Crear un arreglo:

```java
Carta[] baraja = new Carta[52];
```

Llenar el arreglo en orden usando dos ciclos:

```java
int posicion = 0;

for (int i = 0; i < palos.length; i++) {
    for (int j = 0; j < valores.length; j++) {
        baraja[posicion] = new Carta(palos[i], valores[j], posicion);
        posicion++;
    }
}
```

Al terminar este paso, cada carta conoce su posicion original. Esa posicion no debe modificarse aunque la carta cambie de lugar al mezclar.

## Paso 5: Mostrar la baraja original

Antes de mezclar, imprimir la baraja ordenada para verificar que se creo correctamente.

Para cada posicion del arreglo mostrar:

```text
posicionActual - carta - posicionOriginal
```

Ejemplo:

```text
0 - A de Corazones - original: 0
1 - 2 de Corazones - original: 1
```

## Paso 6: Elegir el algoritmo de mezcla

Usar el algoritmo de Fisher-Yates, porque recorre la baraja e intercambia cada carta con otra posicion seleccionada al azar.

La idea del algoritmo es:

1. Recorrer el arreglo desde la ultima posicion hasta la segunda.
2. Para cada posicion actual `i`, elegir una posicion aleatoria `j` entre 0 e `i`.
3. Intercambiar las cartas en las posiciones `i` y `j`.

Pseudocodigo:

```text
para i desde 51 hasta 1:
    j = numero aleatorio entre 0 e i
    intercambiar baraja[i] con baraja[j]
```

Este algoritmo es adecuado porque todas las posiciones tienen oportunidad de cambiar y no depende de mover cartas manualmente una por una.

## Paso 7: Implementar la mezcla

Implementar un metodo:

```java
static void mezclar(Carta[] baraja)
```

Dentro del metodo:

```java
Random random = new Random();

for (int i = baraja.length - 1; i > 0; i--) {
    int j = random.nextInt(i + 1);

    Carta temporal = baraja[i];
    baraja[i] = baraja[j];
    baraja[j] = temporal;
}
```

Este bloque es importante para la revision, porque corresponde al algoritmo que se debe poder escribir en papel y lapiz.

## Paso 8: Mostrar la baraja mezclada

Despues de mezclar, volver a imprimir las 52 cartas.

Para cada carta mostrar:

```text
posicionActual - carta - posicionOriginal - distancia
```

La distancia ayuda a ver cuanto se alejo cada carta de su lugar inicial.

## Paso 9: Calcular la distancia de cada carta

Para cada posicion actual `i` del arreglo:

```java
int distancia = Math.abs(i - baraja[i].posicionOriginal);
```

Guardar o usar esa distancia para calcular las metricas.

## Paso 10: Calcular distancia minima

Inicializar la minima con un valor alto:

```java
int distanciaMinima = Integer.MAX_VALUE;
```

En cada carta:

```java
if (distancia < distanciaMinima) {
    distanciaMinima = distancia;
}
```

La distancia minima indica la carta que menos se movio. Si es 0, al menos una carta quedo en su posicion original.

## Paso 11: Calcular distancia maxima

Inicializar la maxima en 0:

```java
int distanciaMaxima = 0;
```

En cada carta:

```java
if (distancia > distanciaMaxima) {
    distanciaMaxima = distancia;
}
```

La distancia maxima indica la carta que mas se alejo de su posicion original.

## Paso 12: Calcular distancia promedio

Sumar todas las distancias:

```java
int sumaDistancias = 0;
```

En cada carta:

```java
sumaDistancias += distancia;
```

Luego calcular:

```java
double distanciaPromedio = (double) sumaDistancias / baraja.length;
```

La distancia promedio sirve como criterio general para saber cuanto se movieron las cartas en conjunto.

## Paso 13: Agregar metricas complementarias

Para responder mejor a las preguntas de revision, tambien conviene calcular:

* Cantidad de cartas que quedaron en su misma posicion.
* Cantidad de cartas que cambiaron de posicion.
* Porcentaje de cartas movidas.

Ejemplo:

```java
if (distancia == 0) {
    cartasSinMover++;
} else {
    cartasMovidas++;
}
```

Porcentaje:

```java
double porcentajeMovidas = cartasMovidas * 100.0 / baraja.length;
```

Estas metricas no reemplazan las solicitadas, pero ayudan a justificar si la baraja esta realmente mezclada.

## Paso 14: Interpretar los resultados

Definir criterios simples para la conclusion:

* Si muchas cartas tienen distancia 0, la mezcla fue debil.
* Si la distancia promedio es baja, las cartas se movieron poco.
* Si la distancia maxima es alta, al menos una carta se movio bastante.
* Si el porcentaje de cartas movidas es alto, la baraja probablemente esta mejor mezclada.

Una posible interpretacion:

```text
La baraja esta mezclada porque la mayoria de las cartas cambio de posicion.
La distancia promedio muestra cuanto se alejaron en promedio de su posicion original.
La distancia maxima muestra que algunas cartas se desplazaron bastante.
```

## Paso 15: Escribir la conclusion

Al final del programa imprimir una conclusion breve, por ejemplo:

```text
Conclusion:
La baraja quedo mezclada porque 49 de 52 cartas cambiaron de posicion.
La distancia promedio fue 17.35, lo que indica que las cartas se alejaron considerablemente de su orden original.
Sin embargo, 3 cartas quedaron en su posicion inicial, por lo que la mezcla no es perfecta, aunque si es aceptable.
```

La conclusion debe usar los valores reales calculados durante la ejecucion.

## Paso 16: Probar el programa

Ejecutar el programa varias veces y verificar:

* La baraja siempre tiene 52 cartas.
* No se pierde ninguna carta durante la mezcla.
* No se duplican cartas.
* La `posicionOriginal` de cada carta se mantiene igual.
* Las distancias son siempre mayores o iguales a 0.
* La distancia minima, maxima y promedio se imprimen correctamente.
* Los resultados cambian entre ejecuciones porque la mezcla usa numeros aleatorios.

## Paso 17: Preparar respuestas para la revision

### Como saber si la baraja esta realmente mezclada

Comparando la posicion actual de cada carta con su posicion original. Si la mayoria de las cartas tiene distancia mayor que 0, entonces la baraja cambio respecto al orden inicial.

### Cuan bien mezclada esta la baraja

Se puede analizar con la distancia minima, maxima, promedio y el porcentaje de cartas movidas. Una distancia promedio mas alta y pocas cartas sin mover indican una mejor mezcla.

### Criterios para saber si esta bien mezclada

Los criterios principales son:

* pocas cartas quedaron en su posicion original.
* muchas cartas tienen distancia alta.
* la distancia promedio no es cercana a 0.
* no se observan bloques grandes de cartas que sigan en el mismo orden.

### Algoritmo para mezclar

El algoritmo que se debe poder explicar en papel es:

```text
para cada posicion i desde el final de la baraja hasta 1:
    elegir una posicion aleatoria j entre 0 e i
    guardar baraja[i] en una variable temporal
    copiar baraja[j] en baraja[i]
    copiar la variable temporal en baraja[j]
```

## Orden recomendado de implementacion

1. Crear la carpeta `src/main/java/nur/prog3/practicos/p1/`.
2. Crear las clases dentro del paquete `nur.prog3.practicos.p1`.
3. Crear la clase `Carta`.
4. Crear el arreglo de 52 cartas.
5. Inicializar la baraja ordenada.
6. Mostrar la baraja original.
7. Implementar el algoritmo de mezcla.
8. Mostrar la baraja mezclada.
9. Calcular la distancia de cada carta.
10. Calcular distancia minima, maxima y promedio.
11. Calcular cartas movidas y cartas sin mover.
12. Imprimir una conclusion usando las metricas.
13. Ejecutar pruebas manuales con varias corridas.
