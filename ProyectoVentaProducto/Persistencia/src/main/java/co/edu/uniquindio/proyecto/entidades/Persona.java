package co.edu.uniquindio.proyecto.entidades;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@MappedSuperclass
public class Persona implements Serializable
{
    //primary key
    @Id
    //@GeneratedValue(strategy=GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Integer codigo;

    @Column(length = 100, unique=true)
    private  String userName;

    //nombre de la persona
    @Column(length = 50, nullable = false)
    private String nombre;

    //correo de la persona
    @Column(length = 50, nullable = false, unique = true)
    private String email;

    //pasword de la persona
    @Column(length = 13, nullable = false)
    private String password;

    //metodo super
    public Persona()
    {
        super();
    }

    //metodo constructor
    public Persona(Integer  codigo, String nombre, String email, String password)
    {
        this.codigo = codigo;
        this.nombre = nombre;
        this.email = email;
        this.password = password;
    }
    public Persona(String nombre, String email, String password,String userName) {
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.userName= userName;
    }

}
