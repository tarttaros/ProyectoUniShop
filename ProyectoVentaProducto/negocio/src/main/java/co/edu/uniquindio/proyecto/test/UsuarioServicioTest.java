package co.edu.uniquindio.proyecto.test;
import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = NegocioApplication.class)
@Transactional
public class UsuarioServicioTest
{
    @Autowired
    private UsuarioServicio usuarioServicio;
}