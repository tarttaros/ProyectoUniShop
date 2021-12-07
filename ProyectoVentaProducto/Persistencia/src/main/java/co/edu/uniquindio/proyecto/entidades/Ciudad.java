package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Ciudad implements Serializable
{
    //primary key codigo de la ciudad
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoCiudad;

    //nombre de la ciudad
    @Column(length = 50, nullable = false, unique = true)
    @NotBlank(message = "Es obligatorio el nombre de la ciudad")
    private String nombreCiudad;

    //relacion inversa persona ciudad(usuarios en una ciudad)
    @JsonIgnore
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Usuario> usuarios;

    //relacion inversa producto ciudad(productos vendidos en una ciudad)
    @JsonIgnore
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Producto> productos;

    //metodo super
    public Ciudad()
    {
        super();
    }

    //metodo constructor
    public Ciudad(String nombreCiudad)
    {
        this.nombreCiudad = nombreCiudad;
    }
}
