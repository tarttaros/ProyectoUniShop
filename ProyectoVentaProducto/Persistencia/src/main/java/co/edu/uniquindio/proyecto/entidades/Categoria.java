package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Categoria implements Serializable
{
    //primary key de categoria
    @Id
    @GeneratedValue
    @EqualsAndHashCode.Include
    private Integer codigo;

    //nombre de la categoria
    @Column(length = 50 , nullable = false, unique = true)
    private String nombre;

    //productos de una categoria
    @ManyToMany(mappedBy = "categoriasProducto")
    private List<Producto> listaProductos;

    //metodo super
    public Categoria()
    {
        super();
    }

    //metodo constructor
    public Categoria(String nombre) {
        this.nombre = nombre;
    }
}
