package cl.aridos.pago.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.aridos.pago.dto.PagoDTO;
import cl.aridos.pago.model.Pago;
import cl.aridos.pago.model.TipoPago;
import cl.aridos.pago.repository.PagoRepository;
import cl.aridos.pago.repository.TipoPagoRepository;

@ExtendWith(MockitoExtension.class)
class PagoServiceTest {

    @Mock
    private PagoRepository pagoRepository;

    @Mock
    private TipoPagoRepository tipoPagoRepository;

    @InjectMocks
    private PagoService pagoService;

    // ---- Pago tests ----

    @Test
    void listarPagos_deberiaRetornarLista() {
        when(pagoRepository.findAll()).thenReturn(Arrays.asList(new Pago(), new Pago()));

        List<Pago> resultado = pagoService.listarPagos();

        assertEquals(2, resultado.size());
        verify(pagoRepository).findAll();
    }

    @Test
    void buscarPorIdPago_cuandoExiste_retornaPago() {
        Integer id = 1;
        Pago pago = new Pago();
        pago.setIdPago(id);
        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));

        Pago resultado = pagoService.buscarPorIdPago(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdPago());
        verify(pagoRepository).findById(id);
    }

    @Test
    void buscarPorIdPago_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(pagoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> pagoService.buscarPorIdPago(id));
        verify(pagoRepository).findById(id);
    }

    @Test
    void guardarPago_guardaYRetorna() {
        Pago pago = new Pago();
        pago.setCostoDespacho(15000);
        when(pagoRepository.save(pago)).thenReturn(pago);

        Pago resultado = pagoService.guardarPago(pago);

        assertNotNull(resultado);
        assertEquals(15000, resultado.getCostoDespacho());
        verify(pagoRepository).save(pago);
    }

    @Test
    void eliminarPago_cuandoExiste_eliminaYRetornaTrue() {
        Integer id = 1;
        Pago pago = new Pago();
        pago.setIdPago(id);
        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));

        boolean resultado = pagoService.eliminarPago(id);

        assertTrue(resultado);
        verify(pagoRepository).delete(pago);
    }

    @Test
    void eliminarPago_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(pagoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> pagoService.eliminarPago(id));
        verify(pagoRepository, never()).delete(any());
    }

    // ---- TipoPago tests ----

    @Test
    void listarTipoPagos_deberiaRetornarLista() {
        when(tipoPagoRepository.findAll()).thenReturn(Arrays.asList(new TipoPago(), new TipoPago(), new TipoPago()));

        List<TipoPago> resultado = pagoService.listarTipoPagos();

        assertEquals(3, resultado.size());
        verify(tipoPagoRepository).findAll();
    }

    @Test
    void buscarPorIdTipoPago_cuandoExiste_retornaTipoPago() {
        Integer id = 1;
        TipoPago tipoPago = new TipoPago();
        tipoPago.setIdTipoPago(id);
        when(tipoPagoRepository.findById(id)).thenReturn(Optional.of(tipoPago));

        TipoPago resultado = pagoService.buscarPorIdTipoPago(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdTipoPago());
        verify(tipoPagoRepository).findById(id);
    }

    @Test
    void buscarPorIdTipoPago_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(tipoPagoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> pagoService.buscarPorIdTipoPago(id));
        verify(tipoPagoRepository).findById(id);
    }

    @Test
    void eliminarTipoPago_cuandoExiste_eliminaYRetornaTrue() {
        Integer id = 1;
        TipoPago tipoPago = new TipoPago();
        tipoPago.setIdTipoPago(id);
        when(tipoPagoRepository.findById(id)).thenReturn(Optional.of(tipoPago));

        boolean resultado = pagoService.eliminarTipoPago(id);

        assertTrue(resultado);
        verify(tipoPagoRepository).delete(tipoPago);
    }

    @Test
    void eliminarTipoPago_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(tipoPagoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> pagoService.eliminarTipoPago(id));
        verify(tipoPagoRepository, never()).delete(any());
    }

    @Test
    void buscarPagoDTO_cuandoExiste_retornaDTO() {
        Integer id = 1;
        Pago pago = new Pago();
        pago.setIdPago(id);
        when(pagoRepository.findById(id)).thenReturn(Optional.of(pago));

        PagoDTO resultado = pagoService.buscarPagoDTO(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdPago());
        verify(pagoRepository).findById(id);
    }
}
