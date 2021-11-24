package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SubastaUsuarioTest
{
    //variable que representa al repositorio
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private SubastaRepo subastaRepo;
    @Autowired
    private SubastaUsuarioRepo subastaUsuarioRepo;

    //metodo que prueba el registrar una subastaUsuario
    @Test
    public void registrarSubastaUsuarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del que hace la puja
        u.setEmail("laura@correo.com");                    //se define el correo del que hace la puja
        u.setPassword("laura123");                         //se define la contraseña del que hace la puja
        u.setCiudad(c);                                    //se define la ciudad de residencia del que hace la puja

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

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta reg4 = subastaRepo.save(subast);

        //se inicializa la subasta usuario
        SubastaUsuario subUsuario = new SubastaUsuario();
        subUsuario.setUsuario(u);                          //se define el usuario que pujo
        subUsuario.setSubasta(subast);                     //se define la subasta en la que se pujo
        subUsuario.setFecha(null);                         //se define la fecha en la que se pujo
        subUsuario.setValor(1100000.0);                    //se define el valor de la puja

        //Guardamos el registro
        SubastaUsuario guardado = subastaUsuarioRepo.save(subUsuario);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el registrar una subastaUsuario
    @Test
    @Sql("classpath:SubastaUsuarios.sql")
    public void registrarSubastaUsuarioTestSql()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("london");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del que hace la puja
        u.setEmail("laura@correo.com");                    //se define el correo del que hace la puja
        u.setPassword("laura123");                         //se define la contraseña del que hace la puja
        u.setCiudad(c);                                    //se define la ciudad de residencia del que hace la puja

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

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta reg4 = subastaRepo.save(subast);

        //se inicializa la subasta usuario
        SubastaUsuario subUsuario = new SubastaUsuario();
        subUsuario.setUsuario(u);                          //se define el usuario que pujo
        subUsuario.setSubasta(subast);                     //se define la subasta en la que se pujo
        subUsuario.setFecha(null);                         //se define la fecha en la que se pujo
        subUsuario.setValor(1100000.0);                    //se define el valor de la puja

        //Guardamos el registro
        SubastaUsuario guardado = subastaUsuarioRepo.save(subUsuario);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el eliminar una subastaUsuario
    @Test
    public void eliminarSubastaUsuarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del que hace la puja
        u.setEmail("laura@correo.com");                    //se define el correo del que hace la puja
        u.setPassword("laura123");                         //se define la contraseña del que hace la puja
        u.setCiudad(c);                                    //se define la ciudad de residencia del que hace la puja

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

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta reg4 = subastaRepo.save(subast);

        //se inicializa la subasta usuario
        SubastaUsuario subUsuario = new SubastaUsuario();
        subUsuario.setUsuario(u);                          //se define el usuario que pujo
        subUsuario.setSubasta(subast);                     //se define la subasta en la que se pujo
        subUsuario.setFecha(null);                         //se define la fecha en la que se pujo
        subUsuario.setValor(1100000.0);                    //se define el valor de la puja

        //Guardamos el registro
        SubastaUsuario guardado = subastaUsuarioRepo.save(subUsuario);

        //Luego lo eliminamos
        subastaUsuarioRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        SubastaUsuario buscado = subastaUsuarioRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el eliminar una subastaUsuario
    @Test
    @Sql("classpath:SubastaUsuarios.sql")
    public void eliminarSubastaUsuarioTestSql()
    {
        //Buscamos el SubastaUsuario a eliminar
        SubastaUsuario eliminar = subastaUsuarioRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        subastaUsuarioRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        SubastaUsuario buscado = subastaUsuarioRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el actualizar una subastaUsuario
    @Test
    public void actualizarSubastaUsuarioTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del que hace la puja
        u.setEmail("laura@correo.com");                    //se define el correo del que hace la puja
        u.setPassword("laura123");                         //se define la contraseña del que hace la puja
        u.setCiudad(c);                                    //se define la ciudad de residencia del que hace la puja

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

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta reg4 = subastaRepo.save(subast);

        //se inicializa la subasta usuario
        SubastaUsuario subUsuario = new SubastaUsuario();
        subUsuario.setUsuario(u);                          //se define el usuario que pujo
        subUsuario.setSubasta(subast);                     //se define la subasta en la que se pujo
        subUsuario.setFecha(null);                         //se define la fecha en la que se pujo
        subUsuario.setValor(1100000.0);                    //se define el valor de la puja

        //Guardamos el registro
        SubastaUsuario guardado = subastaUsuarioRepo.save(subUsuario);

        //Modificamos la fecha de la puja
        guardado.setFecha(LocalDateTime.of(2023, 9, 29,12,0,0));

        //Guardamos el registro
        subastaUsuarioRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals(LocalDateTime.of(2023, 9, 29,12,0,0), guardado.getFecha());
    }

    //metodo que prueba el actualizar una subastaUsuario
    @Test
    @Sql("classpath:SubastaUsuarios.sql")
    public void actualizarSubastaUsuarioTestSql()
    {
        //Buscamos el SubastaUsuario a actualizar
        SubastaUsuario actualizar = subastaUsuarioRepo.findById(1).orElse(null);

        //Modificamos la fecha de la puja
        actualizar.setFecha(LocalDateTime.of(2023, 9, 29,12,0,0));

        //Guardamos el registro
        subastaUsuarioRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals(LocalDateTime.of(2023, 9, 29,12,0,0), actualizar.getFecha());
    }

    //metodo que prueba el listar las suabstas
    @Test
    public void listarSubastaTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el comprador del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del que hace la puja
        u.setEmail("laura@correo.com");                    //se define el correo del que hace la puja
        u.setPassword("laura123");                         //se define la contraseña del que hace la puja
        u.setCiudad(c);                                    //se define la ciudad de residencia del que hace la puja

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

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta reg4 = subastaRepo.save(subast);

        //se inicializa la subasta usuario
        SubastaUsuario subUsuario = new SubastaUsuario();
        subUsuario.setUsuario(u);                          //se define el usuario que pujo
        subUsuario.setSubasta(subast);                     //se define la subasta en la que se pujo
        subUsuario.setFecha(null);                         //se define la fecha en la que se pujo
        subUsuario.setValor(1100000.0);                    //se define el valor de la puja

        //Guardamos el registro
        SubastaUsuario guardado = subastaUsuarioRepo.save(subUsuario);

        //Obtenemos la lista de todas las subastaUsuario
        List<SubastaUsuario> lista = subastaUsuarioRepo.findAll();

        //Imprimimos la lista
        for (SubastaUsuario subtUsr : lista)
        {
            System.out.println(subtUsr);
        }
    }

    //metodo que prueba el listar las suabstas
    @Test
    @Sql("classpath:SubastaUsuarios.sql")
    public void listarSubastaTestSql()
    {
        //Obtenemos la lista de todas las subastaUsuario
        List<SubastaUsuario> lista = subastaUsuarioRepo.findAll();

        //Imprimimos la lista
        for (SubastaUsuario subtUsr : lista)
        {
            System.out.println(subtUsr);
        }
    }
}
