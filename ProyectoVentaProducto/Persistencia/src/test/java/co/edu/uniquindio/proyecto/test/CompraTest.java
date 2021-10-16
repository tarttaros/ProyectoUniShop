package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Chat;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.CompraRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompraTest
{
    //variable que representa al repositorio
    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    //Metodo que prueba el registrar una compra
    @Test
    public void resgistrarCompraTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre de la persona
        u.setEmail("laura@correo.com");                    //se define el correo de la persona
        u.setPassword("laura123");                         //se define la contraseña de la persona
        u.setCiudad(c);                                    //se define la ciudad de residencia de la persona

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra guardado = compraRepo.save(comp);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el registrar una compra
    @Test
    @Sql("classpath:Compras.sql")
    public void resgistrarCompraTestSql()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("Edinburgh");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("Mafe");                              //se define el nombre de la persona
        u.setEmail("mafe@correo.com");                    //se define el correo de la persona
        u.setPassword("mafe123");                         //se define la contraseña de la persona
        u.setCiudad(c);                                    //se define la ciudad de residencia de la persona

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("contra entrega");                      //se define el medio de pago

        //Guardamos el registro
        Compra guardado = compraRepo.save(comp);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el eliminar una compra
    @Test
    public void eliminarCompraTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre de la persona
        u.setEmail("laura@correo.com");                    //se define el correo de la persona
        u.setPassword("laura123");                         //se define la contraseña de la persona
        u.setCiudad(c);                                    //se define la ciudad de residencia de la persona

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra guardado = compraRepo.save(comp);

        //Luego lo eliminamos
        compraRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Compra buscado = compraRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //Metodo que prueba el eliminar una compra
    @Test
    @Sql("classpath:Compras.sql")
    public void eliminarCompraTestSql()
    {
        //Buscamos la compra a eliminar
        Compra eliminar = compraRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        compraRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Compra buscar = compraRepo.findById(1).orElse(null);
        Assertions.assertNull(buscar);
    }

    //Metodo que prueba el actualizar una compra
    @Test
    public void actualizarCompraTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre de la persona
        u.setEmail("laura@correo.com");                    //se define el correo de la persona
        u.setPassword("laura123");                         //se define la contraseña de la persona
        u.setCiudad(c);                                    //se define la ciudad de residencia de la persona

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra guardado = compraRepo.save(comp);

        //Modificamos el medio de pago
        guardado.setMedioDPago("efectivo");

        //Guardamos el registro
        compraRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("efectivo", guardado.getMedioDPago());

    }

    //Metodo que prueba el actualizar una compra
    @Test
    @Sql("classpath:Compras.sql")
    public void actualizarCompraTestSql()
    {
        //Buscamos la compra a actualizar
        Compra actualizar = compraRepo.findById(1).orElse(null);

        //Modificamos el medio de pago
        actualizar.setMedioDPago("efectivo");

        //Guardamos el registro
        compraRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("efectivo", actualizar.getMedioDPago());
    }

    //Metodo que prueba el listar las compras
    @Test
    public void listarCompraTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre de la persona
        u.setEmail("laura@correo.com");                    //se define el correo de la persona
        u.setPassword("laura123");                         //se define la contraseña de la persona
        u.setCiudad(c);                                    //se define la ciudad de residencia de la persona

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra guardado = compraRepo.save(comp);

        //Obtenemos la lista de todas las compras
        List<Compra> lista = compraRepo.findAll();

        //Imprimimos la lista
        for (Compra compr : lista)
        {
            System.out.println(compr);
        }
    }

    //Metodo que prueba el listar las compras
    @Test
    @Sql("classpath:Compras.sql")
    public void listarCompraTestSql()
    {
        //Obtenemos la lista de todas las compras
        List<Compra> lista = compraRepo.findAll();

        //Imprimimos la lista
        for (Compra compr : lista)
        {
            System.out.println(compr);
        }
    }
}
