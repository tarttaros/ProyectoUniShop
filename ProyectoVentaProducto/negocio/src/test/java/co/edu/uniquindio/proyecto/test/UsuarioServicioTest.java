package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void registrarUsuarioTest() {
        Ciudad c = new Ciudad("toscana");
        Ciudad reg = ciudadRepo.save(c);
        Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
        u.setCodigo(1);

        try {
            Usuario respuesta = usuarioServicio.registrarUsuario(u);
            Assertions.assertNotNull(respuesta);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }

    @Test
    public void eliminarUsuarioTest() {
        try {
            Ciudad c = new Ciudad("toscana");
            Ciudad reg = ciudadRepo.save(c);
            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            u.setCodigo(1);
            usuarioServicio.registrarUsuario(u);
            usuarioServicio.eliminarUsuario(23);
            Assertions.assertTrue(true);
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    public void listar() throws Exception {
        try {
            Ciudad c = new Ciudad("toscana");
            Ciudad reg = ciudadRepo.save(c);

            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            u.setCodigo(1);
            usuarioServicio.registrarUsuario(u);

            List<Usuario> lista = usuarioServicio.listarUsuarios();
            lista.forEach( System.out::println );
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.assertTrue(false);
        }
    }
    @Test
    public void actualizar(){

        try{
            Ciudad c = new Ciudad("toscana");
            Ciudad reg = ciudadRepo.save(c);

            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            u.setCodigo(1);
            usuarioServicio.registrarUsuario(u);
            Usuario b= usuarioServicio.obtenerUsuario(1);
            b.setPassword("&(/_2732");
            Usuario respuesta = usuarioServicio.actualizarUsuario(b);
            Assertions.assertNotNull(respuesta);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void loginTest(){
        try {
            Ciudad c = new Ciudad("toscana");
            Ciudad reg = ciudadRepo.save(c);

            Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
            u.setCodigo(1);
            usuarioServicio.registrarUsuario(u);
            Usuario log=usuarioServicio.iniciarSesion("Juan@gmail.com","1234");
            Assertions.assertNotNull(log);

        }catch (Exception e){
            e.printStackTrace();

            Assertions.assertTrue(false,e.getMessage());
        }
    }

}

