package cl.aridos.persona.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import cl.aridos.persona.dto.PersonaDTO;
import cl.aridos.persona.model.Persona;
import cl.aridos.persona.service.PersonaService;

@WebMvcTest(PersonaController.class)
class PersonaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PersonaService personaService;

    @Test
    void listarPersonas_cuandoExisten_retornaLista() throws Exception {
        Persona p1 = new Persona("1-9", "Juan", "Luis", "Perez", "Gonzalez");
        when(personaService.listarPersonas()).thenReturn(Arrays.asList(p1));

        mockMvc.perform(get("/aridos/personas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void listarPersonas_cuandoNoExisten_retorna404() throws Exception {
        when(personaService.listarPersonas()).thenReturn(List.of());

        mockMvc.perform(get("/aridos/personas"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorRut_cuandoExiste_retornaPersona() throws Exception {
        Persona persona = new Persona("1-9", "Juan", "Luis", "Perez", "Gonzalez");
        when(personaService.buscarPorRut("1-9")).thenReturn(persona);

        mockMvc.perform(get("/aridos/personas/{rut}", "1-9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rut").value("1-9"));
    }

    @Test
    void buscarPorRut_cuandoNoExiste_retorna404() throws Exception {
        when(personaService.buscarPorRut("99-9")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/personas/{rut}", "99-9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarPersona_cuandoEsValido_retorna201() throws Exception {
        Persona persona = new Persona("1-9", "Juan", "Luis", "Perez", "Gonzalez");
        when(personaService.guardarPersona(any())).thenReturn(persona);

        mockMvc.perform(post("/aridos/personas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rut\":\"1-9\",\"pNombre\":\"Juan\",\"apPaterno\":\"Perez\",\"apMaterno\":\"Gonzalez\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizar_cuandoExiste_retorna200() throws Exception {
        Persona persona = new Persona("1-9", "Juan", "Luis", "Perez", "Gonzalez");
        when(personaService.actualizarPersona(eq("1-9"), any())).thenReturn(persona);

        mockMvc.perform(put("/aridos/personas/{rut}", "1-9")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rut\":\"1-9\",\"pNombre\":\"Juan\",\"apPaterno\":\"Perez\",\"apMaterno\":\"Gonzalez\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarPersona_cuandoExiste_retorna204() throws Exception {
        when(personaService.eliminarPersona("1-9")).thenReturn(true);

        mockMvc.perform(delete("/aridos/personas/{rut}", "1-9"))
                .andExpect(status().isNoContent());
    }

    @Test
    void buscarPersonaDTO_cuandoExiste_retornaDTO() throws Exception {
        PersonaDTO dto = new PersonaDTO();
        dto.setRutPersona("1-9");
        when(personaService.buscarPersonaDTO("1-9")).thenReturn(dto);

        mockMvc.perform(get("/aridos/personas/dto/{rut}", "1-9"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rutPersona").value("1-9"));
    }

    @Test
    void buscarPersonaDTO_cuandoNoExiste_retorna404() throws Exception {
        when(personaService.buscarPersonaDTO("99-9")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/personas/dto/{rut}", "99-9"))
                .andExpect(status().isNotFound());
    }
}
