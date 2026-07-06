package com.aridos.material.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aridos.material.model.Material;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {

    Optional<Material> findByNombreMaterial(String nombreMaterial);

    Optional<Material> findByNombreMaterialContainingIgnoreCase(String nombreMaterial);
}
