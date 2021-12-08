package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ProductoRepo extends JpaRepository<Producto, Integer>
{

    //cantidad de productos vendidos por categoria
    @Query("select producto, sum( detalle.unidades ) as total from  Categoria categoria , in (categoria.listaProductos) producto, in (producto.detallesVenta) detalle where categoria.nombre = ?1 group by producto order by total desc ")
    List<Object[]> productoVendido(String nombre);

    //productos subastados por categoria
    @Query("select count( subastas.producto ) as total, producto from  Categoria categoria , in (categoria.listaProductos) producto, in (producto.subastasProducto) subastas group by categoria order by total desc ")
    List<Object[]> productoSubasta();

    //los chats de un vededor
    @Query("select chat, producto.vendedor from Producto producto, in (producto.chatsProducto) chat where producto.vendedor.nombre = ?1")
    List<Object[]> chatsVendedor(String nombre);

    //la cantidad de compras por medio de pago
    @Query("select count(compra.medioDPago), compra.medioDPago from Compra compra group by compra.medioDPago")
    List<Object[]> comprasMedioPago();

    //cantidad de subastas por una categoria en especifico
    @Query("Select subasta from Subasta subasta, in (subasta.producto) producto, in (producto.categoriasProducto) categoria where categoria.nombre =  ?1 and subasta.fechaLimite > CURRENT_DATE")
    List<Subasta> subastasCategoriaEspecifica(String categoria);

    //comentarios que aun no han sido respondidos
    @Query("Select comentario from Producto producto, in (producto.comentarios) comentario where comentario.respuesta is null and producto.nombre = ?1 group by comentario")
    List<Comentario> comentariosNoRespuesta(String nombre);

    //listar productos en un rango de descuentos
    @Query("Select producto from Producto producto where producto.descuento between ?1 and ?2 and producto.cantidad > 0")
    List<Producto> productoRango(Integer bajo, Integer alto);

    //las pujas de cada usuario en una subasta
    @Query("select usuario, subastaUsuario.valor as pujas from Subasta subasta, in  (subasta.pujasSubasta) subastaUsuario, in (subastaUsuario.usuario ) usuario where subasta.codigo= ?1 group by usuario order by pujas desc ")
    List<Usuario> buscar(Integer codigoSubasta);

    //valor de la compra total de un usuario
    @Query("select sum(detalleCompra.unidades * detalleCompra.precio), compra.codigoUsuario from Compra compra, in (compra.detalleCompra) detalleCompra where compra.codigoUsuario.nombre = ?1 group by compra.Codigo")
    List<Object[]> busqueda2(String nombreUsuario);

    //busqueda de producto por nombre
    @Query("select p from Producto p where p.nombre like concat('%', :nombre, '%' ) ")
    List<Producto> buscarProductoNombre(String nombre);

    @Query("select p from Producto p where :categoria member of p.categoriasProducto")
    List<Producto> listarSegunUnaCategoria(Categoria categoria);

    @Query("select p from Categoria c, in (c.listaProductos) p where p.cantidad > 0 and c = ?1 order by c.nombre")
    List<Producto> listarPorCategoria(Categoria categoria);

    @Query("select p from Producto p where p.codigoProducto=?1")
    Producto obtenerProductoPorId(Integer codigo);

    @Query("select p From Producto p where p.fecha<?1" )
    List<Producto> pasadosFechas(LocalDateTime actual);

    @Query("select producto from Producto producto where producto.subastasProducto is empty")
    List<Producto> listarProductosEnVenta();

    @Query("select p from Producto p where p.precio>?1 and p.precio< ?2" )
    List<Producto> listarPorPrecio(Double precio1, Double precio2);

    @Query("select p from Producto p where p.ciudad.nombreCiudad=?1" )
    List<Producto> listarPorCiudad(String nombre);

}
