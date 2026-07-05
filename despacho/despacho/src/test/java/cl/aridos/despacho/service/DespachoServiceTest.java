package cl.aridos.despacho.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.aridos.despacho.client.CamionClient;
import cl.aridos.despacho.client.DireccionClient;
import cl.aridos.despacho.client.EmpresaClient;
import cl.aridos.despacho.client.PagoClient;
import cl.aridos.despacho.client.PersonaClient;
import cl.aridos.despacho.dto.CamionDTO;
import cl.aridos.despacho.dto.DireccionDTO;
import cl.aridos.despacho.dto.EmpresaDTO;
import cl.aridos.despacho.dto.PagoDTO;
import cl.aridos.despacho.dto.PersonaDTO;
import cl.aridos.despacho.model.Despacho;
import cl.aridos.despacho.repository.DespachoRepository;

@ExtendWith(MockitoExtension.class)
class DespachoServiceTest {

    @Mock
    private DespachoRepository despachoRepository;

    @Mock
    private CamionClient camionClient;

    @Mock
    private DireccionClient direccionClient;

    @Mock
    private EmpresaClient empresaClient;

    @Mock
    private PagoClient pagoClient;

    @Mock
    private PersonaClient personaClient;

    @InjectMocks
    private DespachoService despachoService;

    @Test
    void listar_deberiaRetornarLista() {
        Despacho d1 = new Despacho(1, "11-1", "22-2", 1, "ABC123", 1);
        Despacho d2 = new Despacho(2, "33-3", "44-4", 2, "XYZ789", 2);
        when(despachoRepository.findAll()).thenReturn(Arrays.asList(d1, d2));

        List<Despacho> resultado = despachoService.listar();

        assertEquals(2, resultado.size());
        verify(despachoRepository).findAll();
    }

    @Test
    void buscarPorIdDespacho_cuandoExiste_retornaDespacho() {
        Integer id = 1;
        Despacho despacho = new Despacho(id, "11-1", "22-2", 1, "ABC123", 1);
        when(despachoRepository.findById(id)).thenReturn(Optional.of(despacho));

        Despacho resultado = despachoService.buscarPorIdDespacho(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdDespacho());
        verify(despachoRepository).findById(id);
    }

    @Test
    void buscarPorIdDespacho_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(despachoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> despachoService.buscarPorIdDespacho(id));
        verify(despachoRepository).findById(id);
    }

    @Test
    void guardarDespacho_cuandoTodosLosClientesResponden_guardaYRetorna() {
        Despacho despacho = new Despacho(null, "11-1", "22-2", 1, "ABC123", 1);

        when(camionClient.obtenerCamionPorPatente("ABC123")).thenReturn(new CamionDTO());
        when(direccionClient.obtenerDireccionPorId(1)).thenReturn(new DireccionDTO());
        when(empresaClient.obtenerEmpresaPorRut("11-1")).thenReturn(new EmpresaDTO());
        when(pagoClient.obtenerPagoPorId(1)).thenReturn(new PagoDTO());
        when(personaClient.buscarPersonaPorRut("22-2")).thenReturn(new PersonaDTO());
        when(despachoRepository.save(despacho)).thenReturn(new Despacho(1, "11-1", "22-2", 1, "ABC123", 1));

        Despacho resultado = despachoService.guardarDespacho(despacho);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdDespacho());
        verify(camionClient).obtenerCamionPorPatente("ABC123");
        verify(direccionClient).obtenerDireccionPorId(1);
        verify(empresaClient).obtenerEmpresaPorRut("11-1");
        verify(pagoClient).obtenerPagoPorId(1);
        verify(personaClient).buscarPersonaPorRut("22-2");
        verify(despachoRepository).save(despacho);
    }

    @Test
    void guardarDespacho_cuandoCamionNoExiste_lanzaExcepcion() {
        Despacho despacho = new Despacho(null, "11-1", "22-2", 1, "INVALID", 1);

        when(camionClient.obtenerCamionPorPatente("INVALID")).thenReturn(null);

        assertThrows(RuntimeException.class, () -> despachoService.guardarDespacho(despacho));
        verify(camionClient).obtenerCamionPorPatente("INVALID");
        verify(despachoRepository, never()).save(any());
    }

    @Test
    void actualizarDespacho_guardaYRetorna() {
        Despacho despacho = new Despacho(1, "11-1", "22-2", 1, "ABC123", 1);
        when(despachoRepository.save(despacho)).thenReturn(despacho);

        Despacho resultado = despachoService.actualizarDespacho(1, despacho);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdDespacho());
        verify(despachoRepository).save(despacho);
    }

    @Test
    void eliminarDespacho_cuandoExiste_eliminaYRetornaTrue() {
        Integer id = 1;
        Despacho despacho = new Despacho(id, "11-1", "22-2", 1, "ABC123", 1);
        when(despachoRepository.findById(id)).thenReturn(Optional.of(despacho));

        boolean resultado = despachoService.eliminarDespacho(id);

        assertTrue(resultado);
        verify(despachoRepository).delete(despacho);
    }

    @Test
    void eliminarDespacho_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(despachoRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> despachoService.eliminarDespacho(id));
        verify(despachoRepository, never()).delete(any());
    }
}
