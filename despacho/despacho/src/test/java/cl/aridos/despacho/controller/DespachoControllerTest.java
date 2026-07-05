package cl.aridos.despacho.controller;

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

import cl.aridos.despacho.model.Despacho;
import cl.aridos.despacho.service.DespachoService;

@WebMvcTest(DespachoController.class)
class DespachoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private DespachoService despachoService;

    @Test
    void listar_deberiaRetornarLista() throws Exception {
        Despacho d1 = new Despacho(1, "11-1", "22-2", 1, "ABC123", 1);
        when(despachoService.listar()).thenReturn(Arrays.asList(d1));

        mockMvc.perform(get("/aridos/despachos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void buscarPorIdDespacho_cuandoExiste_retornaDespacho() throws Exception {
        Despacho despacho = new Despacho(1, "11-1", "22-2", 1, "ABC123", 1);
        when(despachoService.buscarPorIdDespacho(1)).thenReturn(despacho);

        mockMvc.perform(get("/aridos/despachos/{idDespacho}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idDespacho").value(1));
    }

    @Test
    void buscarPorIdDespacho_cuandoNoExiste_retorna404() throws Exception {
        when(despachoService.buscarPorIdDespacho(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/despachos/{idDespacho}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void crearDespacho_cuandoEsValido_retorna201() throws Exception {
        Despacho despacho = new Despacho(1, "11-1", "22-2", 1, "ABC123", 1);
        when(despachoService.guardarDespacho(any())).thenReturn(despacho);

        mockMvc.perform(post("/aridos/despachos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rutEmpresa\":\"11-1\",\"rutPersona\":\"22-2\",\"idDireccion\":1,\"patente\":\"ABC123\",\"idPago\":1}"))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarDespacho_cuandoExiste_retorna200() throws Exception {
        Despacho despacho = new Despacho(1, "11-1", "22-2", 1, "ABC123", 1);
        when(despachoService.actualizarDespacho(eq(1), any())).thenReturn(despacho);

        mockMvc.perform(put("/aridos/despachos/{idDespacho}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rutEmpresa\":\"11-1\",\"rutPersona\":\"22-2\",\"idDireccion\":1,\"patente\":\"ABC123\",\"idPago\":1}"))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarDespacho_cuandoExiste_retorna204() throws Exception {
        when(despachoService.eliminarDespacho(1)).thenReturn(true);

        mockMvc.perform(delete("/aridos/despachos/{idDespacho}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarDespacho_cuandoNoExiste_retorna400() throws Exception {
        when(despachoService.eliminarDespacho(999)).thenThrow(new RuntimeException());

        mockMvc.perform(delete("/aridos/despachos/{idDespacho}", 999))
                .andExpect(status().isBadRequest());
    }
}
