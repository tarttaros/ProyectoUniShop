package co.edu.uniquindio.proyecto.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Producto implements Serializable
{
    //primary key de producto
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoProducto;

    //nombre del producto a vender
    @NotBlank(message = "El nombre del producto es obligatorio")
    @Column(length = 50, nullable = false)
    private String nombre;

    //cantidad de unidades del producto
    @Column(nullable = false)
    @PositiveOrZero
    private Integer cantidad;

    //descripcion del producto
    @Lob
    @NotBlank
    @Column(length = 100, nullable = false)
    @NotBlank(message = "Se debe establecer una descripcion")
    private String descripcion;

    //precio del producto
    @Column(nullable = false)
    @Positive
    private Double precio;

    //fecha de publicacion del produto
    @Future
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    //descuento con el que cuenta el prodcuto
    @Column(nullable = true)
    @Min(0)
    @Max(99)
    private Double descuento;

    //usuario que vende el producto
    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario vendedor;

    //ciudad donde se vende el producto
    @JoinColumn(nullable = true)
    @ManyToOne
    private Ciudad ciudad;

    //imagenes de un producto
    @Column(nullable = false)
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> imagenes;

    //relacion inversa de prodcuto chats(chats de un producto)
    @OneToMany(mappedBy = "productoComprar", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Chat> chatsProducto;

    //relacion inversa detalleCompra producto(producto a vender)
    @OneToMany(mappedBy = "productoComprar", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<DetalleCompra> detallesVenta;

    //relacion inversa comentario producto(comentarios hechos al producto)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Comentario> comentarios;

    //relacion inversa subasta producto(subastas del producto)
    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    @JsonIgnore
    private List<Subasta> subastasProducto;

    //usuarios que tienen al producto de favorito
    @ManyToMany(mappedBy = "productosFavoritos")
    @JsonIgnore
    private List<Usuario> usuarios;

    //categorias a las que pertenece un producto
    @ManyToMany
    @JsonIgnore
    private List<Categoria> categoriasProducto;

    //metodo super
    public Producto()
    {
        super();
    }

    //metodo constructor
    public Producto(String nombre, Integer cantidad, String descripcion, Double precio, Double descuento, Usuario vendedor, Ciudad ciudad)
    {
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.precio = precio;
        this.descuento = descuento;
        this.vendedor = vendedor;
        this.ciudad = ciudad;
    }

    public String getImagenPrincipal()
    {
        if(imagenes != null && !imagenes.isEmpty())
        {
            return imagenes.get(0);
        }
        return "default.png";
    }
    public String obtenerCategorias(List<Categoria> categorias)
    {
        String a="";
        for (int i=0;i<categorias.size();i++)
        {
            a=categorias.get(i).getNombre()+","+a;
        }
        return a;
    }
}
