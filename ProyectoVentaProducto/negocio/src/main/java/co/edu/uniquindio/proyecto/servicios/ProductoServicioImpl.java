package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoExcepcion;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import co.edu.uniquindio.proyecto.repositorios.ProductoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServicioImpl implements ProductoServicio
{

    private final ProductoRepo productoRepo;


    public ProductoServicioImpl(ProductoRepo productoRepo, CategoriaRepo categoriaRepo)
    {
        this.productoRepo = productoRepo;
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
    public Optional<Producto> obtenerProducto(Integer codigo) throws ProductoNoEncontradoExcepcion
    {
        Optional<Producto> producto= productoRepo.findById(codigo);
        if(producto.isEmpty())
        {
            throw new ProductoNoEncontradoExcepcion("El codigo del producto no es valido");
        }
        return producto;
    }

    @Override
    public void comentarProducto(Comentario comentario) throws Exception
    {

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
    public List<Producto> listarTodosProducto() throws Exception
    {
        return productoRepo.findAll();
    }
}
