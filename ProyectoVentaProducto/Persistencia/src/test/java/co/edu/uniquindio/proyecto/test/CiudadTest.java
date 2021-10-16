package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CiudadTest
{
    //variable que representa al repositorio
    @Autowired
    private CiudadRepo ciudadRepo;

    //metodo que prueba el crear una ciudad
    @Test
    public void registrarCiudadTest()
    {
        //se inicializa una ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad guardado = ciudadRepo.save(c);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el eliminar una ciudad
    @Test
    public void eliminarCiudadTest()
    {
        //se inicializa una ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad guardado = ciudadRepo.save(c);

        //Luego lo eliminamos
        ciudadRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Ciudad buscado = ciudadRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el actualizar una ciudad
    @Test
    public void actualizarCiudadTest()
    {
        //se inicializa una ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad guardado = ciudadRepo.save(c);

        //Modificamos el nombre
        guardado.setNombreCiudad("florencia");

        //Con save guardamos el registro modificado
        ciudadRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("florencia", guardado.getNombreCiudad());
    }

    //metodo que prueba el Listar las ciudades
    @Test
    public void listarCiudadTest()
    {
        //se inicializa una ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad guardado = ciudadRepo.save(c);

        //Obtenemos la lista de todas las ciudades
        List<Ciudad> lista = ciudadRepo.findAll();

        //Imprimimos la lista
        for (Ciudad city : lista)
        {
            System.out.println(city);
        }
    }

    //metodo que prueba el Listar las ciudades
    @Test
    @Sql("classpath:Ciudades.sql")
    public void listarCiudadTestSql()
    {
        //Obtenemos la lista de todas las ciudades
        List<Ciudad> lista = ciudadRepo.findAll();

        //Imprimimos la lista
        for (Ciudad city : lista)
        {
            System.out.println(city);
        }
    }
}
