package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface ProductoServicio {
    Producto PublicarProducto(Producto p) throws Exception;
    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws  Exception;

    Producto obtenerProducto(Integer codigo) throws  Exception;

    List<Producto> listarProducto(Categoria categoria);

    void comentarProducto(String mensaje, Integer calificacion, Usuario usuario,Producto producto) throws  Exception;

    void guardarProductoFavorito(Producto producto, Usuario usuario) throws  Exception;

    void eliminarProductoFavorito(Producto producto, Usuario usuario) throws  Exception;

    void compraProductos(Comparable compra) throws Exception;

    List<Producto> buscarProductos(String nombreDeProducto,String filtros);

    List<Producto> listarProductos(Integer codigoUsuario) throws  Exception;
}
