package cl.aridos.direccion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "REGION")
@Schema(description = "Este objeto representa a una región dentro del sistema.")
public class Region {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_region")
    @Schema(description = "Identificador único de la región", example = "1")
    private Integer idRegion;

    @Column(length = 30, name= "nombre_Region",nullable = false)
    @Size(max = 30, message = "El nombre de la Region debe tener un máximo de 30 caracteres")
    @NotNull(message = "El nombre de la Region no puede estar vacío")
    @Schema(description = "Nombre de la región", example = "Región Metropolitana")
    private String nombreRegion;

}
