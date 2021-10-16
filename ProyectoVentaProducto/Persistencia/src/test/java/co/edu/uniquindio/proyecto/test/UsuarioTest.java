package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest
{
    //variables que representa a los repositorio
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    //metodo que prueba el crear un usuario
    @Test
    public void registrarUsuarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el map de telefonos
        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("celular","3133333333");          //se guarda el numero telefonico

        //se inicializa el administrador
        Usuario u = new Usuario();
        u.setNombre("laura");                           //se define un nombre
        u.setEmail("laura@correo.com");                 //se define un correo
        u.setPassword("laura123");                      //se define una contraseña
        u.setTelefonos(telefonos);                      //se define el map de numeros
        u.setCiudad(c);                                 //se define la ciudad del usuario

        //Guardamos el registro
        Usuario guardado = usuarioRepo.save(u);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el crear un usuario
    @Test
    @Sql("classpath:Usuarios.sql")
    public void registrarUsuarioTestSql()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el map de telefonos
        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("celular","3133333333");          //se guarda el numero telefonico

        //se inicializa el administrador
        Usuario u = new Usuario();
        u.setNombre("laura");                           //se define un nombre
        u.setEmail("laura@correo.com");                 //se define un correo
        u.setPassword("laura123");                      //se define una contraseña
        u.setTelefonos(telefonos);                      //se define el map de numeros
        u.setCiudad(c);                                 //se define la ciudad del usuario

        //Guardamos el registro
        Usuario guardado = usuarioRepo.save(u);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba la eliminacion de un usuario
    @Test
    public void eliminarUsuarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el map de telefonos
        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("celular","3133333333");          //se guarda el numero telefonico

        //se inicializa el administrador
        Usuario u = new Usuario();
        u.setNombre("laura");                           //se define un nombre
        u.setEmail("laura@correo.com");                 //se define un correo
        u.setPassword("laura123");                      //se define una contraseña
        u.setTelefonos(telefonos);                      //se define el map de numeros
        u.setCiudad(c);                                 //se define la ciudad del usuario

        //Guardamos el registro
        Usuario guardado = usuarioRepo.save(u);

        //Luego lo eliminamos
        usuarioRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Usuario buscado = usuarioRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba la eliminacion de un usuario
    @Test
    @Sql("classpath:Usuarios.sql")
    public void eliminarUsuarioTestSql()
    {
        //Buscamos el Usuario a eliminar
        Usuario eliminar = usuarioRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        usuarioRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Usuario buscado = usuarioRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba la actualizacion de informacion de un usuario
    @Test
    public void actualizarUsuarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el map de telefonos
        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("celular","3133333333");          //se guarda el numero telefonico

        //se inicializa el administrador
        Usuario u = new Usuario();
        u.setNombre("laura");                           //se define un nombre
        u.setEmail("laura@correo.com");                 //se define un correo
        u.setPassword("laura123");                      //se define una contraseña
        u.setTelefonos(telefonos);                      //se define el map de numeros
        u.setCiudad(c);                                 //se define la ciudad del usuario

        //Guardamos el registro
        Usuario guardado = usuarioRepo.save(u);

        //Modificamos el nombre
        guardado.setNombre("laurita");

        //Con save guardamos el registro modificado
        usuarioRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("laurita", guardado.getNombre());
    }

    //metodo que prueba la actualizacion de informacion de un usuario
    @Test
    @Sql("classpath:Usuarios.sql")
    public void actualizarUsuarioTestSql()
    {
        //Buscamos el Usuario a actualizar
        Usuario actualizar = usuarioRepo.findById(1).orElse(null);

        //Modificamos el nombre
        actualizar.setNombre("laurita");

        //Con save guardamos el registro modificado
        usuarioRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("laurita", actualizar.getNombre());
    }

    //metodo que prueba el listar los usuario almacenados
    @Test
    public void listarUsuarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("toscana");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el map de telefonos
        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("celular","3133333333");          //se guarda el numero telefonico

        //se inicializa el administrador
        Usuario u = new Usuario();
        u.setNombre("laura");                           //se define un nombre
        u.setEmail("laura@correo.com");                 //se define un correo
        u.setPassword("laura123");                      //se define una contraseña
        u.setTelefonos(telefonos);                      //se define el map de numeros
        u.setCiudad(c);                                 //se define la ciudad del usuario

        //Guardamos el registro
        Usuario guardado = usuarioRepo.save(u);

        //Obtenemos la lista de todos los usuarios
        List<Usuario> lista = usuarioRepo.findAll();

        //Imprimimos la lista
        for (Usuario user : lista)
        {
            System.out.println(user);
        }
    }

    //metodo que prueba el listar los usuario almacenados
    @Test
    @Sql("classpath:Usuarios.sql")
    public void listarUsuarioTestSql()
    {
        //Obtenemos la lista de todos los usuarios
        List<Usuario> lista = usuarioRepo.findAll();

        //Imprimimos la lista
        lista.forEach( usr -> System.out.println(usr));
    }
}
