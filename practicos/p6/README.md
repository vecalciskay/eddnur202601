# Práctico 6

Este práctico se trata de hacer un servidor web que tenga la siguiente funcionalidad.

* El servidor tiene una interfaz gráfica que permite iniciar el servidor y parar el servidor.
* Cuando el servidor se inicia en un puerto específico, el servidor puede responder a las siguientes solicitued HTTP y solamente a éstas solicitudes:

```bash
GET /op?q={EXPRESION CODIFICADA} HTTP/1.1
```

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