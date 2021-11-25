package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Future;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class Mensaje implements Serializable
{
    //primary key codigo mensaje
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigoMensaje;

    //mensaje a enviar
    @Column(length = 333, nullable = false)
    private String mensaje;

    //quien manda el mensaje
    @Column(length = 50, nullable = false)
    private String emisor;

    //fecha del mensaje
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Future
    private LocalDateTime fecha;

    //relacion mensaje chat
    @JoinColumn(nullable = false)
    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Chat codigoChat;

    //metodo super
    public Mensaje()
    {
        super();
    }

    //metodo constructor
    public Mensaje(String mensaje, String emisor, Chat codigoChat)
    {
        this.mensaje = mensaje;
        this.emisor = emisor;
        this.codigoChat = codigoChat;
    }
}
