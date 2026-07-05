package cl.aridos.direccion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.ForeignKey;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name= "COMUNA")
@Schema(description = "Este objeto representa a una comuna dentro del sistema.")
public class Comuna {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_comuna")
    @Schema(description = "Identificador único de la comuna", example = "1")
    private Integer idComuna;

    @Column(length = 30, name= "nombre_comuna",nullable = false)
    @Size(max = 30, message = "El nombre de la comuna debe tener un máximo de 30 caracteres")
    @NotNull(message = "El nombre de la comuna no puede estar vacío")
    @Schema(description = "Nombre de la comuna", example = "Santiago")
    private String nombreComuna;
    
    @ManyToOne
    @JoinColumn(
        name = "id_region",
        referencedColumnName = "id_region",
        nullable = false,
        foreignKey = @ForeignKey(name= "fk_id_region")
    )
    @Schema(description = "Región a la que pertenece la comuna")
    private Region idRegion;
}
