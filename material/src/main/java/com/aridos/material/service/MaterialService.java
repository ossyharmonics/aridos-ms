package com.aridos.material.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aridos.material.dto.MaterialDTO;
import com.aridos.material.model.Material;
import com.aridos.material.repository.MaterialRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MaterialService {

    @Autowired
    private MaterialRepository materialRepository;

    public List<Material> listarMateriales() {
        return materialRepository.findAll();
    }

    public Material buscarPorId(Integer id) {
        return materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Material no encontrado con id: " + id));
    }

    public Material buscarPorNombreMaterial(String nombreMaterial) {
        return materialRepository.findByNombreMaterial(nombreMaterial)
                .orElseThrow(() -> new RuntimeException("Material no encontrado con nombre: " + nombreMaterial));
    }

    public Material guardarMaterial(Material material) {
        return materialRepository.save(material);
    }

    public boolean eliminarMaterial(Integer id) {
        if (materialRepository.existsById(id)) {
            materialRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Material actualizarMaterial(Integer id, Material material) {
        Material m = materialRepository.findById(id).orElse(null);
        if (m != null) {
            m.setNombreMaterial(material.getNombreMaterial());
            m.setPrecioM3(material.getPrecioM3());
            return materialRepository.save(m);
        }
        return null;
    }

    public MaterialDTO buscarMaterialDTO(Integer id) {
        Material m = buscarPorId(id);
        MaterialDTO dto = new MaterialDTO();
        dto.setIdMaterial(m.getIdMaterial());
        return dto;
    }
}
