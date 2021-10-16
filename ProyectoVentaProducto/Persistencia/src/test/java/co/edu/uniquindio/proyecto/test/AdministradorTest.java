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
    //variable que representa al repositorio
    @Autowired
    private AdministradorRepo administradorRepo;

    //metodo que prueba el crear un administrador
    @Test
    public void registrarAdministradorTest()
    {
        //se inicializa el administrador
        Administrador a = new Administrador();
        a.setNombre("laura");                           //se define un nombre
        a.setEmail("laura@correo.com");                 //se define un correo
        a.setPassword("laura123");                      //se define una contraseña

        //Guardamos el registro
        Administrador guardado = administradorRepo.save(a);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el crear un administrador
    @Test
    @Sql("classpath:Administradores.sql")
    public void registrarAdministradorTestSql()
    {
        //se inicializa el administrador
        Administrador a = new Administrador();
        a.setNombre("paola");                           //se define un nombre
        a.setEmail("paola@correo.com");                 //se define un correo
        a.setPassword("paola123");                      //se define una contraseña

        //Guardamos el registro
        Administrador guardado = administradorRepo.save(a);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba la eliminacion de un administrador
    @Test
    public void eliminarAdministradorTest()
    {
        //se inicializa el administrador
        Administrador a = new Administrador();
        a.setNombre("laura");                           //se define un nombre
        a.setEmail("laura@correo.com");                 //se define un correo
        a.setPassword("laura123");                      //se define una contraseña

        //Guardamos el registro
        Administrador guardado = administradorRepo.save(a);

        //Luego lo eliminamos
        administradorRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Administrador buscado = administradorRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba la eliminacion de un administrador
    @Test
    @Sql("classpath:Administradores.sql")
    public void eliminarAdministradorTestSql()
    {
        //Buscamos el administrador a eliminar
        Administrador eliminar = administradorRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        administradorRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Administrador buscar = administradorRepo.findById(1).orElse(null);
        Assertions.assertNull(buscar);
    }

    //metodo que prueba la actualizacion de informacion de un administrador
    @Test
    public void actualizarAdministradorTest()
    {
        //se inicializa el administrador
        Administrador a = new Administrador();
        a.setNombre("laura");                           //se define un nombre
        a.setEmail("laura@correo.com");                 //se define un correo
        a.setPassword("laura123");                      //se define una contraseña

        //Guardamos el registro
        Administrador registrado = administradorRepo.save(a);

        //Modificamos el nombre
        registrado.setNombre("laurita");

        //Con save guardamos el registro modificado
        administradorRepo.save(registrado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("laurita", registrado.getNombre());
    }

    //metodo que prueba la actualizacion de informacion de un administrador
    @Test
    @Sql("classpath:Administradores.sql")
    public void actualizarAdministradorTestSql()
    {
        //Buscamos el administrador a actualizar
        Administrador actualizar = administradorRepo.findById(1).orElse(null);

        //Modificamos el nombre
        actualizar.setNombre("laurita");

        //Con save guardamos el registro modificado
        administradorRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("laurita", actualizar.getNombre());
    }

    //metodo que lista los administradores almacenados
    @Test
    public void listarAdministradorTest()
    {
        //se inicializa el administrador
        Administrador a = new Administrador();
        a.setNombre("laura");                           //se define un nombre
        a.setEmail("laura@correo.com");                 //se define un correo
        a.setPassword("laura123");                      //se define una contraseña

        //Guardamos el registro
        Administrador registrado = administradorRepo.save(a);

        //Obtenemos la lista de todos los Administradores
        List<Administrador> lista = administradorRepo.findAll();

        //Imprimimos la lista
        for (Administrador admin : lista)
        {
            System.out.println(admin);
        }
    }

    //metodo que lista los administradores almacenados
    @Test
    @Sql("classpath:Administradores.sql")
    public void listarAdministradorTestSql()
    {
        //Obtenemos la lista de todos los Administradores
        List<Administrador> lista = administradorRepo.findAll();

        //Imprimimos la lista
        for (Administrador admin : lista)
        {
            System.out.println(admin);
        }
    }
}
