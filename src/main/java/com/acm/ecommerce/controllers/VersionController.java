package com.acm.ecommerce.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Endpoint de verificación del despliegue (CI/CD).
 * - Lo usa el Application Load Balancer como health check.
 * - Muestra la versión (sha-xxxxxxx) que inyecta el pipeline en APP_VERSION.
 */
@Tag(name = "Version", description = "Verificación del despliegue CI/CD")
@RestController
public class VersionController {

    // 👉 DEMO EN VIVO: cambia este texto, haz git push a main y observa el despliegue
    public static final String MENSAJE = "Hola desde AWS ECS Fargate - v1";

    private final String version;
    private final String entorno;

    public VersionController(@Value("${APP_VERSION:local}") String version,
                             @Value("${APP_ENV:desarrollo}") String entorno) {
        this.version = version;
        this.entorno = entorno;
    }

    @Operation(summary = "Versión desplegada", description = "Retorna el mensaje, la versión (commit) y el entorno.")
    @GetMapping("/api/v1/version")
    public Map<String, String> version() {
        Map<String, String> body = new LinkedHashMap<>();
        body.put("app", "ACM Ecommerce");
        body.put("mensaje", MENSAJE);
        body.put("version", version);
        body.put("entorno", entorno);
        return body;
    }
}
