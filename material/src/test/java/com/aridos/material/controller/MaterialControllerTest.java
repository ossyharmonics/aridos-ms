package com.aridos.material.controller;

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

import com.aridos.material.dto.MaterialDTO;
import com.aridos.material.model.Material;
import com.aridos.material.service.MaterialService;

@WebMvcTest(MaterialController.class)
class MaterialControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private MaterialService materialService;

    @Test
    void listarMateriales_cuandoHayMateriales_retornaLista() throws Exception {
        Material m1 = new Material(1, "Arena Gruesa 3/8", 22200);
        Material m2 = new Material(2, "Bolones", 14200);
        List<Material> materiales = Arrays.asList(m1, m2);
        when(materialService.listarMateriales()).thenReturn(materiales);

        mockMvc.perform(get("/aridos/materiales"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void listarMateriales_cuandoNoHayMateriales_retorna404() throws Exception {
        when(materialService.listarMateriales()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/aridos/materiales"))
                .andExpect(status().isNotFound());
    }

    @Test
    void buscarPorId_cuandoExiste_retornaMaterial() throws Exception {
        Material material = new Material(1, "Arena Gruesa 3/8", 22200);
        when(materialService.buscarPorId(1)).thenReturn(material);

        mockMvc.perform(get("/aridos/materiales/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMaterial").value(1))
                .andExpect(jsonPath("$.nombreMaterial").value("Arena Gruesa 3/8"));
    }

    @Test
    void buscarPorId_cuandoNoExiste_retorna404() throws Exception {
        when(materialService.buscarPorId(99)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/materiales/{id}", 99))
                .andExpect(status().isNotFound());
    }

    @Test
    void guardarMaterial_cuandoEsValido_retorna201() throws Exception {
        Material material = new Material(1, "Arena Gruesa 3/8", 22200);
        when(materialService.guardarMaterial(any())).thenReturn(material);

        mockMvc.perform(post("/aridos/materiales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreMaterial\":\"Arena Gruesa 3/8\",\"precioM3\":22200}"))
                .andExpect(status().isCreated());
    }

    @Test
    void guardarMaterial_cuandoEsInvalido_retorna400() throws Exception {
        when(materialService.guardarMaterial(any())).thenReturn(null);

        mockMvc.perform(post("/aridos/materiales")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreMaterial\":\"Arena Gruesa 3/8\",\"precioM3\":22200}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void actualizarMaterial_cuandoExiste_retorna200() throws Exception {
        Material material = new Material(1, "Arena Gruesa 3/8", 22200);
        when(materialService.actualizarMaterial(eq(1), any())).thenReturn(material);

        mockMvc.perform(put("/aridos/materiales/{id}", 1)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreMaterial\":\"Arena Gruesa 3/8\",\"precioM3\":22200}"))
                .andExpect(status().isOk());
    }

    @Test
    void actualizarMaterial_cuandoNoExiste_retorna400() throws Exception {
        when(materialService.actualizarMaterial(eq(99), any())).thenReturn(null);

        mockMvc.perform(put("/aridos/materiales/{id}", 99)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"nombreMaterial\":\"Arena Gruesa 3/8\",\"precioM3\":22200}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void eliminarMaterial_cuandoExiste_retorna204() throws Exception {
        when(materialService.eliminarMaterial(1)).thenReturn(true);

        mockMvc.perform(delete("/aridos/materiales/{id}", 1))
                .andExpect(status().isNoContent());
    }

    @Test
    void eliminarMaterial_cuandoNoExiste_retorna400() throws Exception {
        when(materialService.eliminarMaterial(99)).thenReturn(false);

        mockMvc.perform(delete("/aridos/materiales/{id}", 99))
                .andExpect(status().isBadRequest());
    }

    @Test
    void buscarMaterialDTO_cuandoExiste_retornaDTO() throws Exception {
        MaterialDTO dto = new MaterialDTO();
        dto.setIdMaterial(1);
        when(materialService.buscarMaterialDTO(1)).thenReturn(dto);

        mockMvc.perform(get("/aridos/materiales/dto/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.idMaterial").value(1));
    }

    @Test
    void buscarMaterialDTO_cuandoNoExiste_retorna404() throws Exception {
        when(materialService.buscarMaterialDTO(99)).thenThrow(new RuntimeException());

        mockMvc.perform(get("/aridos/materiales/dto/{id}", 99))
                .andExpect(status().isNotFound());
    }
}
