package cl.aridos.empresa.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TIPO_GIRO")
@Schema(description = "Este objeto representa un tipo de giro comercial dentro del sistema.")
public class TipoGiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "id_giro", nullable = false)
    @Schema(description = "Identificador único del tipo de giro comercial", example ="1")
    private Integer idGiro;

    @Column(length = 50, name = "nombre_giro", nullable = false)
    @NotBlank(message = "El nombre del giro no puede estar vacío")
    @Schema(description = "Nombre del tipo de giro comercial.", example = "Transporte de carga por carrertera.")
    private String nombreGiro;
}
