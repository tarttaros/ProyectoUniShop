package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@ToString(callSuper=true)
public class Usuario extends Persona implements Serializable
{
    //relacion usuario ciudad
    @JoinColumn(nullable = false)
    @ManyToOne
    private Ciudad ciudad;

    //lista de telefonos
    @Column(nullable = false)
    @ElementCollection
    private Map<String,String> telefonos;

    //relacion inversa usuario chatComprador(chats del usuario)
    @OneToMany(mappedBy = "usuarioComprador")
    private List<Chat> chatsComprador;

    //relacion inversa compra usuarios(compras que realiza el usuario)
    @OneToMany(mappedBy = "codigoUsuario")
    private List<Compra> compras;

    //relacion inversa producto usuario(productos que vende el usuario)
    @OneToMany(mappedBy = "vendedor")
    private List<Producto> productos;

    //relacion inversa comentario usuario(comentarios hechos por el usuario)
    @OneToMany(mappedBy = "usuario")
    private List<Comentario> comentarios;

    //relacion inversa subastasUsuario usuario(pujas del usuario)
    @OneToMany(mappedBy = "usuario")
    private List<SubastaUsuario> pujasUsuario;

    //productos favoritos del usuario
    @ManyToMany
    private List<Producto> productosFavoritos;

    //metodo super
    public Usuario()
    {
        super();
    }

    //metodo constructor
    public Usuario(String nombre, String email, String password, Ciudad ciudad)
    {
        super(nombre, email, password);
        this.ciudad = ciudad;
    }
}
