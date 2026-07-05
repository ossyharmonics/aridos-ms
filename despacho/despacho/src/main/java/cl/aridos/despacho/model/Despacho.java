package cl.aridos.despacho.model;

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
@Schema(description = "Este objeto representa a un despacho dentro del sistema.")
public class Despacho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDespacho")
    @Schema(description = "Identificador unico del despacho", example = "1")
    private Integer idDespacho;

    @Column(name = "rutEmpresa", nullable= false)
    @Schema(description = "RUT de la empresa asociada al despacho", example = "12345678-K")
    private String rutEmpresa;

    @Column(name = "rutPersona", nullable= false)
    @Schema(description = "RUT de la persona asociada al despacho", example = "98765432-1")
    private String rutPersona;

    @Column(name = "idDireccion", nullable= false)
    @Schema(description = "Identificador de la direccion asociada al despacho", example = "1")
    private Integer idDireccion;

    @Column(name = "patente", length = 7)
    @Schema(description = "Patente del camion asociado al despacho", example = "ABCD12")
    private String patente;

    @Column(name = "idPago")
    @Schema(description = "Identificador del pago asociado al despacho", example = "1")
    private Integer idPago;
}
