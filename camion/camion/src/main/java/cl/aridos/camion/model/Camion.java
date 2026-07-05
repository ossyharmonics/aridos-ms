package cl.aridos.camion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Entidad CAMION.
 * PK: patente
 * FK: idConductor -> CONDUCTOR.idConductor (otro microservicio, se resuelve via Feign)
 * IDX: CAMION__IDX (idConductor)
 */
@Entity
@Table(name = "CAMION")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Este objeto representa a un camion dentro del sistema.")
public class Camion {

    @Id
    @Column(name = "patente", length = 7, nullable = false)
    @Schema(description = "Patente del camion", example = "ABCD-12")
    private String patente;

    // NUMBER(2,1) -> precision 2, escala 1
    @Column(name = "capacidad", precision = 3, scale = 1)
    @Schema(description = "Capacidad de carga del camion en toneladas", example = "15.5")
    private BigDecimal capacidad;

    // FK logica hacia el microservicio CONDUCTOR (CONDUCTOR.idConductor)
    @Column(name = "idConductor")
    @Schema(description = "Identificador del conductor asociado al camion", example = "1")
    private Integer idConductor;
}
