package cl.aridos.camion.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import cl.aridos.camion.client.ConductorClient;
import cl.aridos.camion.dto.CamionDTO;
import cl.aridos.camion.dto.ConductorDTO;
import cl.aridos.camion.model.Camion;
import cl.aridos.camion.repository.CamionRepository;

@ExtendWith(MockitoExtension.class)
class CamionServiceTest {

    @Mock
    private CamionRepository camionRepository;

    @Mock
    private ConductorClient conductorClient;

    @InjectMocks
    private CamionService camionService;

    @Test
    void listarCamiones_deberiaRetornarLista() {
        Camion c1 = new Camion("ABC1234", new BigDecimal("10.5"), 1);
        Camion c2 = new Camion("XYZ5678", new BigDecimal("15.0"), 2);
        when(camionRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Camion> resultado = camionService.listarCamiones();

        assertEquals(2, resultado.size());
        verify(camionRepository).findAll();
    }

    @Test
    void listar_deberiaRetornarLista() {
        Camion c1 = new Camion("ABC1234", new BigDecimal("10.5"), 1);
        when(camionRepository.findAll()).thenReturn(Arrays.asList(c1));

        List<Camion> resultado = camionService.listar();

        assertEquals(1, resultado.size());
        verify(camionRepository).findAll();
    }

    @Test
    void buscarPorPatenteCamion_cuandoExiste_retornaCamion() {
        String patente = "ABC1234";
        Camion camion = new Camion(patente, new BigDecimal("10.5"), 1);
        when(camionRepository.findById(patente)).thenReturn(Optional.of(camion));

        Camion resultado = camionService.buscarPorPatenteCamion(patente);

        assertNotNull(resultado);
        assertEquals(patente, resultado.getPatente());
        verify(camionRepository).findById(patente);
    }

    @Test
    void buscarPorPatenteCamion_cuandoNoExiste_lanzaExcepcion() {
        String patente = "ZZZ9999";
        when(camionRepository.findById(patente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> camionService.buscarPorPatenteCamion(patente));
        verify(camionRepository).findById(patente);
    }

    @Test
    void guardarCamion_cuandoConductorExiste_guardaYRetorna() {
        Camion camion = new Camion("ABC1234", new BigDecimal("10.5"), 1);
        when(conductorClient.buscarConductorPorId(1)).thenReturn(new ConductorDTO());
        when(camionRepository.save(camion)).thenReturn(camion);

        Camion resultado = camionService.guardarCamion(camion);

        assertNotNull(resultado);
        assertEquals("ABC1234", resultado.getPatente());
        verify(conductorClient).buscarConductorPorId(1);
        verify(camionRepository).save(camion);
    }

    @Test
    void guardarCamion_cuandoConductorNoExiste_lanzaExcepcion() {
        Camion camion = new Camion("ABC1234", new BigDecimal("10.5"), 1);
        when(conductorClient.buscarConductorPorId(1)).thenReturn(null);

        assertThrows(RuntimeException.class, () -> camionService.guardarCamion(camion));
        verify(conductorClient).buscarConductorPorId(1);
        verify(camionRepository, never()).save(any());
    }

    @Test
    void eliminarCamion_cuandoExiste_eliminaYRetornaTrue() {
        String patente = "ABC1234";
        Camion camion = new Camion(patente, new BigDecimal("10.5"), 1);
        when(camionRepository.findById(patente)).thenReturn(Optional.of(camion));

        boolean resultado = camionService.eliminarCamion(patente);

        assertTrue(resultado);
        verify(camionRepository).delete(camion);
    }

    @Test
    void eliminarCamion_cuandoNoExiste_lanzaExcepcion() {
        String patente = "ZZZ9999";
        when(camionRepository.findById(patente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> camionService.eliminarCamion(patente));
        verify(camionRepository, never()).delete(any());
    }

    @Test
    void buscarCamionDTO_cuandoExiste_retornaDTO() {
        String patente = "ABC1234";
        Camion camion = new Camion(patente, new BigDecimal("10.5"), 1);
        when(camionRepository.findById(patente)).thenReturn(Optional.of(camion));

        CamionDTO resultado = camionService.buscarCamionDTO(patente);

        assertNotNull(resultado);
        assertEquals(patente, resultado.getPatente());
        verify(camionRepository).findById(patente);
    }

    @Test
    void buscarCamionDTO_cuandoNoExiste_lanzaExcepcion() {
        String patente = "ZZZ9999";
        when(camionRepository.findById(patente)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> camionService.buscarCamionDTO(patente));
        verify(camionRepository).findById(patente);
    }
}
