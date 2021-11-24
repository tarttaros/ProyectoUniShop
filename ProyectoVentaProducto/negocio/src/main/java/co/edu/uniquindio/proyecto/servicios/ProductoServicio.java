package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.excepciones.ProductoNoEncontradoExcepcion;

import java.util.List;
import java.util.Optional;

public interface ProductoServicio {



    Producto publicarProducto(Producto p) throws  Exception;


    void actualizarProducto(Producto p) throws Exception;

    void eliminarProducto(Integer codigo) throws  Exception;

    Optional<Producto> obtenerProducto(Integer codigo) throws ProductoNoEncontradoExcepcion;

    List<Producto> listarProducto(Categoria categoria);

    void comentarProducto (String mensaje, Integer calificacion, Usuario usuario, Producto producto) throws  Exception;

    void guardarProductoFavorito(Producto producto,Usuario usuario)throws Exception;

    void eliminarProductoFavorito (Producto producto,Usuario usuario)throws  Exception;

    void comprarProductos (Compra compra) throws  Exception;

    List<Producto> buscarProductos(String nombreProducto, String[] filtros);

    List<Producto> listarProductos(Integer codigoUsuario)throws Exception;

}
