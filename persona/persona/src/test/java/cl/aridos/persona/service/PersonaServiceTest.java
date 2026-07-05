package cl.aridos.persona.service;

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

import cl.aridos.persona.dto.PersonaDTO;
import cl.aridos.persona.model.Persona;
import cl.aridos.persona.repository.PersonaRepository;

@ExtendWith(MockitoExtension.class)
class PersonaServiceTest {

    @Mock
    private PersonaRepository personaRepository;

    @InjectMocks
    private PersonaService personaService;

    @Test
    void listarPersonas_deberiaRetornarLista() {
        Persona p1 = new Persona("1-9", "Juan", "Luis", "Perez", "Gonzalez");
        Persona p2 = new Persona("2-7", "Maria", null, "Lopez", "Martinez");
        when(personaRepository.findAll()).thenReturn(Arrays.asList(p1, p2));

        List<Persona> resultado = personaService.listarPersonas();

        assertEquals(2, resultado.size());
        verify(personaRepository).findAll();
    }

    @Test
    void buscarPorRut_cuandoExiste_retornaPersona() {
        String rut = "1-9";
        Persona persona = new Persona(rut, "Juan", "Luis", "Perez", "Gonzalez");
        when(personaRepository.findByRut(rut)).thenReturn(Optional.of(persona));

        Persona resultado = personaService.buscarPorRut(rut);

        assertNotNull(resultado);
        assertEquals(rut, resultado.getRut());
        assertEquals("Juan", resultado.getPNombre());
        verify(personaRepository).findByRut(rut);
    }

    @Test
    void buscarPorRut_cuandoNoExiste_lanzaExcepcion() {
        String rut = "99-9";
        when(personaRepository.findByRut(rut)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personaService.buscarPorRut(rut));
        verify(personaRepository).findByRut(rut);
    }

    @Test
    void guardarPersona_guardaYRetorna() {
        Persona persona = new Persona("1-9", "Juan", "Luis", "Perez", "Gonzalez");
        when(personaRepository.save(persona)).thenReturn(persona);

        Persona resultado = personaService.guardarPersona(persona);

        assertNotNull(resultado);
        assertEquals("1-9", resultado.getRut());
        verify(personaRepository).save(persona);
    }

    @Test
    void actualizarPersona_cuandoExiste_actualizaYRetorna() {
        String rut = "1-9";
        Persona existente = new Persona(rut, "Juan", "Luis", "Perez", "Gonzalez");
        Persona actualizada = new Persona(null, "Pedro", null, "Perez", "Gonzalez");

        when(personaRepository.findByRut(rut)).thenReturn(Optional.of(existente));
        when(personaRepository.save(any(Persona.class))).thenReturn(existente);

        Persona resultado = personaService.actualizarPersona(rut, actualizada);

        assertNotNull(resultado);
        assertEquals("Pedro", resultado.getPNombre());
        verify(personaRepository).findByRut(rut);
        verify(personaRepository).save(existente);
    }

    @Test
    void actualizarPersona_cuandoNoExiste_retornaNull() {
        String rut = "99-9";
        when(personaRepository.findByRut(rut)).thenReturn(Optional.empty());

        Persona resultado = personaService.actualizarPersona(rut, new Persona());

        assertNull(resultado);
        verify(personaRepository).findByRut(rut);
        verify(personaRepository, never()).save(any());
    }

    @Test
    void eliminarPersona_cuandoExiste_eliminaYRetornaTrue() {
        String rut = "1-9";
        Persona persona = new Persona(rut, "Juan", "Luis", "Perez", "Gonzalez");
        when(personaRepository.findByRut(rut)).thenReturn(Optional.of(persona));

        boolean resultado = personaService.eliminarPersona(rut);

        assertTrue(resultado);
        verify(personaRepository).delete(persona);
    }

    @Test
    void eliminarPersona_cuandoNoExiste_lanzaExcepcion() {
        String rut = "99-9";
        when(personaRepository.findByRut(rut)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personaService.eliminarPersona(rut));
        verify(personaRepository, never()).delete(any());
    }

    @Test
    void buscarPersonaDTO_cuandoExiste_retornaDTO() {
        String rut = "1-9";
        Persona persona = new Persona(rut, "Juan", "Luis", "Perez", "Gonzalez");
        when(personaRepository.findByRut(rut)).thenReturn(Optional.of(persona));

        PersonaDTO resultado = personaService.buscarPersonaDTO(rut);

        assertNotNull(resultado);
        assertEquals(rut, resultado.getRutPersona());
        verify(personaRepository).findByRut(rut);
    }

    @Test
    void buscarPersonaDTO_cuandoNoExiste_lanzaExcepcion() {
        String rut = "99-9";
        when(personaRepository.findByRut(rut)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> personaService.buscarPersonaDTO(rut));
        verify(personaRepository).findByRut(rut);
    }
}
