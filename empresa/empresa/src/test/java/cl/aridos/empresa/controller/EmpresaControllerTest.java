package cl.aridos.empresa.controller;

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

import cl.aridos.empresa.dto.EmpresaDTO;
import cl.aridos.empresa.model.Empresa;
import cl.aridos.empresa.model.TipoGiro;
import cl.aridos.empresa.service.EmpresaService;

@WebMvcTest(EmpresaController.class)
class EmpresaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private EmpresaService empresaService;

    @Test
    void listarEmpresas_deberiaRetornarLista() throws Exception {
        Empresa e1 = new Empresa("11-1", "Empresa SA", null);
        when(empresaService.listarEmpresas()).thenReturn(Arrays.asList(e1));

        mockMvc.perform(get("/aridos/empresas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void buscarPorRut_cuandoExiste_retornaEmpresa() throws Exception {
        Empresa empresa = new Empresa("11-1", "Empresa SA", null);
        when(empresaService.buscarPorRut("11-1")).thenReturn(empresa);

        mockMvc.perform(get("/aridos/empresas/{rut}", "11-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rutEmpresa").value("11-1"));
    }

    @Test
    void buscarPorRut_cuandoNoExiste_retorna404() throws Exception {
        when(empresaService.buscarPorRut("99-9")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/empresas/{rut}", "99-9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarEmpresa_cuandoEsValido_retorna201() throws Exception {
        Empresa empresa = new Empresa("11-1", "Empresa SA", null);
        when(empresaService.guardarEmpresa(any())).thenReturn(empresa);

        mockMvc.perform(post("/aridos/empresas/")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rutEmpresa\":\"11-1\",\"nombreEmpresa\":\"Empresa SA\"}"))
                .andExpect(status().isCreated());
    }

    @Test
    void actualizarEmpresa_cuandoExiste_retorna200() throws Exception {
        Empresa empresa = new Empresa("11-1", "Empresa SA", null);
        when(empresaService.actualizarEmpresa(eq("11-1"), any())).thenReturn(empresa);

        mockMvc.perform(put("/aridos/empresas/{rut}", "11-1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rutEmpresa\":\"11-1\",\"nombreEmpresa\":\"Empresa SA\"}"))
                .andExpect(status().isOk());
    }

    @Test
    void eliminarEmpresa_cuandoExiste_retorna204() throws Exception {
        when(empresaService.eliminarEmpresa("11-1")).thenReturn(true);

        mockMvc.perform(delete("/aridos/empresas/{rut}", "11-1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void listarTipoGiros_deberiaRetornarLista() throws Exception {
        TipoGiro tg = new TipoGiro(1, "Transporte");
        when(empresaService.listarTipoGiros()).thenReturn(Arrays.asList(tg));

        mockMvc.perform(get("/aridos/empresas/giros"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }

    @Test
    void buscarEmpresaDTO_cuandoExiste_retornaDTO() throws Exception {
        EmpresaDTO dto = new EmpresaDTO();
        dto.setRutEmpresa("11-1");
        when(empresaService.buscarEmpresaDTO("11-1")).thenReturn(dto);

        mockMvc.perform(get("/aridos/empresas/dto/{rut}", "11-1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.rutEmpresa").value("11-1"));
    }

    @Test
    void buscarEmpresaDTO_cuandoNoExiste_retorna404() throws Exception {
        when(empresaService.buscarEmpresaDTO("99-9")).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/empresas/dto/{rut}", "99-9"))
                .andExpect(status().isNotFound());
    }
}
