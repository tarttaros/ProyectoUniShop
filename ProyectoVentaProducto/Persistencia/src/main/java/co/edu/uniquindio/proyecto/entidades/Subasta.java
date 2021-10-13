package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Subasta implements Serializable
{
    //primary key subasta
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //fecha limite de la subasta
    @Column(nullable = false)
    @Future
    private LocalDateTime fechaLimite;

    //producto a subastar
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    //relacion inversa subastausuario subasta(pujas del producto)
    @OneToMany(mappedBy = "subasta")
    private List<SubastaUsuario> pujasSubasta;

    //metodo super
    public Subasta()
    {
        super();
    }

    //metodo constructor
    public Subasta(LocalDateTime fechaLimite, Producto producto) {
        this.fechaLimite = fechaLimite;
        this.producto = producto;
    }
}
