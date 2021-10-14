package co.edu.uniquindio.proyecto.entidades;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import java.io.Serializable;

@Getter
@Setter
@Entity
@ToString(callSuper=true)
public class Administrador extends Persona implements Serializable
{
    //metodo super
    public Administrador()
    {
        super();
    }

    //metodo constructor
    public Administrador(String nombre, String email, String password) {
        super(nombre, email, password);
    }

}