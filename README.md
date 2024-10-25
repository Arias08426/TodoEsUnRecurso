# Archivo API

Este proyecto es una API creada en Java usando Spring Boot para cargar y servir archivos de diferentes tipos (imagen, HTML, XML, JSON, PDF) desde la ruta de recursos estáticos del proyecto.

Cada endpoint permite cargar un tipo de archivo específico. Para utilizar estos endpoints, asegúrate de que el servidor esté ejecutándose en el puerto `8080` (puedes ajustarlo en `application.properties` si es necesario).

### Formato de la URL

- URL base: `http://localhost:8080/apiarchivos`
- Para cada tipo de archivo, usa el endpoint correspondiente y el nombre del archivo (sin extensión).

## Instrucciones para el Uso de la API
# Cargar IMAGEN
GET http://localhost:8080/apiarchivos/imagen/Spring

# Cargar HTML
GET http://localhost:8080/apiarchivos/html/beneficios

# Cargar XML
GET http://localhost:8080/apiarchivos/xml/introduccion

# Cargar JSON
GET http://localhost:8080/apiarchivos/json/importante

# Cargar PDF
GET http://localhost:8080/apiarchivos/pdf/Design Pattern
