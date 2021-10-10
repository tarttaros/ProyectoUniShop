package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashMap;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest
{
    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarCiudadTest()
    {
        Ciudad c = new Ciudad();
        c.setNombreCiudad("toscana");

        //Guardamos el registro
        Ciudad guardado = ciudadRepo.save(c);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }
}
