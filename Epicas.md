0. Creación de celdas
1. Información básica
2. Acceso Indexado
3. Carga/Descarga del Dataframe
4. Visualización
5. Generación y modificación
6. Selección
7. Copia Independiente
8. Concatenación
9. Ordenamiento
10. Imputación
11. Muestreo

Historias de usuario

0.  Esta épica es **prioritaria**

Como usuario, quiero poder crear una celda para poder almacenar un dato que pueda ser de tipo bool, numérico o cadena.
Como usuario, quiero poder indicar si el dato almacenado en una celda es NA.
Como usuario, quiero poder cambiar el estado de NA de una celda.
Como usuario, quiero poder cambiar el dato en una celda.

1.  Como usuario, quiero poder conocer la cantidad de filas y columnas del dataframe
    Como usuario , quiero poder saber cuáles son las etiquetas de las filas y columnas del dataframe
    Como usuario , quiero poder saber cuál es el tipo de dato de cada columna del dataframe

2.

Como usuario, quiero poder acceder a una fila del dataframe a partir de su respectiva etiqueta
Como usuario , quiero poder acceder a una columna del dataframe a partir de su respectiva etiqueta
Como usuario , quiero poder acceder a una celda del dataframe a partir de las etiquetas que la identifiquen

3.

Como usuario, quiero poder cargar desde disco una tabla en formato CSV
Como usuario, quiero poder definir el carácter deliminitador de columnas y si se utilizarán las etiquetas de las columnas a la hora de cargar una tabla desde disco
Como usuario, quiero poder grabar en disco una tabla en formato CSV
Como usuario, quiero poder definir el carácter deliminitador de columnas y si se utilizarán las etiquetas de las columnas a la hora de grabar una tabla en disco (debemos decir que si no se pone NO se utilizará por defecto "," para el delimitador y las etiquetas de las col se indexarán como 0..#col - 1

4.

Como usuario, quiero poder visualizar en "pantalla" el contenido de la tabla de forma que sea fácil de comprender.
Como usuario, quiero a la hora de ver la tabla poder definir un máximo de columnas y/o filas que se mostrarán .
Como usuario, quiero a la hora de ver la tabla poder definir un máximo de carácteres para cada celda (esto es sólo para tipo cadena? ese máximo afecta a la columna entera? o se debe explicitar las celdas ?) que se mostrarán .

5.

Esta épica tiene historias de usuario **prioritarias**

Como usuario, quiero poder generar una tabla a partir de un archivo CSV almacenado en disco.
Como usuario, quiero poder generar una tabla nueva a partir de una copia profunda de otra previamente creada.
Como usuario, quiero poder generar una tabla nueva a partir de una estructura de 2 dimensiones nativa de Java.
Como usuario, quiero poder generar una tabla nueva a partir de una secuencia lineal nativa de Java.
/
/

Como usuario, quiero poder modificar la tabla accediendo a una celda y cambiando su valor (con los NAs pasa = ? ) /
Como usuario, quiero poder modificar la tabla agregando una columna (con igual cantidad de elementos que las filas de la tabla) /
Como usuario, quiero poder modificar la tabla agregando una columna a partir de una secuencia lineal nativa de Java (con igual cantidad de elementos que las filas de la tabla) /

Como usuario, quiero poder modificar la tabla eliminando una columna /

Como usuario, quiero poder modificar la tabla eliminando una fila /

/
/
/

6.

Como usuario, quiero poder ver una parte de la tabla indicando las filas y/o columna de la misma.
Como usuario, quiero poder ver las primeras filas de la tabla indicando cuántas deseo observar. (se pone?)
Como usuario, quiero poder ver las últimas filas de la tabla indicando cuántas deseo observar. (se pone?)

/
/
/

7.

Como usuario, quiero aplicar un filtro para poder visualizar las filas de la tabla que cumplen una condición que se aplica a las columnas /

/
/
/

8.

Como usuario, quiero aplicar un filtro para poder visualizar las filas de la tabla que cumplen una condición que se aplica a las columnas /

/
/
/

9.

Como usuario, quiero poder crear una tabla que resuelte de afectuar una copia profunda a otra tabla /

/
/
/

10.

Como usuario, quiero poder agregar filas de un tabla a otra siempre y cuando las dos posean la misma cantidad de columnas, éstas se encuentren en el mismo orden, sean del mismo tipo y tengan las mismas etiquetas /

/
/
/

11.

Como usuario, quiero poder ordenar las filas de la tabla (ascendente o descendente) aplicando un criterio a una o más columnas /

/
/
/

Como usuario, quiero poder rellenar las celdas con valores faltantes con un valor literal/

/
/
/

12.

Como usuario, quiero poder seleccionar en forma aleatoria una cantidad de filas de la tabla según un porcentaje del total /
