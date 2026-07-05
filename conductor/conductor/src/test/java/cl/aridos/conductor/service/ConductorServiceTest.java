package cl.aridos.conductor.service;

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

import cl.aridos.conductor.dto.ConductorDTO;
import cl.aridos.conductor.model.Conductor;
import cl.aridos.conductor.repository.ConductorRepository;

@ExtendWith(MockitoExtension.class)
class ConductorServiceTest {

    @Mock
    private ConductorRepository conductorRepository;

    @InjectMocks
    private ConductorService conductorService;

    @Test
    void listarConductores_deberiaRetornarLista() {
        Conductor c1 = new Conductor(1, "A4");
        Conductor c2 = new Conductor(2, "A2");
        when(conductorRepository.findAll()).thenReturn(Arrays.asList(c1, c2));

        List<Conductor> resultado = conductorService.listarConductores();

        assertEquals(2, resultado.size());
        verify(conductorRepository).findAll();
    }

    @Test
    void listar_deberiaRetornarLista() {
        Conductor c1 = new Conductor(1, "A4");
        when(conductorRepository.findAll()).thenReturn(Arrays.asList(c1));

        List<Conductor> resultado = conductorService.listar();

        assertEquals(1, resultado.size());
        verify(conductorRepository).findAll();
    }

    @Test
    void buscarPorIdConductor_cuandoExiste_retornaConductor() {
        Integer id = 1;
        Conductor conductor = new Conductor(id, "A4");
        when(conductorRepository.findById(id)).thenReturn(Optional.of(conductor));

        Conductor resultado = conductorService.buscarPorIdConductor(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdConductor());
        assertEquals("A4", resultado.getLicencia());
        verify(conductorRepository).findById(id);
    }

    @Test
    void buscarPorIdConductor_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(conductorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> conductorService.buscarPorIdConductor(id));
        verify(conductorRepository).findById(id);
    }

    @Test
    void guardarConductor_guardaYRetorna() {
        Conductor conductor = new Conductor(null, "A5");
        when(conductorRepository.save(conductor)).thenReturn(new Conductor(1, "A5"));

        Conductor resultado = conductorService.guardarConductor(conductor);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdConductor());
        assertEquals("A5", resultado.getLicencia());
        verify(conductorRepository).save(conductor);
    }

    @Test
    void eliminarConductor_cuandoExiste_eliminaYRetornaTrue() {
        Integer id = 1;
        Conductor conductor = new Conductor(id, "A4");
        when(conductorRepository.findById(id)).thenReturn(Optional.of(conductor));

        boolean resultado = conductorService.eliminarConductor(id);

        assertTrue(resultado);
        verify(conductorRepository).delete(conductor);
    }

    @Test
    void eliminarConductor_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(conductorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> conductorService.eliminarConductor(id));
        verify(conductorRepository, never()).delete(any());
    }

    @Test
    void buscarConductorDTO_cuandoExiste_retornaDTO() {
        Integer id = 1;
        Conductor conductor = new Conductor(id, "A4");
        when(conductorRepository.findById(id)).thenReturn(Optional.of(conductor));

        ConductorDTO resultado = conductorService.buscarConductorDTO(id);

        assertNotNull(resultado);
        assertEquals(id, resultado.getIdConductor());
        verify(conductorRepository).findById(id);
    }

    @Test
    void buscarConductorDTO_cuandoNoExiste_lanzaExcepcion() {
        Integer id = 999;
        when(conductorRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> conductorService.buscarConductorDTO(id));
        verify(conductorRepository).findById(id);
    }
}
