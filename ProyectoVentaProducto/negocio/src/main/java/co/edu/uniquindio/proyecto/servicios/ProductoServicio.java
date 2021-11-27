package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.*;

import java.util.List;
import java.util.Optional;

public interface ProductoServicio
{
    Producto publicarProducto(Producto producto) throws Exception;

    Producto actualizarProducto(Producto producto) throws Exception;

    Optional<Producto> obtenerProducto(Integer codigo) throws Exception;

    void eliminarProducto(Integer codigo) throws Exception;

    void comentarProducto(Comentario comentario) throws Exception;

    void guardarProductoFavorito(Producto producto) throws Exception;

    void eliminarProductoFavorito(Producto producto) throws Exception;

    void comprarProducto(Compra compra) throws Exception;

    List<Producto> listaProductos(Categoria categoria) throws Exception;

    List<Producto> buscarProducto(String nombre, String[] filtros);

    List<Producto> productosUsuario(Integer codigo) throws Exception;

    List<Producto>  listarTodosProducto() throws Exception;


}
