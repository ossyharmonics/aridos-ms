package cl.aridos.pago.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="TIPO_PAGO")
@Schema(description = "Este objeto representa a un tipo de pago dentro del sistema.")
public class TipoPago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_pago", nullable = false)
    @Schema(description = "Identificador unico del tipo de pago", example = "1")
    private Integer idTipoPago;

    @Column(length = 25, name = "nombre_tipo_pago", nullable = false)
    @NotBlank(message = "El nombre del tipo de pago no puede estar vacío")
    @Schema(description = "Nombre del tipo de pago", example = "Transferencia")
    private String nombreTipoPago;


}
