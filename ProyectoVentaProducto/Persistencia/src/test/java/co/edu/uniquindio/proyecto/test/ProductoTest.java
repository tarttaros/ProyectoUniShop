package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.CiudadRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductoTest
{
    //variable que representa al repositorio
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;

    //metodo que prueba el crear un producto
    @Test
    public void registrarProductoTest()
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
        p.setDescuento(0);                                 //se define el descuento del producto
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto guardado = productoRepo.save(p);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el eliminar un producto
    @Test
    public void eliminarProductoTest()
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
        p.setDescuento(0);                                 //se define el descuento del producto
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto guardado = productoRepo.save(p);

        //Luego lo eliminamos
        productoRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Producto buscado = productoRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el actualizar un producto
    @Test
    public void actualizarProductoTest()
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
        p.setDescuento(0);                                 //se define el descuento del producto
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto guardado = productoRepo.save(p);

        //Modificamos la cantidad de productos a vender
        guardado.setCantidad(2);

        //Guardamos el registro
        productoRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Producto buscado = productoRepo.findById(1).orElse(null);
        Assertions.assertEquals(2, buscado.getCantidad());
    }

    //metodo que prueba el listar los productos
    @Test
    public void listarProductoTest()
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
        p.setDescuento(0);                                 //se define el descuento del producto
        p.setVendedor(u);                                  //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto guardado = productoRepo.save(p);

        //Obtenemos la lista de todos los productos
        List<Producto> lista = productoRepo.findAll();

        //Imprimimos la lista
        for (Producto produc : lista)
        {
            System.out.println(produc);
        }
    }
}
