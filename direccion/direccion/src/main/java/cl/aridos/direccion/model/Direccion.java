package cl.aridos.direccion.model;

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
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name= "id_direccion")
    private Integer idDireccion;

    @Column(length = 30, name= "calle",nullable = false)
    @Size(max = 30, message = "El nombre de la calle debe tener un máximo de 30 caracteres.")
    @NotNull(message = "La calle no puede estar vacía")
    private String calle;

    @Column(length = 10, name= "numero",nullable = false)
    @NotNull(message = "El número de la calle no puede estar vacío")
    private String numeracion;
    @ManyToOne
    @JoinColumn(name="id_comuna", 
                referencedColumnName="id_comuna",
                nullable = false,
                foreignKey= @ForeignKey(name = "fk_id_comuna")
            )
    private Comuna idComuna;            




}
