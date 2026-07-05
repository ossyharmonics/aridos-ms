package cl.aridos.direccion.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name= "DIRECCION")
@Schema(description = "Este objeto representa a una dirección dentro del sistema.")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_direccion")
    @Schema(description = "Identificador único de la dirección", example = "1")
    private Integer idDireccion;

    @Column(length = 30, name= "calle",nullable = false)
    @Size(max = 30, message = "El nombre de la calle debe tener un máximo de 30 caracteres.")
    @NotNull(message = "La calle no puede estar vacía")
    @Schema(description = "Nombre de la calle", example = "Avenida Providencia")
    private String calle;

    @Column(length = 10, name= "numero",nullable = false)
    @NotNull(message = "El número de la calle no puede estar vacío")
    @Schema(description = "Número de la calle", example = "1234")
    private String numeracion;
    @ManyToOne
    @JoinColumn(name="id_comuna", 
                referencedColumnName="id_comuna",
                nullable = false,
                foreignKey= @ForeignKey(name = "fk_id_comuna")
            )
    @Schema(description = "Comuna a la que pertenece la dirección")
    private Comuna idComuna;            




}
