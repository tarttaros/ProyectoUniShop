package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Subasta;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.SubastaRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
public class SubastaTest
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

    //metodo que prueba el crear una subasta
    @Test
    public void registrarSubastaTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el vendedor del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del vendedor
        u.setEmail("laura@correo.com");                    //se define el correo del vendedor
        u.setPassword("laura123");                         //se define la contraseña del vendedor
        u.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

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
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg2 = productoRepo.save(p);

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta guardado = subastaRepo.save(subast);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el crear una subasta
    @Test
    @Sql("classpath:Subastas.sql")
    public void registrarSubastaTestSql()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("london");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el vendedor del producto
        Usuario u = new Usuario();
        u.setNombre("mafe");                              //se define el nombre del vendedor
        u.setEmail("mafe@correo.com");                    //se define el correo del vendedor
        u.setPassword("mafe123");                         //se define la contraseña del vendedor
        u.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

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
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg2 = productoRepo.save(p);

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta guardado = subastaRepo.save(subast);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el eliminar una subasta
    @Test
    public void eliminarSubastaTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el vendedor del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del vendedor
        u.setEmail("laura@correo.com");                    //se define el correo del vendedor
        u.setPassword("laura123");                         //se define la contraseña del vendedor
        u.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

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
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg2 = productoRepo.save(p);

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta guardado = subastaRepo.save(subast);

        //Luego lo eliminamos
        subastaRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Subasta buscado = subastaRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el eliminar una subasta
    @Test
    @Sql("classpath:Subastas.sql")
    public void eliminarSubastaTestSql()
    {
        //Buscamos el Subasta a eliminar
        Subasta eliminar = subastaRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        subastaRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Subasta buscado = subastaRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el actualizar una subasta
    @Test
    public void actualizarSubastaTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el vendedor del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del vendedor
        u.setEmail("laura@correo.com");                    //se define el correo del vendedor
        u.setPassword("laura123");                         //se define la contraseña del vendedor
        u.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

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
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg2 = productoRepo.save(p);

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta guardado = subastaRepo.save(subast);

        //Modificamos la fecha limite de la subasta
        guardado.setFechaLimite(LocalDateTime.of(2023, 9, 29,12,0,0));

        //Guardamos el registro
        subastaRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals(LocalDateTime.of(2023, 9, 29,12,0,0), guardado.getFechaLimite());
    }

    //metodo que prueba el actualizar una subasta
    @Test
    @Sql("classpath:Subastas.sql")
    public void actualizarSubastaTestSql()
    {
        //Buscamos el Subasta a actualizar
        Subasta actualizar = subastaRepo.findById(1).orElse(null);

        //Modificamos la fecha limite de la subasta
        actualizar.setFechaLimite(LocalDateTime.of(2023, 9, 29,12,0,0));

        //Guardamos el registro
        subastaRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals(LocalDateTime.of(2023, 9, 29,12,0,0), actualizar.getFechaLimite());
    }

    //metodo que prueba el listar las suabstas
    @Test
    public void listarSubastaTest()
    {
        //se inicializa la ciudad
        Ciudad c = new Ciudad("milan");

        //Guardamos el registro
        Ciudad reg = ciudadRepo.save(c);

        //se inicializa el vendedor del producto
        Usuario u = new Usuario();
        u.setNombre("laura");                              //se define el nombre del vendedor
        u.setEmail("laura@correo.com");                    //se define el correo del vendedor
        u.setPassword("laura123");                         //se define la contraseña del vendedor
        u.setCiudad(c);                                    //se define la ciudad de residencia del vendedor

        //Guardamos el registro
        Usuario reg1 = usuarioRepo.save(u);

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
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg2 = productoRepo.save(p);

        //se inicializa una subasta
        Subasta subast = new Subasta();
        subast.setProducto(p);                             //se define el producto a subastar
        subast.setFechaLimite(LocalDateTime.of(2022, 9, 29,12,0,0));                       //se define la fecha de cierre de la subasta

        //Guardamos el registro
        Subasta guardado = subastaRepo.save(subast);

        //Obtenemos la lista de todas las subastas
        List<Subasta> lista = subastaRepo.findAll();

        //Imprimimos la lista
        for (Subasta subt : lista)
        {
            System.out.println(subt);
        }
    }

    //metodo que prueba el listar las suabstas
    @Test
    @Sql("classpath:Subastas.sql")
    public void listarSubastaTestSql()
    {
        //Obtenemos la lista de todas las subastas
        List<Subasta> lista = subastaRepo.findAll();

        //Imprimimos la lista
        for (Subasta subt : lista)
        {
            System.out.println(subt);
        }
    }
}
