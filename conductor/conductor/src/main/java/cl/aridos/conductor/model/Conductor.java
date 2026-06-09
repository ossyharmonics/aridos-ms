package cl.aridos.conductor.model;

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
public class Conductor {

    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @GeneratedValue(strategy
    @Column(name = "idConductor", nullable = false, unique = true)
    private Integer idConductor;

    // CHAR(2)
    @Column(name = "licencia", length = 2, nullable= false)
    private String licencia;

    /*/ FK logica hacia el microservicio CAMION (CAMION.patente)
    @Column(name = "patente", length = 7)
    private String patente;*/
}
