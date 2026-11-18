package com.acm.ecommerce.controllers;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class VersionControllerTest {

    @Test
    void devuelveLaVersionYElEntornoInyectados() {
        VersionController controller = new VersionController("sha-abc1234", "pruebas");

        Map<String, String> respuesta = controller.version();

        assertEquals("ACM Ecommerce", respuesta.get("app"));
        assertEquals("sha-abc1234", respuesta.get("version"));
        assertEquals("pruebas", respuesta.get("entorno"));
        assertFalse(respuesta.get("mensaje").isBlank());
    }
}
