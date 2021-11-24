package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Producto;
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
        Usuario u = new Usuario(1, "Juanita","juana@mail.com","juana1",null);
        //u.setCodigo(1);
        try {
        //Guardamos el registro
            Usuario guardado = usuarioServicio.registrarUsuario(u);
        //Comprobamos que si haya quedado
            Assertions.assertNotNull(guardado);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void actualizarUsuarioTest()
    {
        try {
            Usuario u = usuarioServicio.obtenerUsuario(1);
            //Guardamos el registro
            u.setEmail("juana@mail.com");
            Usuario actualizar = usuarioServicio.actualizarUsuario(u);
            //Comprobamos que si haya quedado
            Assertions.assertEquals("pepe@mail.com",actualizar.getEmail());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void eliminarUsuarioTest()
    {
        try
        {
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
    public void listarUsuariosTest()
    {
        List<Usuario> lista = usuarioServicio.listarUsuarios();
    }

    @Test
    public void listarProductosFavoritosTest()
    {
        try
        {
            List<Producto> lista = usuarioServicio.listarProductosFavoritos("maria@mail.com");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
