package cl.aridos.conductor.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CONDUCTOR")
@Schema(description = "Este objeto representa a un conductor dentro del sistema.")
public class Conductor {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy
    @Column(name = "idConductor", nullable = false, unique = true)
    @Schema(description = "Identificador unico del conductor", example = "1")
    private Integer idConductor;

    // CHAR(2)
    @Column(name = "licencia", length = 2, nullable= false)
    @Schema(description = "Tipo de licencia del conductor", example = "A4")
    private String licencia;

    /*/ FK logica hacia el microservicio CAMION (CAMION.patente)
    @Column(name = "patente", length = 7)
    private String patente;*/
}
