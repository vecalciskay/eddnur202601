# Practico 5 - Arbol JSON

Realizr un programa que tenga dos ventanas una al lado
de la otra donde la ventana izquierda acepta texto
y la ventana derecha es un panel donde se dibuja el arbol.

## Ventana izquierda
Aqui se tendra un arbol representado en formato
JSON. De la siguiente manera:

```json
{
  "contenido": "D",
  "hijos": [
    {
      "contenido": "N",
      "hijos": [
        {
          "contenido": "C",
          "hijos": []
        },{
          "contenido": "A",
          "hijos": []
        }
      ]
    },{
      "contenido": "E",
      "hijos": []
    },{
      "contenido": "W",
      "hijos": []
    }
  ]
}
```

Cuando se edita el arbol en la parte izquierda, el
usuario puede hacer clic en un boton para actualizar el 
arbol de la derecha.

## Ventana derecha

Representa graficamente el arbol de la ventana izquierda.

Permite tambien realizar las siguientes operaciones:
* Se puede hacer clic sobre un nodo y se habilita las operaciones de eliminar y aumentar hijo
* Si se elige la opcion eliminar se elimina el nodo y todos los subnodos y se actualiza la venta izquierda
* Si se elige la opcion aumentar hijo, se solicita el nombre del hijo y se añade el nodo, y se actualiza la venta izquierda

## PAtrones de diseño y librerias

* Observer
* Logger
* MVC
* Json

## Presentacion

El practico debe presentarse el viernes 19 de junio