package cl.aridos.direccion.service;

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

import cl.aridos.direccion.dto.DireccionDTO;
import cl.aridos.direccion.model.Comuna;
import cl.aridos.direccion.model.Direccion;
import cl.aridos.direccion.model.Region;
import cl.aridos.direccion.repository.ComunaRepository;
import cl.aridos.direccion.repository.DireccionRepository;
import cl.aridos.direccion.repository.RegionRepository;

@ExtendWith(MockitoExtension.class)
class DireccionServiceTest {

    @Mock
    private DireccionRepository direccionRepository;

    @Mock
    private ComunaRepository comunaRepository;

    @Mock
    private RegionRepository regionRepository;

    @InjectMocks
    private DireccionService direccionService;

    // ---- Direccion tests ----

    @Test
    void listarDirecciones_deberiaRetornarLista() {
        when(direccionRepository.findAll()).thenReturn(Arrays.asList(new Direccion(), new Direccion()));

        List<Direccion> resultado = direccionService.listarDirecciones();

        assertEquals(2, resultado.size());
        verify(direccionRepository).findAll();
    }

    @Test
    void buscarPorIdDireccion_cuandoExiste_retornaDireccion() {
        Integer id = 1;
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(id);
        when(direccionRepository.findById(id)).thenReturn(Optional.of(direccion));

        Direccion resultado = direccionService.buscarPorIdDireccion(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdDireccion());
        verify(direccionRepository).findById(id);
    }

    @Test
    void buscarPorIdDireccion_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(direccionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> direccionService.buscarPorIdDireccion(id));
        verify(direccionRepository).findById(id);
    }

    @Test
    void guardarDireccion_guardaYRetorna() {
        Direccion direccion = new Direccion();
        direccion.setCalle("Test");
        when(direccionRepository.save(direccion)).thenReturn(direccion);

        Direccion resultado = direccionService.guardarDireccion(direccion);

        assertNotNull(resultado);
        assertEquals("Test", resultado.getCalle());
        verify(direccionRepository).save(direccion);
    }

    @Test
    void actualizarDireccion_cuandoExiste_actualizaYRetorna() {
        Integer id = 1;
        Direccion existente = new Direccion();
        existente.setIdDireccion(id);
        existente.setCalle("Original");

        Direccion actualizada = new Direccion();
        actualizada.setCalle("Modificada");

        when(direccionRepository.findById(id)).thenReturn(Optional.of(existente));
        when(direccionRepository.save(any(Direccion.class))).thenReturn(existente);

        Direccion resultado = direccionService.actualizarDireccion(id, actualizada);

        assertNotNull(resultado);
        assertEquals("Modificada", resultado.getCalle());
        verify(direccionRepository).findById(id);
        verify(direccionRepository).save(existente);
    }

    @Test
    void actualizarDireccion_cuandoNoExiste_retornaNull() {
        Integer id = 999;
        when(direccionRepository.findById(id)).thenReturn(Optional.empty());

        Direccion resultado = direccionService.actualizarDireccion(id, new Direccion());

        assertNull(resultado);
        verify(direccionRepository).findById(id);
        verify(direccionRepository, never()).save(any());
    }

    @Test
    void eliminarDireccion_cuandoExiste_eliminaYRetornaTrue() {
        Integer id = 1;
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(id);
        when(direccionRepository.findById(id)).thenReturn(Optional.of(direccion));

        boolean resultado = direccionService.eliminarDireccion(id);

        assertTrue(resultado);
        verify(direccionRepository).delete(direccion);
    }

    @Test
    void eliminarDireccion_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(direccionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> direccionService.eliminarDireccion(id));
        verify(direccionRepository, never()).delete(any());
    }

    // ---- Region tests ----

    @Test
    void listarRegiones_deberiaRetornarLista() {
        Region r1 = new Region(1, "Metropolitana");
        when(regionRepository.findAll()).thenReturn(Arrays.asList(r1));

        List<Region> resultado = direccionService.listarRegiones();

        assertEquals(1, resultado.size());
        verify(regionRepository).findAll();
    }

    @Test
    void buscarPorIdRegion_cuandoExiste_retornaRegion() {
        Integer id = 1;
        Region region = new Region(id, "Metropolitana");
        when(regionRepository.findById(id)).thenReturn(Optional.of(region));

        Region resultado = direccionService.buscarPorIdRegion(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdRegion());
        verify(regionRepository).findById(id);
    }

    @Test
    void buscarPorIdRegion_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(regionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> direccionService.buscarPorIdRegion(id));
        verify(regionRepository).findById(id);
    }

    @Test
    void eliminarRegion_cuandoExiste_eliminaYRetornaTrue() {
        Integer id = 1;
        Region region = new Region(id, "Metropolitana");
        when(regionRepository.findById(id)).thenReturn(Optional.of(region));

        boolean resultado = direccionService.eliminarRegion(id);

        assertTrue(resultado);
        verify(regionRepository).delete(region);
    }

    @Test
    void eliminarRegion_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(regionRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> direccionService.eliminarRegion(id));
        verify(regionRepository, never()).delete(any());
    }

    // ---- Comuna tests ----

    @Test
    void listarComunas_deberiaRetornarLista() {
        when(comunaRepository.findAll()).thenReturn(Arrays.asList(new Comuna(), new Comuna(), new Comuna()));

        List<Comuna> resultado = direccionService.listarComunas();

        assertEquals(3, resultado.size());
        verify(comunaRepository).findAll();
    }

    @Test
    void buscarPorIdComuna_cuandoExiste_retornaComuna() {
        Integer id = 1;
        Comuna comuna = new Comuna();
        comuna.setIdComuna(id);
        when(comunaRepository.findById(id)).thenReturn(Optional.of(comuna));

        Comuna resultado = direccionService.buscarPorIdComuna(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdComuna());
        verify(comunaRepository).findById(id);
    }

    @Test
    void buscarPorIdComuna_cuandoNoExiste_retornaNull() {
        Integer id = 999;
        when(comunaRepository.findById(id)).thenReturn(Optional.empty());

        Comuna resultado = direccionService.buscarPorIdComuna(id);

        assertNull(resultado);
        verify(comunaRepository).findById(id);
    }

    @Test
    void eliminarComuna_cuandoExiste_eliminaYRetornaTrue() {
        Integer id = 1;
        Comuna comuna = new Comuna();
        comuna.setIdComuna(id);
        when(comunaRepository.findById(id)).thenReturn(Optional.of(comuna));

        boolean resultado = direccionService.eliminarComuna(id);

        assertTrue(resultado);
        verify(comunaRepository).delete(comuna);
    }

    @Test
    void buscarDireccionDTO_cuandoExiste_retornaDTO() {
        Integer id = 1;
        Direccion direccion = new Direccion();
        direccion.setIdDireccion(id);
        when(direccionRepository.findById(id)).thenReturn(Optional.of(direccion));

        DireccionDTO resultado = direccionService.buscarDireccionDTO(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdDireccion());
        verify(direccionRepository).findById(id);
    }
}
