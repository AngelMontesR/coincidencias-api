# Examen: Deduplicación de Contactos

## Descripción
Este proyecto es una solución técnica al examen de deduplicación de contactos. La aplicación, desarrollada con Spring Boot, lee un archivo de contactos en formato Excel, identifica posibles pares duplicados y les asigna una puntuación de precisión. Todo el procesamiento se realiza en memoria, sin usar una base de datos, cumpliendo con los requisitos del ejercicio.
La solución expone un servicio RESTful que recibe el archivo de contactos como entrada y devuelve una lista de coincidencias de duplicados en formato JSON.


## Estructura del Proyecto
La estructura del proyecto sigue el patrón de **arquitectura hexagonal** para separar las preocupaciones y hacer el sistema fácilmente mantenible y testeable.
```
│
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── spring.duplicados/
│ │ │ ├── application/
│ │ │ │ ├── DetectarDuplicadosUseCase.java # Lógica de negocio para detectar duplicados
│ │ │ │ └── DuplicadosApplication.java # Clase principal de la aplicación
│ │ │ ├── domain/
│ │ │ │ ├── Coincidencia.java # Modelo de negocio para coincidencias
│ │ │ │ └── Contacto.java # Modelo de negocio para contactos
│ │ │ ├── infrastructure/
│ │ │ │ └── ExcelReaderAdapter.java # Adaptador para leer archivos Excel
│ │ │ └── controller/
│ │ │ └── ContactoController.java # Controlador para manejar las solicitudes HTTP
│ ├── resources/
│ │ ├── application.properties # Configuración de la aplicación
│ │ └── listado.xlsx # Ejemplo de archivo Excel con datos
│ └── test/
│ └── java/
│ └── spring.duplicados/
│ ├── application/
│ │ └── DetectarDuplicadosUseCaseTest.java # Pruebas para la lógica de negocio
| | └── ContactoControllerIntegrationTest.java # Pruebas de integración para el controlador
└── target/ # Archivos generados por la compilación
```

## Funcionalidades
- **Detección de duplicados**: El sistema permite procesar archivos Excel (`listado.xlsx`) y detectar registros duplicados basados en criterios específicos.
- **API RESTful**: El `ContactoController` expone un endpoint para interactuar con el sistema y recibir datos de duplicados.
- **Pruebas de integración**: Se incluyen pruebas de integración para verificar el correcto funcionamiento del sistema.

## Requisitos
- **Java 21**
- **Maven** para gestionar las dependencias y la construcción del proyecto.

## Instalación
1. Clona este repositorio en tu máquina local:
    ```bash
    git clone https://github.com/usuario/duplicados.git
    cd duplicados
    ```
2. Instala las dependencias:
    ```bash
    mvn install
    ```
3. Ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```
    La aplicación se ejecutará en un puerto aleatorio por defecto.
   
## Ejemplo de uso
Para ejecutar el caso de uso de detección de duplicados, debes enviar un archivo Excel a través del siguiente **endpoint** con el parametro **file**:
- **POST /api/duplicacion/procesar**: Este endpoint recibe un archivo Excel y devuelve los registros duplicados encontrados.

**Ejemplo de solicitud**:
```bash
curl -X POST -F "file=@/path/to/your/file.xlsx" http://localhost:8080/api/duplicacion/procesar

