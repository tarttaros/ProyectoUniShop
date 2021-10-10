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
public class Chat implements Serializable
{
    //primary key codigo del chat
    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue
    private Integer codigoChat;

    //relacion chat usuarioComprador
    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuarioComprador;

    //relacion chat Producto(producto a comprar)
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto productoComprar;

    //relacion inversa de chat con mensajes(mensajes del chat)
    @OneToMany(mappedBy = "codigoChat")
    private List<Mensaje> mensajes;

    //metodo super
    public Chat()
    {
        super();
    }

    //metodo constructor
    public Chat(Usuario usuarioComprador, Producto productoComprar) {
        this.usuarioComprador = usuarioComprador;
        this.productoComprar = productoComprar;
    }
}
