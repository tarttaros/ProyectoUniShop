package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AdministradorTest
{
    @Autowired
    private AdministradorRepo administradorRepo;

    @Test
    public void registrarAdministradorTest(){
        Administrador a = new Administrador();
        a.setNombre("laura");
        a.setEmail("laura@correo.com");
        a.setPassword("laura123");

        //Guardamos el registro
        Administrador guardado = administradorRepo.save(a);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    @Test
    public void eliminarAdministradorTest(){
        Administrador a = new Administrador();
        a.setNombre("laura");
        a.setEmail("laura@correo.com");
        a.setPassword("laura123");

        //Guardamos el registro
        Administrador guardado = administradorRepo.save(a);

        //Luego lo eliminamos
        administradorRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Administrador buscado = administradorRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    @Test
    public void actualizarAdministradorTest(){
        Administrador a = new Administrador();
        a.setNombre("laura");
        a.setEmail("laura@correo.com");
        a.setPassword("laura123");

        //Guardamos el registro
        Administrador registrado = administradorRepo.save(a);

        //Modificamos el nombre
        registrado.setNombre("laurita");

        //Con save guardamos el registro modificado
        administradorRepo.save(registrado);

        //Por último, verificamos que si haya quedado actualizado
        Administrador buscado = administradorRepo.findById(1).orElse(null);
        Assertions.assertEquals("laurita", buscado.getNombre());
    }

    @Test
    @Sql("classpath:Administradores.sql")
    public void listarAdministradorTest(){
        //Obtenemos la lista de todos los usuarios
        List<Administrador> lista = administradorRepo.findAll();

        //Imprimimos la lista
        System.out.println(lista);
    }
}
