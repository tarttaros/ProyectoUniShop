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
public class DetalleCompraTest
{
    //variable que representa al repositorio
    @Autowired
    private CompraRepo compraRepo;
    @Autowired
    private CiudadRepo ciudadRepo;
    @Autowired
    private UsuarioRepo usuarioRepo;
    @Autowired
    private ProductoRepo productoRepo;
    @Autowired
    private DetalleCompraRepo detalleCompraRepo;

    //Metodo que prueba el registrar un detallecompra
    @Test
    public void resgistrarDetalleCompraTest()
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

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra reg4 = compraRepo.save(comp);

        //se inicializa el detallecompra
        DetalleCompra dc = new DetalleCompra();
        dc.setCodigoCompra(comp);                          //se define la compra
        dc.setPrecio(p.getPrecio());                       //se define el precio del producto
        dc.setUnidades(1);                                 //se define las unidades a comprar
        dc.setProductoComprar(p);                          //se define el producto a comprar

        //Guardamos el registro
        DetalleCompra guardado = detalleCompraRepo.save(dc);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el registrar un detallecompra
    @Test
    @Sql("classpath:DetalleCompras.sql")
    public void resgistrarDetalleCompraTestSql()
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

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra reg4 = compraRepo.save(comp);

        //se inicializa el detallecompra
        DetalleCompra dc = new DetalleCompra();
        dc.setCodigoCompra(comp);                          //se define la compra
        dc.setPrecio(p.getPrecio());                       //se define el precio del producto
        dc.setUnidades(1);                                 //se define las unidades a comprar
        dc.setProductoComprar(p);                          //se define el producto a comprar

        //Guardamos el registro
        DetalleCompra guardado = detalleCompraRepo.save(dc);

        //Comprobamos que si haya guardado
        Assertions.assertNotNull(guardado);
    }

    //Metodo que prueba el eliminar un detallecompra
    @Test
    public void eliminarDetalleCompraTest()
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

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra reg4 = compraRepo.save(comp);

        //se inicializa el detallecompra
        DetalleCompra dc = new DetalleCompra();
        dc.setCodigoCompra(comp);                          //se define la compra
        dc.setPrecio(p.getPrecio());                       //se define el precio del producto
        dc.setUnidades(1);                                 //se define las unidades a comprar
        dc.setProductoComprar(p);                          //se define el producto a comprar

        //Guardamos el registro
        DetalleCompra guardado = detalleCompraRepo.save(dc);

        //Luego lo eliminamos
        detalleCompraRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        DetalleCompra buscado = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //Metodo que prueba el eliminar un detallecompra
    @Test
    @Sql("classpath:DetalleCompras.sql")
    public void eliminarDetalleCompraTestSql()
    {
        //Buscamos el DetalleCompra a eliminar
        DetalleCompra eliminar = detalleCompraRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        detalleCompraRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        DetalleCompra buscado = detalleCompraRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //Metodo que prueba el actualizar un detallecompra
    @Test
    public void actualizarDetalleCompraTest()
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
        p.setCantidad(9);                                  //se define la cantidad a vender
        p.setDescripcion("televisor de 65 pulgadas");      //se define la descripcion del producto
        p.setPrecio(1900000.0);                            //se define el percio del producto
        p.setDescuento(0.0);                                 //se define el descuento del producto
        p.setVendedor(u1);                                 //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg3 = productoRepo.save(p);

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra reg4 = compraRepo.save(comp);

        //se inicializa el detallecompra
        DetalleCompra dc = new DetalleCompra();
        dc.setCodigoCompra(comp);                          //se define la compra
        dc.setPrecio(p.getPrecio());                       //se define el precio del producto
        dc.setUnidades(1);                                 //se define las unidades a comprar
        dc.setProductoComprar(p);                          //se define el producto a comprar

        //Guardamos el registro
        DetalleCompra guardado = detalleCompraRepo.save(dc);

        //Modificamos la cantidad de unidades
        guardado.setUnidades(5);

        //Guardamos el registro
        detalleCompraRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals(5, guardado.getUnidades() );
    }

    //Metodo que prueba el actualizar un detallecompra
    @Test
    @Sql("classpath:DetalleCompras.sql")
    public void actualizarDetalleCompraTestSql()
    {
        //Buscamos el DetalleCompra a actualizar
        DetalleCompra actualizar = detalleCompraRepo.findById(1).orElse(null);

        //Modificamos la cantidad de unidades
        actualizar.setUnidades(5);

        //Guardamos el registro
        detalleCompraRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals(5, actualizar.getUnidades() );
    }

    //Metodo que prueba el listar los detallecompra
    @Test
    public void listarDetalleCompraTest()
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
        p.setCantidad(9);                                  //se define la cantidad a vender
        p.setDescripcion("televisor de 65 pulgadas");      //se define la descripcion del producto
        p.setPrecio(1900000.0);                            //se define el percio del producto
        p.setDescuento(0.0);                                 //se define el descuento del producto
        p.setVendedor(u1);                                 //se define el vendedor del producto
        p.setCiudad(c);                                    //se define la ciudad donde se encuentra el producto
        p.setImagenes(imagenes);                           //se definen las imagenes del producto

        //Guardamos el registro
        Producto reg3 = productoRepo.save(p);

        //se inicializa la compra
        Compra comp = new Compra();
        comp.setCodigoUsuario(u);                          //se define el usuario que compra
        comp.setMedioDPago("paypal");                      //se define el medio de pago

        //Guardamos el registro
        Compra reg4 = compraRepo.save(comp);

        //se inicializa el detallecompra
        DetalleCompra dc = new DetalleCompra();
        dc.setCodigoCompra(comp);                          //se define la compra
        dc.setPrecio(p.getPrecio());                       //se define el precio del producto
        dc.setUnidades(1);                                 //se define las unidades a comprar
        dc.setProductoComprar(p);                          //se define el producto a comprar

        //Guardamos el registro
        DetalleCompra guardado = detalleCompraRepo.save(dc);

        //Obtenemos la lista de todas los detallecompras
        List<DetalleCompra> lista = detalleCompraRepo.findAll();

        //Imprimimos la lista
        for (DetalleCompra detco : lista)
        {
            System.out.println(detco);
        }
    }

    //Metodo que prueba el listar los detallecompra
    @Test
    @Sql("classpath:DetalleCompras.sql")
    public void listarDetalleCompraTestSql()
    {
        //Obtenemos la lista de todas los detallecompras
        List<DetalleCompra> lista = detalleCompraRepo.findAll();

        //Imprimimos la lista
        for (DetalleCompra detco : lista)
        {
            System.out.println(detco);
        }
    }
}
