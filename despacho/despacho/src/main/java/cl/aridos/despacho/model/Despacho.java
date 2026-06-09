package cl.aridos.despacho.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entidad DESPACHO.
 * PK: idDespacho
 * FKs logicas hacia otros microservicios (se resuelven via Feign):
 *   rutEmpresa  -> EMPRESA.rutEmpresa
 *   rutPersona  -> PERSONA.rutPersona
 *   idDireccion -> DIRECCION.idDireccion
 *   patente     -> CAMION.patente
 *   idPago      -> PAGO.idPago
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DESPACHO")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDespacho", nullable= false)
    private Integer idDespacho;

    @Column(name = "rutEmpresa", nullable= false)
    private String rutEmpresa;

    @Column(name = "rutPersona", nullable= false)
    private String rutPersona;

    @Column(name = "idDireccion", nullable= false)
    private Integer idDireccion;

    @Column(name = "patente", length = 7)
    private String patente;

    @Column(name = "idPago")
    private Integer idPago;
}
