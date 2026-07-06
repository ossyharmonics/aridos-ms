package com.aridos.material.service;

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

import com.aridos.material.dto.MaterialDTO;
import com.aridos.material.model.Material;
import com.aridos.material.repository.MaterialRepository;

@ExtendWith(MockitoExtension.class)
class MaterialServiceTest {

    @Mock
    private MaterialRepository materialRepository;

    @InjectMocks
    private MaterialService materialService;

    @Test
    void listarMateriales_deberiaRetornarLista() {
        Material m1 = new Material(1, "Arena Gruesa 3/8", 22200);
        Material m2 = new Material(2, "Bolones", 14200);
        when(materialRepository.findAll()).thenReturn(Arrays.asList(m1, m2));

        List<Material> resultado = materialService.listarMateriales();

        assertEquals(2, resultado.size());
        verify(materialRepository).findAll();
    }

    @Test
    void buscarPorId_cuandoExiste_retornaMaterial() {
        Material material = new Material(1, "Arena Gruesa 3/8", 22200);
        when(materialRepository.findById(1)).thenReturn(Optional.of(material));

        Material resultado = materialService.buscarPorId(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdMaterial());
        assertEquals("Arena Gruesa 3/8", resultado.getNombreMaterial());
        assertEquals(22200, resultado.getPrecioM3());
        verify(materialRepository).findById(1);
    }

    @Test
    void buscarPorId_cuandoNoExiste_lanzaExcepcion() {
        when(materialRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> materialService.buscarPorId(99));
        verify(materialRepository).findById(99);
    }

    @Test
    void guardarMaterial_deberiaRetornarMaterialGuardado() {
        Material material = new Material(null, "Arena Gruesa 3/8", 22200);
        Material materialGuardado = new Material(1, "Arena Gruesa 3/8", 22200);
        when(materialRepository.save(material)).thenReturn(materialGuardado);

        Material resultado = materialService.guardarMaterial(material);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdMaterial());
        verify(materialRepository).save(material);
    }

    @Test
    void eliminarMaterial_cuandoExiste_retornaTrue() {
        when(materialRepository.existsById(1)).thenReturn(true);

        boolean resultado = materialService.eliminarMaterial(1);

        assertTrue(resultado);
        verify(materialRepository).deleteById(1);
    }

    @Test
    void eliminarMaterial_cuandoNoExiste_retornaFalse() {
        when(materialRepository.existsById(99)).thenReturn(false);

        boolean resultado = materialService.eliminarMaterial(99);

        assertFalse(resultado);
        verify(materialRepository, never()).deleteById(any());
    }

    @Test
    void actualizarMaterial_cuandoExiste_retornaMaterialActualizado() {
        Material existente = new Material(1, "Arena Gruesa 3/8", 22200);
        Material actualizacion = new Material(null, "Arena Gruesa Actualizada", 25000);
        when(materialRepository.findById(1)).thenReturn(Optional.of(existente));
        when(materialRepository.save(any(Material.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Material resultado = materialService.actualizarMaterial(1, actualizacion);

        assertNotNull(resultado);
        assertEquals("Arena Gruesa Actualizada", resultado.getNombreMaterial());
        assertEquals(25000, resultado.getPrecioM3());
        verify(materialRepository).findById(1);
        verify(materialRepository).save(any(Material.class));
    }

    @Test
    void actualizarMaterial_cuandoNoExiste_retornaNull() {
        when(materialRepository.findById(99)).thenReturn(Optional.empty());

        Material resultado = materialService.actualizarMaterial(99, new Material());

        assertNull(resultado);
        verify(materialRepository).findById(99);
        verify(materialRepository, never()).save(any());
    }

    @Test
    void buscarMaterialDTO_cuandoExiste_retornaDTO() {
        Material material = new Material(1, "Arena Gruesa 3/8", 22200);
        when(materialRepository.findById(1)).thenReturn(Optional.of(material));

        MaterialDTO resultado = materialService.buscarMaterialDTO(1);

        assertNotNull(resultado);
        assertEquals(1, resultado.getIdMaterial());
        verify(materialRepository).findById(1);
    }
}
