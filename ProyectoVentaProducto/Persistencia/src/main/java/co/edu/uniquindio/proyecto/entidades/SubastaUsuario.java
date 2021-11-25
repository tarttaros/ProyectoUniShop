package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Entity
public class SubastaUsuario implements Serializable
{
    //primary key subastaUsuario
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    //subasta del usuario
    @JoinColumn(nullable = false)
    @ManyToOne
    private Subasta subasta;

    //usuario que subasta
    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    //valor de la puja
    @Column(nullable = false)
    @Positive
    private Double valor;

    //fecha de la puja
    @Column(columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Future
    private LocalDateTime fecha;

    //metodo super
    public SubastaUsuario()
    {
        super();
    }

    //metodo constructor
    public SubastaUsuario(Subasta subasta, Usuario usuario, Double valor)
    {
        this.subasta = subasta;
        this.usuario = usuario;
        this.valor = valor;
    }
}
