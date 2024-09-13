# Proyecto: **Nombre del Proyecto**

## 1. Objetivo y Alcance

# Objetivo del Proyecto

_El proyecto tiene como objetivo desarrollar una **librería en Java** que permita manipular y analizar datos en forma tabular (2 dimensiones). La librería debe proporcionar estructuras de datos y operaciones que faciliten la manipulación de datos tabulares, como lectura, modificación y análisis de los mismos, sin depender de librerías externas._

_Además, el proyecto contempla la posibilidad de **futuras extensiones** y debe minimizar el impacto de futuras modificaciones en la arquitectura del sistema. Aunque no se hará foco en la eficiencia de las operaciones inicialmente, se considerará la inclusión de mecanismos para medir el **costo temporal** de la ejecución de las mismas._

# Alcance del Proyecto

El proyecto abarcará los siguientes aspectos:

1. **Implementación de estructuras de datos**: Que permitan almacenar y manipular datos en dos dimensiones.
2. **Operaciones sobre las estructuras de datos**: Como agregar, eliminar, modificar datos, filtrado, y operaciones básicas de análisis.
3. **Extensibilidad**: Se deberá diseñar una arquitectura que permita agregar nuevas funcionalidades en el futuro con mínimo impacto.
4. **Medición de costo temporal**: Incorporación de una función que permita medir el tiempo de ejecución de las operaciones más relevantes.

## 2. Descripción de Alto Nivel del Sistema

_La libreria consiste en una biblioteca de java para poder manipular estructuras tabulares de 2 dimensiones, el usuario podra importar esta libreria en su propio proyecto y acceder a los metodos implementados para poder leer, agregar, modificar, filtrar y ordenar los datos provistos por el usuario_

## 3. Requerimientos Funcionales

_Los principales requerimientos funcionales del sistema incluyen:_

- **Macro-requerimiento 1: Implementación de estructuras de datos**

  - RF 1.1: La libreria deberia tener un tipo de dato abstracto que represente una instancia de datos de 2 dimensiones con n filas y m columnas
  - RF 1.2: La libreria deberia implementar etiquetas(labels) para poder indexar filas y columnas
  - RF 1.3: La libreria deberia tener un tipo de dato abstracto para una fila
  - RF 1.4: La libreria deberia tener un tipo de dato abstracto para una columna

- **Macro-requerimiento 2: Operaciones sobre las estructuras de datos**

  - RF 2.1: La libreria debe poder aceptar datos null o NA
  - RF 2.2: La libreria debe poder permitir cambiar los datos null
  - RF 2.3: La libreria debe poder permitir cambiar el dato de una celda
  - RF 2.4: La libreria debe mostrar la cantidad de filas y columnas de la tabla
  - RF 2.5: La libreria debe poder permitir agregar y eliminar filas y columnas de una tabla
  - RF 2.6: La libreria debe permitir saber los tipos de datos de cada columna
  - RF 2.6: La libreria debe permitir saber cuales son las etiquetas de las filas y las columnas

- **Macro-requerimiento 3: Extensibilidad**

  - RF 3.1: Que pueda permitir agregar nuevos tipos de datos para las celdas
  - RF 3.2: Que se pueda cambiar el formato de la visualizacion de las tablas
  - RF 3.3:
  - RF 3.4:

- **Macro-requerimiento 4: Medición de costo temporal**
  - RF 3.1: requerimiento funcional 1
  - RF 3.2: requerimiento funcional 2
  - RF 3.3: requerimiento funcional n
  - RF 3.4: ...

## 4. Requerimientos No Funcionales

_Los requerimientos no funcionales definen cómo debe comportarse el sistema en términos de rendimiento, seguridad y usabilidad._

- **RNF 1:** Requerimiento no funcional 1
- **RNF 2:** Requerimiento no funcional 2
- **RNF 3:** Requerimiento n
- **RNF 4:** ...

## 5. Arquitectura del Sistema

_Describe brevemente la arquitectura utilizada. Ejemplo: El sistema utilizará una arquitectura cliente-servidor, donde el frontend estará construido con [framework/lenguaje] y el backend con [framework/lenguaje], conectado a una base de datos relacional/no relacional._

## Desarrollado por:

- [AgustinRebechi](https://github.com/AgustinRebechi)
- [aecheverri](https://github.com/aecheverri)
- [dabaron13](https://github.com/dabaron13)
- [rriveraro](https://github.com/rriveraro)
- [cvidalsastre](https://github.com/cvidalsastre)
