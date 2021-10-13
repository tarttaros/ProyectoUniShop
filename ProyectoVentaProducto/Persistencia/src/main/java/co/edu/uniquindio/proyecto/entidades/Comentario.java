package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Comentario implements Serializable
{
    //primary key comentario
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //usuario que comenta el producto
    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    //producto comentado por el usuario
    @JoinColumn(nullable = false)
    @ManyToOne
    private Producto producto;

    //mensaje que se postea en el comentario
    @Column(length = 333)
    private String mensaje;

    //respuesta al comentario
    @Column(length = 333)
    private String respuesta;

    //fecha del comentario
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime fecha;

    //calificacion del producto
    @Column(nullable = true)
    @Min(0)
    @Max(5)
    private Integer calificacion;

    //metodo super
    public Comentario()
    {
        super();
    }

    //metodo constructor
    public Comentario(Usuario usuario, Producto producto, String mensaje, String respuesta, Integer calificacion) {
        this.usuario = usuario;
        this.producto = producto;
        this.mensaje = mensaje;
        this.respuesta = respuesta;
        this.calificacion = calificacion;
    }
}
