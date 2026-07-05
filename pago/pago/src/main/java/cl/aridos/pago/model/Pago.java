package cl.aridos.pago.model;

import jakarta.persistence.ForeignKey;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PAGO")
@Schema(description = "Este objeto representa a un pago dentro del sistema.")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false)
    @Schema(description = "Identificador unico del pago", example = "1")
    private Integer idPago;

    @Column(name = "costo_despacho", nullable = false)
    @NotNull(message = "El costo de despacho no puede estar vacío")
    @Schema(description = "Costo de despacho del pago", example = "5000")
    private Integer costoDespacho;

    @ManyToOne
    @JoinColumn(
        name = "id_tipo_pago", 
        referencedColumnName = "id_tipo_pago",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_id_tipo_pago")
    )
    @Schema(description = "Tipo de pago asociado al pago")
    private TipoPago idTipoPago;
}

