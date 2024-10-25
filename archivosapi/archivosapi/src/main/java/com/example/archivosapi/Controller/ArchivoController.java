package com.example.archivosapi.Controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controlador para manejar la carga y entrega de archivos de diferentes tipos
 * (JPG, HTML, XML, JSON, PDF) desde la ruta de recursos estáticos del proyecto.
 *
 * @version 1.0
 * @since 2024-10-24
 * Juan Camilo Arias Ospina
 */
@RestController
@RequestMapping("/apiarchivos")
public class ArchivoController {

    // Tipos MIME como constantes para evitar errores y mejorar legibilidad
    private static final String MIME_TYPE_PNG = "image/png";
    private static final String MIME_TYPE_HTML = "text/html";
    private static final String MIME_TYPE_XML = "application/xml";
    private static final String MIME_TYPE_JSON = "application/json";
    private static final String MIME_TYPE_PDF = "application/pdf";

    /**
     * Carga una imagen JPG desde la ruta especificada.
     *
     * @param nombre Nombre del archivo de imagen (sin extensión)
     * @return Respuesta HTTP con el recurso de imagen solicitado
     */
    @GetMapping("/imagen/{nombre}")
    public ResponseEntity<Resource> cargarImagen(@PathVariable String nombre) {
        return cargarArchivo("static/imagen/" + nombre + ".png", MIME_TYPE_PNG);
    }

    /**
     * Carga un archivo HTML desde la ruta especificada.
     *
     * @param nombre Nombre del archivo HTML (sin extensión)
     * @return Respuesta HTTP con el recurso HTML solicitado
     */
    @GetMapping("/html/{nombre}")
    public ResponseEntity<Resource> cargarHtml(@PathVariable String nombre) {
        return cargarArchivo("static/html/" + nombre + ".html", MIME_TYPE_HTML);
    }

    /**
     * Carga un archivo XML desde la ruta especificada.
     *
     * @param nombre Nombre del archivo XML (sin extensión)
     * @return Respuesta HTTP con el recurso XML solicitado
     */
    @GetMapping("/xml/{nombre}")
    public ResponseEntity<Resource> cargarXml(@PathVariable String nombre) {
        return cargarArchivo("static/xml/" + nombre + ".xml", MIME_TYPE_XML);
    }

    /**
     * Carga un archivo JSON desde la ruta especificada.
     *
     * @param nombre Nombre del archivo JSON (sin extensión)
     * @return Respuesta HTTP con el recurso JSON solicitado
     */
    @GetMapping("/json/{nombre}")
    public ResponseEntity<Resource> cargarJson(@PathVariable String nombre) {
        return cargarArchivo("static/json/" + nombre + ".json", MIME_TYPE_JSON);
    }

    /**
     * Carga un archivo PDF desde la ruta especificada.
     *
     * @param nombre Nombre del archivo PDF (sin extensión)
     * @return Respuesta HTTP con el recurso PDF solicitado
     */
    @GetMapping("/pdf/{nombre}")
    public ResponseEntity<Resource> cargarPdf(@PathVariable String nombre) {
        return cargarArchivo("static/pdf/" + nombre + ".pdf", MIME_TYPE_PDF);
    }

    /**
     * Método auxiliar para cargar archivos desde una ruta especificada y devolver
     * una respuesta HTTP.
     *
     * @param rutaArchivo Ruta relativa al archivo en el classpath
     * @param tipoMime Tipo MIME del archivo a cargar
     * @return Respuesta HTTP con el recurso solicitado
     */
    private ResponseEntity<Resource> cargarArchivo(String rutaArchivo, String tipoMime) {
        try {
            Resource archivo = new ClassPathResource(rutaArchivo);
            if (!archivo.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            HttpHeaders encabezados = new HttpHeaders();
            encabezados.add(HttpHeaders.CONTENT_TYPE, tipoMime);
            return ResponseEntity.ok().headers(encabezados).body(archivo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


// Método privado para servir archivos desde el directorio 'resources/static'
    private ResponseEntity<Resource> serveFile(String filePath, String contentType) {
        Resource resource = new ClassPathResource(filePath);
        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_TYPE, contentType);
        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    }
}
