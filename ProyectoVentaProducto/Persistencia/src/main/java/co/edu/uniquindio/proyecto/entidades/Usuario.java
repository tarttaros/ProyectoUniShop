package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@Entity
@ToString(callSuper = true)
public class Usuario extends Persona implements Serializable
{
    //relacion usuario ciudad
    //@JoinColumn(nullable = false)

    @ManyToOne
    private Ciudad ciudad;

    //lista de telefonos
    //@Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)

    private Map<String,String> telefonos;

    //relacion inversa usuario chatComprador(chats del usuario)
    @JsonIgnore
    @OneToMany(mappedBy = "usuarioComprador", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Chat> chatsComprador;

    //relacion inversa compra usuarios(compras que realiza el usuario)
    @JsonIgnore
    @OneToMany(mappedBy = "codigoUsuario", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Compra> compras;

    //relacion inversa producto usuario(productos que vende el usuario)
    @JsonIgnore
    @OneToMany(mappedBy = "vendedor", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Producto> productos;

    //relacion inversa comentario usuario(comentarios hechos por el usuari
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Comentario> comentarios;

    //relacion inversa subastasUsuario usuario(pujas del usuario)
    @JsonIgnore
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<SubastaUsuario> pujasUsuario;

    //productos favoritos del usuario
    @JsonIgnore
    @ManyToMany
    @ToString.Exclude
    private List<Producto> productosFavoritos;

    //metodo super
    public Usuario()
    {
        super();
    }

    //metodo constructor
    public Usuario(Integer codigo, String nombre, String email, String password, Ciudad ciudad)
    {
        super(codigo,nombre, email, password);
        this.ciudad = ciudad;
    }
    /*
    public Usuario(String nombre, String email, String password, Ciudad ciudad,String userName)
    {
        super(nombre, email, password,userName);
        this.ciudad = ciudad;
    }
    */
}
