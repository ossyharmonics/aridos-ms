package cl.aridos.camion.model;

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
public class Camion {

    @Id
    @Column(name = "patente", length = 7, nullable = false)
    private String patente;

    // NUMBER(2,1) -> precision 2, escala 1
    @Column(name = "capacidad", precision = 3, scale = 1)
    private BigDecimal capacidad;

    // FK logica hacia el microservicio CONDUCTOR (CONDUCTOR.idConductor)
    @Column(name = "idConductor")
    private Integer idConductor;
}
