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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioTest
{
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarUsuarioTest(){
        Ciudad c = new Ciudad();
        c.setNombreCiudad("toscana");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("celular","3133333333");

        Usuario u = new Usuario();
        u.setNombre("laura");
        u.setEmail("laura@correo.com");
        u.setPassword("laura123");
        u.setTelefonos(telefonos);
        u.setCiudad(c);

        //Guardamos el registro
        Usuario guardado = usuarioRepo.save(u);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }
/*
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
    */

    @Test
    public void listarAdministradorTest(){
        Ciudad c = new Ciudad();
        c.setNombreCiudad("toscana");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        Map<String,String> telefonos = new HashMap<>();
        telefonos.put("celular","3133333333");

        Usuario u = new Usuario();
        u.setNombre("laura");
        u.setEmail("laura@correo.com");
        u.setPassword("laura123");
        u.setTelefonos(telefonos);
        u.setCiudad(c);

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


}
