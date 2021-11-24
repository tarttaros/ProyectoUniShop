package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
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
public class MensajeTest
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
    @Autowired
    private MensajeRepo mensajeRepo;

    //Metodo que prueba el registrar un mensaje
    @Test
    public void registrarMensajeTest()
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
        Chat reg4 = chatRepo.save(ch);

        //se inicializa el mensaje
        Mensaje mj = new Mensaje();
        mj.setMensaje("hola, cuenta con envio?");          //se setea el mensaje a enviar
        mj.setCodigoChat(ch);                              //se setea el chat donde se va a enviar
        mj.setEmisor(u.getNombre());                       //se setea el emisor del mensaje

        //Guardamos el registro
        Mensaje guardado = mensajeRepo.save(mj);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el registrar un mensaje
    @Test
    @Sql("classpath:Mensajes.sql")
    public void registrarMensajeTestSql()
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
        Chat reg4 = chatRepo.save(ch);

        //se inicializa el mensaje
        Mensaje mj = new Mensaje();
        mj.setMensaje("hola, cuenta con envio?");          //se setea el mensaje a enviar
        mj.setCodigoChat(ch);                              //se setea el chat donde se va a enviar
        mj.setEmisor(u.getNombre());                       //se setea el emisor del mensaje

        //Guardamos el registro
        Mensaje guardado = mensajeRepo.save(mj);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el eliminar un mensaje
    @Test
    public void eliminarMensajeTest()
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
        Chat reg4 = chatRepo.save(ch);

        //se inicializa el mensaje
        Mensaje mj = new Mensaje();
        mj.setMensaje("hola, cuenta con envio?");          //se setea el mensaje a enviar
        mj.setCodigoChat(ch);                              //se setea el chat donde se va a enviar
        mj.setEmisor(u.getNombre());                       //se setea el emisor del mensaje

        //Guardamos el registro
        Mensaje guardado = mensajeRepo.save(mj);

        //Luego lo eliminamos
        mensajeRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Mensaje buscado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //Metodo que prueba el eliminar un mensaje
    @Test
    @Sql("classpath:Mensajes.sql")
    public void eliminarMensajeTestSql()
    {
        //Buscamos el mensaje a eliminar
        Mensaje eliminar = mensajeRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        mensajeRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Mensaje buscado = mensajeRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //Metodo que prueba el actualizar un mensaje
    @Test
    public void actualizarMensajeTest()
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
        Chat reg4 = chatRepo.save(ch);

        //se inicializa el mensaje
        Mensaje mj = new Mensaje();
        mj.setMensaje("hola, cuenta con envio?");          //se setea el mensaje a enviar
        mj.setCodigoChat(ch);                              //se setea el chat donde se va a enviar
        mj.setEmisor(u.getNombre());                       //se setea el emisor del mensaje

        //Guardamos el registro
        Mensaje guardado = mensajeRepo.save(mj);

        //Modificamos el mensaje
        guardado.setMensaje("hola, ¿es con envio incluido?");

        //Guardamos el registro
        mensajeRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("hola, ¿es con envio incluido?", guardado.getMensaje());
    }

    //Metodo que prueba el actualizar un mensaje
    @Test
    @Sql("classpath:Mensajes.sql")
    public void actualizarMensajeTestSql()
    {
        //Buscamos el mensaje a actualizar
        Mensaje actualizar = mensajeRepo.findById(1).orElse(null);

        //Modificamos el mensaje
        actualizar.setMensaje("hola, ¿es con envio incluido?");

        //Guardamos el registro
        mensajeRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("hola, ¿es con envio incluido?", actualizar.getMensaje());

    }

    //Metodo que prueba el listar los mensajes
    @Test
    public void listarMensajeTest()
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
        Chat reg4 = chatRepo.save(ch);

        //se inicializa el mensaje
        Mensaje mj = new Mensaje();
        mj.setMensaje("hola, cuenta con envio?");          //se setea el mensaje a enviar
        mj.setCodigoChat(ch);                              //se setea el chat donde se va a enviar
        mj.setEmisor(u.getNombre());                       //se setea el emisor del mensaje

        //Guardamos el registro
        Mensaje guardado = mensajeRepo.save(mj);

        //Obtenemos la lista de todos los mensajes
        List<Mensaje> lista = mensajeRepo.findAll();

        //Imprimimos la lista
        for (Mensaje msj : lista)
        {
            System.out.println(msj);
        }
    }

    //Metodo que prueba el listar los mensajes
    @Test
    @Sql("classpath:Mensajes.sql")
    public void listarMensajeTestSql()
    {
        //Obtenemos la lista de todos los mensajes
        List<Mensaje> lista = mensajeRepo.findAll();

        //Imprimimos la lista
        for (Mensaje msj : lista)
        {
            System.out.println(msj);
        }
    }
}
