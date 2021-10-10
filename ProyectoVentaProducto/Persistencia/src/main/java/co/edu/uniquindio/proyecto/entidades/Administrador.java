package co.edu.uniquindio.proyecto.entidades;

import javax.persistence.Entity;
import java.io.Serializable;

@Entity
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

    //metodo toString
    @Override
    public String toString() {
        return super.toString();
    }
}
