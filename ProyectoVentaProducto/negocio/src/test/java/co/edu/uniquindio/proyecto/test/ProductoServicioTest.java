package co.edu.uniquindio.proyecto.test;


import co.edu.uniquindio.proyecto.NegocioApplication;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@SpringBootTest(classes = NegocioApplication.class)
//@Transactional
public class ProductoServicioTest
{

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadRepo ciudadRepo;

    @Test
    public void obtenerProductoTest() throws Exception
    {

        Ciudad c = new Ciudad("toscana");
        Ciudad reg = ciudadRepo.save(c);

        Usuario u = new Usuario(1,"juan", "Juan@gmail.com", "1234", null);
        Usuario r =usuarioServicio.registrarUsuario(u);
        LocalDateTime ldt =LocalDateTime.of(2021,12,22,20,10);
        Producto producto= new Producto("Televisor LG",4,"LG 22 pulgadas",1000000.0,0.0,r,reg);
        Producto publicado = productoServicio.publicarProducto(producto);
        Assertions.assertNotNull(publicado);

    }


}
