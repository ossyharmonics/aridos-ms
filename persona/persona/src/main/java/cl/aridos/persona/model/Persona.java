package cl.aridos.persona.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="PERSONA")
@Schema(description = "Este objeto representa a una persona dentro del sistema.")
public class Persona {
    @Id
    @Schema(description = "Rut de la persona", example = "12345678-K")
    @Column(length = 10, nullable = false)
    @Size(min = 8, max = 10, message = "Formato de rut inválido (12345678-K")
    @NotBlank(message = "El rut no puede estar vacío")
    private String rut;

    @Schema(description = "Primer nombre de la persona", example = "Juan")
    @Column(length = 20, nullable = false)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    @NotBlank(message="Campo no puede ser nulo.")
    private String pNombre;

    @Schema(description = "Segundo nombre de la persona", example = "Pablo")
    @Column(length = 20)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    private String sNombre;

    @Schema(description = "Apellido paterno de la persona", example = "González")
    @Column(length = 20, nullable = false)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    @NotBlank(message="Campo no puede ser nulo.")
    private String apPaterno;

    @Schema(description = "Apellido materno de la persona", example = "López")
    @Column(length = 20, nullable = false)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    @NotBlank(message="Campo no puede ser nulo.")
    private String apMaterno;
}
