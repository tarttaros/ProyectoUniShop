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
public class Compra implements Serializable
{
    //primary key
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Integer Codigo;

    //codigo usuario que realiza la compra
    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario codigoUsuario;

    //fecha de compra
    @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Future
    private LocalDateTime fecha;

    //medio de pago
    @Column(length = 50)
    private String medioDPago;

    //relacion inversa detalleCompra Compra(compra a detallar)
    @OneToMany(mappedBy = "productoComprar")
    private List<DetalleCompra> detalleCompra;

    //metodo super
    public Compra()
    {
        super();
    }

    //metodo constructor
    public Compra(Usuario codigoUsuario, String medioDPago) {
        this.codigoUsuario = codigoUsuario;
        this.medioDPago = medioDPago;
    }
}
