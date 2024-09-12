# Proyecto: **Nombre del Proyecto**

## 1. Objetivo y Alcance

# Objetivo del Proyecto

El proyecto tiene como objetivo desarrollar una **librería en Java** que permita manipular y analizar datos en forma tabular (2 dimensiones). La librería debe proporcionar estructuras de datos y operaciones que faciliten la manipulación de datos tabulares, como lectura, modificación y análisis de los mismos, sin depender de librerías externas.

Además, el proyecto contempla la posibilidad de **futuras extensiones** y debe minimizar el impacto de futuras modificaciones en la arquitectura del sistema. Aunque no se hará foco en la eficiencia de las operaciones inicialmente, se considerará la inclusión de mecanismos para medir el **costo temporal** de la ejecución de las mismas.

# Alcance del Proyecto

El proyecto abarcará los siguientes aspectos:

1. **Implementación de estructuras de datos**: Que permitan almacenar y manipular datos en dos dimensiones.
2. **Operaciones sobre las estructuras de datos**: Como agregar, eliminar, modificar datos, filtrado, y operaciones básicas de análisis.
3. **Extensibilidad**: Se deberá diseñar una arquitectura que permita agregar nuevas funcionalidades en el futuro con mínimo impacto.
4. **Medición de costo temporal**: Incorporación de una función que permita medir el tiempo de ejecución de las operaciones más relevantes.

**Alcance:**  
_Explica los límites y las funcionalidades que estarán incluidas en el proyecto. Ejemplo: El sistema gestionará el stock, generará informes y permitirá la administración de usuarios, pero no incluirá integración con sistemas externos como software contable._

## 2. Descripción de Alto Nivel del Sistema

_Proporciona una descripción general del sistema. Ejemplo: El sistema estará compuesto por tres módulos principales: gestión de inventarios, gestión de usuarios y procesamiento de órdenes de compra. Cada módulo interactuará con una base de datos central para registrar, actualizar y eliminar información._

## 3. Requerimientos Funcionales

_Los principales requerimientos funcionales del sistema incluyen:_

- **Macro-requerimiento 1: Gestión de Inventario**

  - RF 1.1: Registrar productos en inventario
  - RF 1.2: Editar productos existentes
  - RF 1.3: Eliminar productos del inventario
  - RF 1.4: Generar reportes de inventario

- **Macro-requerimiento 2: Gestión de Usuarios**

  - RF 2.1: Crear cuentas de usuario
  - RF 2.2: Editar cuentas de usuario
  - RF 2.3: Asignar roles y permisos
  - RF 2.4: Eliminar cuentas de usuario

- **Macro-requerimiento 3: Procesamiento de Órdenes**
  - RF 3.1: Crear órdenes de compra
  - RF 3.2: Actualizar estado de las órdenes
  - RF 3.3: Generar reportes de órdenes

## 4. Requerimientos No Funcionales

_Los requerimientos no funcionales definen cómo debe comportarse el sistema en términos de rendimiento, seguridad y usabilidad._

- **RNF 1:** Requerimiento 1
- **RNF 2:** Requerimiento 2
- **RNF 3:** ...
- **RNF 4:** Requerimiento n

## 5. Arquitectura del Sistema

_Describe brevemente la arquitectura utilizada. Ejemplo: El sistema utilizará una arquitectura cliente-servidor, donde el frontend estará construido con [framework/lenguaje] y el backend con [framework/lenguaje], conectado a una base de datos relacional/no relacional._
