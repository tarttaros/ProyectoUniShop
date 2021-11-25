package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class DetalleCompra implements Serializable
{
    //primary key del detalle de la compra
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //codigo de la compra a describir
    @JoinColumn(nullable = false)
    @ManyToOne
    private Compra codigoCompra;

    //producto a comprar
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto productoComprar;

    //unidades del producto a vender
    @Column(nullable = false)
    @Positive
    private Integer unidades;

    //precio del producto a vender
    @Column(nullable = false)
    @Positive
    private Double precio;

    //metodo super
    public DetalleCompra()
    {
        super();
    }

    //metodo constructor
    public DetalleCompra(Compra codigoCompra, Producto productoComprar, Integer unidades, Double precio)
    {
        this.codigoCompra = codigoCompra;
        this.productoComprar = productoComprar;
        this.unidades = unidades;
        this.precio = precio;
    }
}
