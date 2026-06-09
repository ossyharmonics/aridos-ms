package cl.aridos.empresa.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="EMPRESA")
@Schema(description = "Este objeto representa a una empresa dentro del sistema.")
public class Empresa {
    @Id
    @Column(length = 10, nullable = false)
    @Schema(description = "Identificador unico de la empresa", example = "12345678-K")
    private String rutEmpresa;

    @Column(length = 50, nullable = false)
    @Size(max = 50, message = "El nombre de la empresa no puede ser mayor a")
    @Schema(description = "Nombre de la empresa", example = "Empresa SPA")
    @NotNull(message = "El nombre de la empresa es requerido.")
    private String nombreEmpresa;

    @ManyToOne
    @JoinColumn(
        name = "id_giro",
        referencedColumnName = "id_giro",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_id_giro")
    )
    private TipoGiro idGiro;
}
