package cl.aridos.pago.controller;

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

import cl.aridos.pago.dto.PagoDTO;
import cl.aridos.pago.model.Pago;
import cl.aridos.pago.model.TipoPago;
import cl.aridos.pago.service.PagoService;

@WebMvcTest(PagoController.class)
class PagoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PagoService pagoService;

    @Test
    void listarPagos_deberiaRetornarLista() throws Exception {
        Pago p1 = new Pago(1, 15000, null);
        when(pagoService.listarPagos()).thenReturn(Arrays.asList(p1));

        mockMvc.perform(get("/aridos/pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void buscarIdPago_cuandoExiste_retornaPago() throws Exception {
        Pago pago = new Pago(1, 15000, null);
        when(pagoService.buscarPorIdPago(1)).thenReturn(pago);

        mockMvc.perform(get("/aridos/pagos/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPago").value(1));
    }

    @Test
    void buscarIdPago_cuandoNoExiste_retorna404() throws Exception {
        when(pagoService.buscarPorIdPago(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/pagos/{id}", 999))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarPago_cuandoEsValido_retorna201() throws Exception {
        Pago pago = new Pago(1, 15000, null);
        when(pagoService.guardarPago(any())).thenReturn(pago);

        mockMvc.perform(post("/aridos/pagos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"costoDespacho\":15000}"))
                .andExpect(status().isCreated());
    }

    @Test
    void listarTipoPagos_deberiaRetornarLista() throws Exception {
        TipoPago tp = new TipoPago(1, "Efectivo");
        when(pagoService.listarTipoPagos()).thenReturn(Arrays.asList(tp));

        mockMvc.perform(get("/aridos/pagos/tipo-pagos"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void buscarPagoDTO_cuandoExiste_retornaDTO() throws Exception {
        PagoDTO dto = new PagoDTO();
        dto.setIdPago(1);
        when(pagoService.buscarPagoDTO(1)).thenReturn(dto);

        mockMvc.perform(get("/aridos/pagos/dto/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idPago").value(1));
    }

    @Test
    void buscarPagoDTO_cuandoNoExiste_retorna404() throws Exception {
        when(pagoService.buscarPagoDTO(999)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/pagos/dto/{id}", 999))
                .andExpect(status().isNotFound());
    }
}
