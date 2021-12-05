package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer>
{
    //para loggin
    Optional<Usuario> findByEmailAndPassword (String email, String password);

    //registro y actualizacion usuario
    Optional<Usuario> findByNombre (String nombre);

    //busca un usuario por email
    Optional<Usuario> findByEmail (String email);

    //para listar productos favoritos
    @Query("select producto from Usuario usuario, in (usuario.productosFavoritos) producto where usuario.email = ?1 ")
    List<Producto> obtenerProductosFavoritos(String email);

    //buscar un producto favorito
    @Query("select producto from Usuario usuario, in (usuario.productosFavoritos) producto where usuario = ?1 and producto = ?2")
    Optional<Producto> obtenerUnProductoFavorito(Usuario u, Producto p);

    //productos comprados por un cliente
    @Query("select detalle from Usuario usuario, in (usuario.compras) compra , in (compra.detalleCompra) detalle where usuario = ?1 order by detalle.precio desc")
    List<DetalleCompra> productoComprado(Usuario usuario);

    @Query("select producto from Producto producto where producto.vendedor = ?1")
    List<Producto> productosUsuario(Usuario usuario);

    @Query("select producto from Subasta subasta, in (subasta.producto) producto where producto.vendedor = ?1")
    List<Producto> listarSubastasRealizadas(Usuario usuario);
}
