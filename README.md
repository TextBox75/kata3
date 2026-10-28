# IS2 Kata 3

### Objetivo
El objetivo de esta kata es el de aprender a serializar y deserializar objetos en formato JSON en Java, trabajando con flujos de datos procedentes tanto de URLs como de ficheros locales para la generación y persistencia de reportes estructurados.

### Como compilar y ejecutar
Clonar el repositorio con `git clone https://github.com/TextBox75/kata3.git` y abrir la carpeta con IntelliJ IDEA y OpenJDK 25.
Finalmente, ejecutar la clase Main. En los microproyectos también se incluyen pruebas de verificación que se pueden ejecutar.
Las dependencias de este proyecto incluyen JUnit Jupiter para testing y la librería Gson para la serialización JSON.

### Estructura de la entrega
El proyecto se divide en 3 branches principales:

- Master branch: El primer microproyecto, encargado de procesar datos de vehículos leyendo desde la fuente de datos correspondiente, calcular sus métricas y serializar/deserializar el reporte resultante en formato JSON. La clase principal es Car.

- Titanic branch: El segundo microproyecto, encargado de leer y analizar el dataset del Titanic para calcular métricas sobre los pasajeros y serializar el informe correspondiente en JSON. La clase principal es Passenger.

- Wine branch: El tercer microproyecto, enfocado en el análisis de datos vinícolas para evaluar criterios de calidad de los vinos y generar su respectivo reporte serializado en JSON. La clase principal es Wine.

Todas las branches siguen una misma estructura de arquitectura limpia organizada en puertos y adaptadores:
- El modelo del dominio bajo el paquete software.ulpgc.katas.model (incluyendo clases para métricas, reportes y serializadores).
- Las interfaces y puertos bajo software.ulpgc.katas.ports.
- Los lectores y escritores adaptadores bajo software.ulpgc.katas.adapters (incluyendo lectores y escritores de reportes JSON).

Cabe mencionar que la clase principal de cada modelo solo contiene una parte de la totalidad de los atributos que se cargan desde las fuentes de datos con el propósito de simplificar el trabajo.   

El main se encarga de orquestar todos los componentes de los microproyectos. Cada componente tiene una única responsabilidad bien definida: leer datos desde las fuentes (fichero local o URL), calcular métricas derivadas, serializar los reportes a JSON y persistirlos o leerlos.   

Los componentes de entrada y salida implementan adaptadores específicos para la manipulación y almacenamiento de la información serializada en JSON.

### Enlace al vídeo
(PENDIENTE)

### Verificación
La verificación se ha realizado mediante pruebas automatizadas de JUnit Jupiter. Hay un único fichero de pruebas que estudia
el comportamiento del serializador y deserializador JSON según los casos descritos en la kata.