package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest
{
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Test
    public void registrarUsuarioTest()
    {
        Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
        try
        {
            Usuario respuesta = usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(respuesta);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarUsuarioTest()
    {
        try
        {
            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            usuarioServicio.registrarUsuario(u);
            usuarioServicio.eliminarUsuario(1);
            Assertions.assertTrue(true);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void listar() throws Exception
    {
        try
        {
            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            usuarioServicio.registrarUsuario(u);
            List<Usuario> lista = usuarioServicio.listarUsuarios();
            lista.forEach( System.out::println );
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    public void actualizar()
    {
        try
        {
            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            usuarioServicio.registrarUsuario(u);
            Usuario b = usuarioServicio.obtenerUsuario(1);
            b.setPassword("&(/_2732");
            Usuario respuesta = usuarioServicio.actualizarUsuario(b);
            Assertions.assertEquals("&(/_2732",b.getPassword());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Test
    public void loginTest()
    {
        try
        {
            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            usuarioServicio.registrarUsuario(u);
            Usuario log = usuarioServicio.iniciarSesion("Juan@gmail.com","1234");
            Assertions.assertNotNull(log);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            Assertions.assertTrue(false,e.getMessage());
        }
    }

}

