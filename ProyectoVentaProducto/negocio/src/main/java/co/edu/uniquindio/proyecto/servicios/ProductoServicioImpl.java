package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoExcepcion;
import co.edu.uniquindio.proyecto.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio
{

    private final ProductoRepo productoRepo;

    private final ComentarioRepo comentarioRepo;
    @Autowired
    private final DetalleCompraRepo detalleCompraRepo;

    private final CompraRepo compraRepo;

    @Autowired
    private final SubastaRepo subastaRepo ;


    public ProductoServicioImpl(ProductoRepo productoRepo, CategoriaRepo categoriaRepo, ComentarioRepo comentarioRepo, DetalleCompraRepo detalleCompraRepo, CompraRepo compraRepo, SubastaRepo subastaRepo)
    {
        this.productoRepo = productoRepo;
        this.comentarioRepo = comentarioRepo;
        this.detalleCompraRepo = detalleCompraRepo;
        this.compraRepo = compraRepo;
        this.subastaRepo = subastaRepo;
    }

    @Override
    public Producto publicarProducto(Producto p) throws Exception
    {
        try
        {
            return productoRepo.save(p);
        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public Producto actualizarProducto(Producto p) throws Exception
    {
        Optional<Producto> buscado = productoRepo.findById(p.getCodigoProducto());
        if(buscado.isEmpty())
        {
            throw new Exception("El producto no existe");
        }

        return productoRepo.save(p);
    }

    @Override
    public void eliminarProducto(Integer codigo) throws Exception
    {
        Optional<Producto> producto= productoRepo.findById(codigo);
        if(producto.isEmpty())
        {
            throw  new Exception("El producto no existe");

        }

        productoRepo.deleteById(codigo);
    }

    @Override
    public Producto obtenerProducto(Integer codigo) throws ProductoNoEncontradoExcepcion
    {
        Optional<Producto> producto= productoRepo.findById(codigo);
        if(producto.isEmpty())
        {
            throw new ProductoNoEncontradoExcepcion("El codigo del producto no es valido");
        }
        Producto produc =productoRepo.obtenerProductoPorId(codigo);
        return produc;
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception
    {
        comentario.setFecha(LocalDateTime.now());
        comentarioRepo.save(comentario);
    }

    @Override
    public void guardarProductoFavorito(Producto producto) throws Exception
    {

    }

    @Override
    public void eliminarProductoFavorito(Producto producto) throws Exception
    {

    }

    @Override
    public void comprarProducto(Compra compra) throws Exception
    {

    }

    @Override
    public List<Producto> listaProductos(Categoria categoria) throws Exception
    {
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public List<Producto> buscarProducto(String nombre, String[] filtros)
    {
        return productoRepo.buscarProductoNombre(nombre);
    }

    @Override
    public List<Producto> productosUsuario (Integer codigo) throws Exception
    {
        return null;
    }

    @Override
    public Producto publicarSubasta(Producto p) throws Exception {

        try
        {
            Producto pro= productoRepo.save(p);
            Subasta subasta = new Subasta();
            subasta.setFechaLimite(pro.getFecha());
            subasta.setProducto(pro);



            subastaRepo.save(subasta);
            return pro;

        }
        catch (Exception e)
        {
            throw new Exception(e.getMessage());
        }

    }

    @Override
    public List<Producto> listarTodosProducto() throws Exception
    {
        eliminarPorFechas();
        return productoRepo.listarProductosEnVenta();
    }

    @Override
    public Compra comprarProductos(Usuario usuario, ArrayList<ProductoCarrito> productos, String medioPago) throws Exception {

        Producto aux;
        try {
            Compra c = new Compra();
            c.setFecha(LocalDateTime.now(ZoneId.of("America/Bogota")));
            c.setCodigoUsuario(usuario);
            c.setMedioDPago(medioPago);

            Compra compraGuardada = compraRepo.save(c);

            DetalleCompra dc;
            for (ProductoCarrito p : productos) {
                dc = new DetalleCompra();
                dc.setCodigoCompra(compraGuardada);
                dc.setPrecio(p.getPrecio());
                dc.setUnidades(p.getUnidades());
                dc.setProductoComprar(productoRepo.findById(p.getId()).get());
                detalleCompraRepo.save(dc);
                aux= productoRepo.findById(p.getId()).get();
                aux.setCantidad(productoRepo.findById(p.getId()).get().getCantidad()-p.getUnidades());
                productoRepo.save(aux);

            }
            return compraGuardada;
        }catch (Exception e){

            throw new Exception(e.getMessage());
        }


    }
    public List<Producto> listaProductosSubastados() throws Exception
    {

        return subastaRepo.listarProductoEnSubasta();
    }

    @Override
    public String productosComprados(List<ProductoCarrito> u) {
        String c="";
        for(int i=0;i<u.size();i++){
            c=u.get(i).getNombre()+","+c;
        }
        System.out.println(c);
        return c;
    }

    @Override
    public void  eliminarPorFechas(){


        List<Producto> pro= productoRepo.pasadosFechas(LocalDateTime.now());

        for(Producto p:pro) {
            productoRepo.delete(p);
        }

    }
    @Override
    public List<Producto> buscarCategorias(Categoria categoria){
        return productoRepo.listarPorCategoria(categoria);
    }

    @Override
    public Producto pujarSubasta(Producto p,Double precio) throws Exception {

        Double d=p.getPrecio();
        if(d<precio){
            p.setPrecio(precio);
            return productoRepo.save(p);



        }
        return productoRepo.obtenerProductoPorId(p.getCodigoProducto());
    }
    public List<Producto> listaProductosPrecio (Double precio1, Double precio2) throws Exception
    {
        return productoRepo.listarPorPrecio(precio1,precio2);
    }

    @Override
    public List<Producto> listaProductosCiudad(String nombre) throws Exception
    {
        return productoRepo.listarPorCiudad(nombre);
    }

}
