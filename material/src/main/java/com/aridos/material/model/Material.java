package com.aridos.material.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "MATERIAL")
@Schema(description = "Este objeto representa un material dentro del sistema.")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_material", nullable = false)
    @Schema(description = "Identificador unico del material", example = "1")
    private Integer idMaterial;

    @Column(name = "nombre_material", length = 25, nullable = false)
    @Size(max = 25, message = "El nombre debe ser obligatorio.")
    @Schema(description = "Nombre del material", example = "Arena")
    private String nombreMaterial;

    @Column(name = "precio_m3", nullable = false)
    @Digits(message = "El precio no puede estar vacio", integer = 10, fraction = 0)
    @Schema(description = "Precio por m3 del material", example = "22200")
    private Integer precioM3;
}
