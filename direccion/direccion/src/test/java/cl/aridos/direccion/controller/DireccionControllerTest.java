package cl.aridos.direccion.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.aridos.direccion.dto.DireccionDTO;
import cl.aridos.direccion.model.Comuna;
import cl.aridos.direccion.model.Direccion;
import cl.aridos.direccion.model.Region;
import cl.aridos.direccion.service.DireccionService;

@WebMvcTest(DireccionController.class)
class DireccionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DireccionService direccionService;

    // ---- Regiones ----

    @Test
    void listarRegiones_deberiaRetornarLista() throws Exception {
        Region r1 = new Region(1, "Metropolitana");
        when(direccionService.listarRegiones()).thenReturn(Arrays.asList(r1));

        mockMvc.perform(get("/aridos/direcciones/regiones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void buscarPorIdRegion_cuandoExiste_retornaRegion() throws Exception {
        Region region = new Region(1, "Metropolitana");
        when(direccionService.buscarPorIdRegion(1)).thenReturn(region);

        mockMvc.perform(get("/aridos/direcciones/regiones/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idRegion").value(1));
    }

    @Test
    void buscarPorIdRegion_cuandoNoExiste_retorna404() throws Exception {
        when(direccionService.buscarPorIdRegion(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/direcciones/regiones/{id}", 999))
                .andExpect(status().isNotFound());
    }

    // ---- Comunas ----

    @Test
    void listarComunas_deberiaRetornarLista() throws Exception {
        Comuna c1 = new Comuna();
        c1.setIdComuna(1);
        when(direccionService.listarComunas()).thenReturn(Arrays.asList(c1));

        mockMvc.perform(get("/aridos/direcciones/comunas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    // ---- Direcciones ----

    @Test
    void listarDirecciones_deberiaRetornarLista() throws Exception {
        Direccion d1 = new Direccion();
        d1.setIdDireccion(1);
        when(direccionService.listarDirecciones()).thenReturn(Arrays.asList(d1));

        mockMvc.perform(get("/aridos/direcciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void buscarPorIdDireccion_cuandoExiste_retornaDireccion() throws Exception {
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(1);
        when(direccionService.buscarPorIdDireccion(1)).thenReturn(direccion);

        mockMvc.perform(get("/aridos/direcciones/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDireccion").value(1));
    }

    @Test
    void buscarPorIdDireccion_cuandoNoExiste_retorna404() throws Exception {
        when(direccionService.buscarPorIdDireccion(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/direcciones/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarDireccionDTO_cuandoExiste_retornaDTO() throws Exception {
        DireccionDTO dto = new DireccionDTO();
        dto.setIdDireccion(1);
        when(direccionService.buscarDireccionDTO(1)).thenReturn(dto);

        mockMvc.perform(get("/aridos/direcciones/dto/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDireccion").value(1));
    }

    @Test
    void buscarDireccionDTO_cuandoNoExiste_retorna404() throws Exception {
        when(direccionService.buscarDireccionDTO(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/direcciones/dto/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
