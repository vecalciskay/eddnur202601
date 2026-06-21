# Práctico 6

Este práctico se trata de hacer un servidor web que tenga la siguiente funcionalidad.

* El servidor tiene una interfaz gráfica que permite iniciar el servidor y parar el servidor.
* Cuando el servidor se inicia en un puerto específico, el servidor puede responder a las siguientes solicitued HTTP y solamente a éstas solicitudes:

```bash
GET /op?q={EXPRESION CODIFICADA} HTTP/1.1
```

La expresión es una expresión aritmética correcta como puede ser 4+7. El programa debe devolver al 
navegador una imagen mostrando el árbol aritmético utilizado y en algún lugar de la imagen 
(preferentemente abajo a la derecha) mostrar también el resultado de evaluar la expresión aritmética.

## Codificación URL

En HTTP no se puede colocar símbolos como +, *, (, ) porque llevan otros significados. Por ello estos se deben codificar

| Normal                   | Codificado                                      |
|--------------------------|-------------------------------------------------|
| (4/3)*(6-((8+4.3)+9))    | %284%2F3%29%2A%286-%28%288%2B4.3%29%2B9%29%29   |

Entonces, para el valor de q más arriba, se debe utilizar la expresión codificada y no la expresión normal.
Luego en Java se puede recuperar esa expresión codificada de esta manera:

```java
String expresion = URLDecoder.decode(encoded, StandardCharsets.UTF_8);
logger.info("La expresion es: " + expresion);
```

## Empezar y parar el servidor

La ventana debe tener dos botones claros para parar y comenzar el servidor. Esto quiere decir que de
alguna manera se comienza el thread principal y se lo detiene. En general, la buena práctica indica
no utilizar los métodos stop o kill de Thread sino más bien utilizar un boolean para indicar que 
se debe parar o no el proceso.

## Observer

Utilizar observer sobre el servidor para poder ver en la interfaz el estado del servidor. Esto significa
que la ventana (JFrame) que tiene los botones de comenzar y parar es el observador del servidor (y por ende de todos los threads que ocurren con ese objeto).

## Fecha de presentación

El práctico se debe presentar el 30 de Junio de 2026.