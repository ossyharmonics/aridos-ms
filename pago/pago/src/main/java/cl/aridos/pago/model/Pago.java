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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PAGO")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pago", nullable = false)
    private Integer idPago;

    @Column(name = "costo_despacho", nullable = false)
    @NotNull(message = "El costo de despacho no puede estar vacío")
    private Integer costoDespacho;

    @ManyToOne
    @JoinColumn(
        name = "id_tipo_pago", 
        referencedColumnName = "id_tipo_pago",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_id_tipo_pago")
    )
    private TipoPago idTipoPago;
}

