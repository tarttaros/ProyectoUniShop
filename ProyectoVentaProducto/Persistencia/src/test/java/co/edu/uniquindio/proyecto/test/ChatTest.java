package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.ChatRepo;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
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
public class ChatTest
{
    //variable que representa al repositorio
    @Autowired
    private ChatRepo chatRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;

    //Metodo que prueba el registrar un chat
    @Test
    public void registrarChatTest()
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

        //se inicializa el chat
        Chat ch = new Chat();
        ch.setUsuarioComprador(u);                         //se define el comprador del producto
        ch.setProductoComprar(p);                          //se define el producto a vender

        //Guardamos el registro
        Chat guardado = chatRepo.save(ch);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el registrar un chat
    @Test
    @Sql("classpath:Chats.sql")
    public void registrarChatTestSql()
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

        //se inicializa el chat
        Chat ch = new Chat();
        ch.setUsuarioComprador(u);                         //se define el comprador del producto
        ch.setProductoComprar(p);                          //se define el producto a vender

        //Guardamos el registro
        Chat guardado = chatRepo.save(ch);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el eliminar un chat
    @Test
    public void eliminarChatTest()
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

        //se inicializa el chat
        Chat ch = new Chat();
        ch.setUsuarioComprador(u);                         //se define el comprador del producto
        ch.setProductoComprar(p);                          //se define el producto a vender

        //Guardamos el registro
        Chat guardado = chatRepo.save(ch);

        //Luego lo eliminamos
        chatRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Chat buscado = chatRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //Metodo que prueba el eliminar un chat
    @Test
    @Sql("classpath:Chats.sql")
    public void eliminarChatTestSql()
    {
        //Buscamos el chat a eliminar
        Chat eliminar = chatRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        chatRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Chat buscar = chatRepo.findById(1).orElse(null);
        Assertions.assertNull(buscar);
    }

    //Metodo que prueba el actualizar un chat
    @Test
    public void actualizarChatTest()
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
        Usuario reg3 = usuarioRepo.save(u1);

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
        Producto reg4 = productoRepo.save(p);

        //se inicializa el producto a vender
        Producto p1 = new Producto();
        p1.setNombre("televisor 4k");                          //se define el nombre del producto
        p1.setCantidad(1);                                  //se define la cantidad a vender
        p1.setDescripcion("televisor de 95 pulgadas");      //se define la descripcion del producto
        p1.setPrecio(3900000.0);                            //se define el percio del producto
        p1.setDescuento(0.0);                                 //se define el descuento del producto
        p1.setVendedor(u1);                                 //se define el vendedor del producto
        p1.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p1.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg5 = productoRepo.save(p);

        //se inicializa el chat
        Chat ch = new Chat();
        ch.setUsuarioComprador(u);                         //se define el comprador del producto
        ch.setProductoComprar(p);                          //se define el producto a vender

        //Guardamos el registro
        Chat guardado = chatRepo.save(ch);

        //Modificamos el producto a comprar
        guardado.setProductoComprar(p1);

        //Guardamos el registro
        chatRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("televisor 4k", guardado.getProductoComprar().getNombre());
    }

    //Metodo que prueba el actualizar un chat
    @Test
    @Sql("classpath:Chats.sql")
    public void actualizarChatTestSql()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("Edinburgh");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el vendedor del producto
        Usuario u = new Usuario();
        u.setNombre("Mafe");                           //se define el nombre del vendedor
        u.setEmail("mafe@correo.com");                 //se define el correo del vendedor
        u.setPassword("mafe123");                      //se define la contraseña del vendedor
        u.setCiudad(c);                                //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

        //Buscamos el chat a eliminar
        Chat actualizar = chatRepo.findById(1).orElse(null);

        //Modificamos el usuario que va a comprar
        actualizar.setUsuarioComprador(u);

        //Guardamos el registro
        chatRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("Mafe", actualizar.getUsuarioComprador().getNombre());
    }

    //Metodo que prueba el listar los chats
    @Test
    public void listarChatTest()
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

        //se inicializa el chat
        Chat ch = new Chat();
        ch.setUsuarioComprador(u);                         //se define el comprador del producto
        ch.setProductoComprar(p);                          //se define el producto a vender

        //Guardamos el registro
        Chat guardado = chatRepo.save(ch);

        //Obtenemos la lista de todos los chats
        List<Chat> lista = chatRepo.findAll();

        //Imprimimos la lista
        for (Chat ch1 : lista) {
            System.out.println(ch1);
        }
    }

    //Metodo que prueba el listar los chats
    @Test
    @Sql("classpath:Chats.sql")
    public void listarChatTestSql()
    {
        //Obtenemos la lista de todos los chats
        List<Chat> lista = chatRepo.findAll();

        //Imprimimos la lista
        for(Chat ch1 :lista)
        {
            System.out.println(ch1);
        }
    }
}
