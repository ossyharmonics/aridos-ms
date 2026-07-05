package cl.aridos.conductor.controller;

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

import cl.aridos.conductor.dto.ConductorDTO;
import cl.aridos.conductor.model.Conductor;
import cl.aridos.conductor.service.ConductorService;

@WebMvcTest(ConductorController.class)
class ConductorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ConductorService conductorService;

    @Test
    void listar_deberiaRetornarLista() throws Exception {
        Conductor c1 = new Conductor(1, "A4");
        Conductor c2 = new Conductor(2, "A2");
        when(conductorService.listar()).thenReturn(Arrays.asList(c1, c2));

        mockMvc.perform(get("/aridos/conductores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void buscarPorIdConductor_cuandoExiste_retornaConductor() throws Exception {
        Conductor conductor = new Conductor(1, "A4");
        when(conductorService.buscarPorIdConductor(1)).thenReturn(conductor);

        mockMvc.perform(get("/aridos/conductores/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idConductor").value(1))
                .andExpect(jsonPath("$.licencia").value("A4"));
    }

    @Test
    void buscarPorIdConductor_cuandoNoExiste_retorna404() throws Exception {
        when(conductorService.buscarPorIdConductor(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/conductores/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearConductor_cuandoEsValido_retorna201() throws Exception {
        Conductor conductor = new Conductor(1, "A5");
        when(conductorService.guardarConductor(any())).thenReturn(conductor);

        mockMvc.perform(post("/aridos/conductores")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"licencia\":\"A5\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void eliminarConductor_cuandoExiste_retorna204() throws Exception {
        when(conductorService.eliminarConductor(1)).thenReturn(true);

        mockMvc.perform(delete("/aridos/conductores/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarConductor_cuandoNoExiste_retorna400() throws Exception {
        when(conductorService.eliminarConductor(999)).thenThrow(new RuntimeException());

        mockMvc.perform(delete("/aridos/conductores/{id}", 999))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarConductorDTO_cuandoExiste_retornaDTO() throws Exception {
        ConductorDTO dto = new ConductorDTO();
        dto.setIdConductor(1);
        when(conductorService.buscarConductorDTO(1)).thenReturn(dto);

        mockMvc.perform(get("/aridos/conductores/dto/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idConductor").value(1));
    }

    @Test
    void buscarConductorDTO_cuandoNoExiste_retorna404() throws Exception {
        when(conductorService.buscarConductorDTO(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/conductores/dto/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
