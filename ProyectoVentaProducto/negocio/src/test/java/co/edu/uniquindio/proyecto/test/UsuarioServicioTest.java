package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

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
        Usuario u = new Usuario(1, "Juanita","juana@mail.com","juana1",null);
        //u.setCodigo(1);
        try {
            Usuario guardado = usuarioServicio.registrarUsuario(u);
            //Guardamos el registro
            u.setEmail("juana@mail.com");
            Usuario actualizar = usuarioServicio.actualizarUsuario(u);
            //Comprobamos que si haya quedado
            Assertions.assertNotNull(guardado);
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

}
