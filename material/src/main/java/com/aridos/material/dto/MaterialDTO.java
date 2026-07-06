package com.aridos.material.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Este objeto representa un material en formato DTO dentro del sistema.")
public class MaterialDTO {
    @Schema(description = "Identificador unico del material", example = "1")
    private Integer idMaterial;
}
