package cl.aridos.camion.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.aridos.camion.dto.CamionDTO;
import cl.aridos.camion.model.Camion;
import cl.aridos.camion.service.CamionService;

@WebMvcTest(CamionController.class)
class CamionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private CamionService camionService;

    @Test
    void listarCamiones_deberiaRetornarLista() throws Exception {
        Camion c1 = new Camion("ABC123", new BigDecimal("10.5"), 1);
        when(camionService.listar()).thenReturn(Arrays.asList(c1));

        mockMvc.perform(get("/aridos/camiones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].patente").value("ABC123"));
    }

    @Test
    void obtener_cuandoExiste_retornaCamion() throws Exception {
        Camion camion = new Camion("ABC123", new BigDecimal("10.5"), 1);
        when(camionService.buscarPorPatenteCamion("ABC123")).thenReturn(camion);

        mockMvc.perform(get("/aridos/camiones/{id}", "ABC123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente").value("ABC123"));
    }

    @Test
    void obtener_cuandoNoExiste_retorna404() throws Exception {
        when(camionService.buscarPorPatenteCamion("ZZZ999")).thenThrow(new RuntimeException("No existe"));

        mockMvc.perform(get("/aridos/camiones/{id}", "ZZZ999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void crear_cuandoEsValido_retorna201() throws Exception {
        Camion camion = new Camion("ABC123", new BigDecimal("10.5"), 1);
        when(camionService.guardarCamion(any())).thenReturn(camion);

        mockMvc.perform(post("/aridos/camiones")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"patente\":\"ABC123\",\"capacidad\":10.5,\"idConductor\":1}"))
                .andExpect(status().isCreated());
    }

    @Test
    void eliminarCamion_cuandoExiste_retorna204() throws Exception {
        when(camionService.eliminarCamion("ABC123")).thenReturn(true);

        mockMvc.perform(delete("/aridos/camiones/{patente}", "ABC123"))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarCamion_cuandoNoExiste_retorna400() throws Exception {
        when(camionService.eliminarCamion("ZZZ999")).thenThrow(new RuntimeException());

        mockMvc.perform(delete("/aridos/camiones/{patente}", "ZZZ999"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarCamionDTO_cuandoExiste_retornaDTO() throws Exception {
        CamionDTO dto = new CamionDTO();
        dto.setPatente("ABC123");
        when(camionService.buscarCamionDTO("ABC123")).thenReturn(dto);

        mockMvc.perform(get("/aridos/camiones/dto/{patente}", "ABC123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.patente").value("ABC123"));
    }

    @Test
    void buscarCamionDTO_cuandoNoExiste_retorna404() throws Exception {
        when(camionService.buscarCamionDTO("ZZZ999")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/camiones/dto/{patente}", "ZZZ999"))
                .andExpect(status().isNotFound());
    }
}
