package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ComentarioRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ComentarioTest
{
    //variable que representa al repositorio
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private ComentarioRepo comentarioRepo;

    //Metodo que prueba el registrar un comentario
    @Test
    public void registrarComentarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del comprador
        u.setEmail("laura@correo.com");                    //se define el correo del comprador
        u.setPassword("laura123");                         //se define la contraseña del comprador
        u.setCiudad(c);                                    //se define la ciudad de residencia del comprador

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa el vendedor del producto
        Usuario u1 = new Usuario();
        u1.setNombre("Gabriela");                           //se define el nombre del vendedor
        u1.setEmail("gabriela@correo.com");                 //se define el correo del vendedor
        u1.setPassword("gabriela123");                      //se define la contraseña del vendedor
        u1.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg2 = usuarioRepo.save(u1);

        //se inicializa la lista de imagenes
        List<String> imagenes = new ArrayList<String>();

        //se almacena la imagen del producto
        imagenes.add("http//external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fqph.fs.quoracdn.net%2Fmain-qimg-27dcd2ab5609c7c1271d610c16418919-c&f=1&nofb=1");

        //se inicializa el producto a vender
        Producto p = new Producto();
        p.setNombre("televisor");                          //se define el nombre del producto
        p.setCantidad(1);                                  //se define la cantidad a vender
        p.setDescripcion("televisor de 65 pulgadas");      //se define la descripcion del producto
        p.setPrecio(1900000.0);                            //se define el percio del producto
        p.setDescuento(0.0);                                 //se define el descuento del producto
        p.setVendedor(u1);                                 //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg3 = productoRepo.save(p);

        //se inicializa el comentario
        Comentario coment = new Comentario();
        coment.setProducto(p);                             //se define el producto a comentar
        coment.setUsuario(u);                              //se define el usuario que comenta
        coment.setCalificacion(0);                         //se define la calificacion
        coment.setMensaje("muy buen producto");            //se define el comentario del usuario

        //Guardamos el registro
        Comentario guardado = comentarioRepo.save(coment);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el registrar un comentario
    @Test
    @Sql("classpath:Comentarios.sql")
    public void registrarComentarioTestSql()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del comprador
        u.setEmail("laura@correo.com");                    //se define el correo del comprador
        u.setPassword("laura123");                         //se define la contraseña del comprador
        u.setCiudad(c);                                    //se define la ciudad de residencia del comprador

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa el vendedor del producto
        Usuario u1 = new Usuario();
        u1.setNombre("Gabriela");                           //se define el nombre del vendedor
        u1.setEmail("gabriela@correo.com");                 //se define el correo del vendedor
        u1.setPassword("gabriela123");                      //se define la contraseña del vendedor
        u1.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg2 = usuarioRepo.save(u1);

        //se inicializa la lista de imagenes
        List<String> imagenes = new ArrayList<String>();

        //se almacena la imagen del producto
        imagenes.add("http//external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fqph.fs.quoracdn.net%2Fmain-qimg-27dcd2ab5609c7c1271d610c16418919-c&f=1&nofb=1");

        //se inicializa el producto a vender
        Producto p = new Producto();
        p.setNombre("televisor");                          //se define el nombre del producto
        p.setCantidad(1);                                  //se define la cantidad a vender
        p.setDescripcion("televisor de 65 pulgadas");      //se define la descripcion del producto
        p.setPrecio(1900000.0);                            //se define el percio del producto
        p.setDescuento(0.0);                                 //se define el descuento del producto
        p.setVendedor(u1);                                 //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg3 = productoRepo.save(p);

        //se inicializa el comentario
        Comentario coment = new Comentario();
        coment.setProducto(p);                             //se define el producto a comentar
        coment.setUsuario(u);                              //se define el usuario que comenta
        coment.setCalificacion(0);                         //se define la calificacion
        coment.setMensaje("muy buen producto");            //se define el comentario del usuario

        //Guardamos el registro
        Comentario guardado = comentarioRepo.save(coment);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el eliminar un comentario
    @Test
    public void eliminarComentarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del comprador
        u.setEmail("laura@correo.com");                    //se define el correo del comprador
        u.setPassword("laura123");                         //se define la contraseña del comprador
        u.setCiudad(c);                                    //se define la ciudad de residencia del comprador

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa el vendedor del producto
        Usuario u1 = new Usuario();
        u1.setNombre("Gabriela");                           //se define el nombre del vendedor
        u1.setEmail("gabriela@correo.com");                 //se define el correo del vendedor
        u1.setPassword("gabriela123");                      //se define la contraseña del vendedor
        u1.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg2 = usuarioRepo.save(u1);

        //se inicializa la lista de imagenes
        List<String> imagenes = new ArrayList<String>();

        //se almacena la imagen del producto
        imagenes.add("http//external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fqph.fs.quoracdn.net%2Fmain-qimg-27dcd2ab5609c7c1271d610c16418919-c&f=1&nofb=1");

        //se inicializa el producto a vender
        Producto p = new Producto();
        p.setNombre("televisor");                          //se define el nombre del producto
        p.setCantidad(1);                                  //se define la cantidad a vender
        p.setDescripcion("televisor de 65 pulgadas");      //se define la descripcion del producto
        p.setPrecio(1900000.0);                            //se define el percio del producto
        p.setDescuento(0.0);                                 //se define el descuento del producto
        p.setVendedor(u1);                                 //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg3 = productoRepo.save(p);

        //se inicializa el comentario
        Comentario coment = new Comentario();
        coment.setProducto(p);                             //se define el producto a comentar
        coment.setUsuario(u);                              //se define el usuario que comenta
        coment.setCalificacion(0);                         //se define la calificacion
        coment.setMensaje("muy buen producto");            //se define el comentario del usuario

        //Guardamos el registro
        Comentario guardado = comentarioRepo.save(coment);

        //Luego lo eliminamos
        comentarioRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Comentario buscado = comentarioRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //Metodo que prueba el eliminar un comentario
    @Test
    @Sql("classpath:Comentarios.sql")
    public void eliminarComentarioTestSql()
    {
        //Buscamos el comentario a eliminar
        Comentario eliminar = comentarioRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        comentarioRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Comentario buscar = comentarioRepo.findById(1).orElse(null);
        Assertions.assertNull(buscar);
    }

    //Metodo que prueba el actualizar un comentario
    @Test
    public void actualizarComentarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del comprador
        u.setEmail("laura@correo.com");                    //se define el correo del comprador
        u.setPassword("laura123");                         //se define la contraseña del comprador
        u.setCiudad(c);                                    //se define la ciudad de residencia del comprador

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa el vendedor del producto
        Usuario u1 = new Usuario();
        u1.setNombre("Gabriela");                           //se define el nombre del vendedor
        u1.setEmail("gabriela@correo.com");                 //se define el correo del vendedor
        u1.setPassword("gabriela123");                      //se define la contraseña del vendedor
        u1.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg2 = usuarioRepo.save(u1);

        //se inicializa la lista de imagenes
        List<String> imagenes = new ArrayList<String>();

        //se almacena la imagen del producto
        imagenes.add("http//external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fqph.fs.quoracdn.net%2Fmain-qimg-27dcd2ab5609c7c1271d610c16418919-c&f=1&nofb=1");

        //se inicializa el producto a vender
        Producto p = new Producto();
        p.setNombre("televisor");                          //se define el nombre del producto
        p.setCantidad(1);                                  //se define la cantidad a vender
        p.setDescripcion("televisor de 65 pulgadas");      //se define la descripcion del producto
        p.setPrecio(1900000.0);                            //se define el percio del producto
        p.setDescuento(0.0);                                 //se define el descuento del producto
        p.setVendedor(u1);                                 //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg3 = productoRepo.save(p);

        //se inicializa el comentario
        Comentario coment = new Comentario();
        coment.setProducto(p);                             //se define el producto a comentar
        coment.setUsuario(u);                              //se define el usuario que comenta
        coment.setCalificacion(0);                         //se define la calificacion
        coment.setMensaje("muy buen producto");            //se define el comentario del usuario

        //Guardamos el registro
        Comentario guardado = comentarioRepo.save(coment);

        //Modificamos el comentario del producto
        guardado.setMensaje("no es tan bueno como pense");

        //Guardamos el registro
        comentarioRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("no es tan bueno como pense", guardado.getMensaje());
    }

    //Metodo que prueba el actualizar un comentario
    @Test
    @Sql("classpath:Comentarios.sql")
    public void actualizarComentarioTestSql()
    {
        //Buscamos el comentario a eliminar
        Comentario actualizar = comentarioRepo.findById(1).orElse(null);

        //Modificamos el comentario del producto
        actualizar.setMensaje("no es tan bueno como pense");

        //Guardamos el registro
        comentarioRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("no es tan bueno como pense", actualizar.getMensaje());
    }

    //Metodo que prueba el listar los comentarios
    @Test
    public void listararComentarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del comprador
        u.setEmail("laura@correo.com");                    //se define el correo del comprador
        u.setPassword("laura123");                         //se define la contraseña del comprador
        u.setCiudad(c);                                    //se define la ciudad de residencia del comprador

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //se inicializa el vendedor del producto
        Usuario u1 = new Usuario();
        u1.setNombre("Gabriela");                           //se define el nombre del vendedor
        u1.setEmail("gabriela@correo.com");                 //se define el correo del vendedor
        u1.setPassword("gabriela123");                      //se define la contraseña del vendedor
        u1.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg2 = usuarioRepo.save(u1);

        //se inicializa la lista de imagenes
        List<String> imagenes = new ArrayList<String>();

        //se almacena la imagen del producto
        imagenes.add("http//external-content.duckduckgo.com/iu/?u=https%3A%2F%2Fqph.fs.quoracdn.net%2Fmain-qimg-27dcd2ab5609c7c1271d610c16418919-c&f=1&nofb=1");

        //se inicializa el producto a vender
        Producto p = new Producto();
        p.setNombre("televisor");                          //se define el nombre del producto
        p.setCantidad(1);                                  //se define la cantidad a vender
        p.setDescripcion("televisor de 65 pulgadas");      //se define la descripcion del producto
        p.setPrecio(1900000.0);                            //se define el percio del producto
        p.setDescuento(0.0);                                 //se define el descuento del producto
        p.setVendedor(u1);                                 //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg3 = productoRepo.save(p);

        //se inicializa el comentario
        Comentario coment = new Comentario();
        coment.setProducto(p);                             //se define el producto a comentar
        coment.setUsuario(u);                              //se define el usuario que comenta
        coment.setCalificacion(0);                         //se define la calificacion
        coment.setMensaje("muy buen producto");            //se define el comentario del usuario

        //Guardamos el registro
        Comentario guardado = comentarioRepo.save(coment);

        //Obtenemos la lista de todos los comentarios
        List<Comentario> lista = comentarioRepo.findAll();

        //Imprimimos la lista
        for (Comentario com : lista)
        {
            System.out.println(com);
        }
    }

    //Metodo que prueba el listar los comentarios
    @Test
    @Sql("classpath:Comentarios.sql")
    public void listararComentarioTestSql()
    {
        //Obtenemos la lista de todos los comentarios
        List<Comentario> lista = comentarioRepo.findAll();

        //Imprimimos la lista
        for (Comentario com : lista)
        {
            System.out.println(com);
        }
    }
}
