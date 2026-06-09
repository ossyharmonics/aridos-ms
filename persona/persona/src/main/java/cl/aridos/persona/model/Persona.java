package cl.aridos.persona.model;

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
public class Persona {
    @Id
    @Column(length = 10, nullable = false)
    @Size(min = 8, max = 10, message = "Formato de rut inválido (12345678-K")
    @NotBlank(message = "El rut no puede estar vacío")
    private String rut;

    @Column(length = 20, nullable = false)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    @NotBlank(message="Campo no puede ser nulo.")
    private String pNombre;

    @Column(length = 20)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    private String sNombre;

    @Column(length = 20, nullable = false)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    @NotBlank(message="Campo no puede ser nulo.")
    private String apPaterno;

    @Column(length = 20, nullable = false)
    @Size(max=20, message="Limite de caracteres excedido (max. 20)")
    @NotBlank(message="Campo no puede ser nulo.")
    private String apMaterno;
}
