package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Optional;

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
    @Lob
    @Column(length = 333)
    @NotBlank(message = "Se debe poner un comentario")
    private String mensaje;

    //respuesta al comentario
    @Lob
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
    public Comentario(Usuario usuario, Producto producto, String mensaje, Integer calificacion)
    {
        this.usuario = usuario;
        this.producto = producto;
        this.mensaje = mensaje;
        this.calificacion = calificacion;
    }
}
