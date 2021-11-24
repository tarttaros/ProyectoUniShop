package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepo extends JpaRepository<Usuario, Integer>
{

    Optional<Usuario> findByEmailAndPassword (String email, String password);

    Optional<Usuario> findByEmail (String email);

     List<Usuario> findAllByNombreConstrains (String nombre);


    Page<Usuario> findAll(Pageable paginador);


    //   @Query("select u.productosFavoritos from Usuario u where u.email =:email")
    // List<Producto> obtenerFavoritos(String email);

    @Query("select p from Usuario u, IN (u.productosFavoritos) p where  u.email =:email")
    List<Producto> obtenerFavoritos(String email);

    Optional<Usuario> findByUserName(String username);

    Optional<Usuario> findById(int codigo);



    //  Optional<Usuario> findByUsername(String email);

    //   @Query("select new co.edu.uniquindio.proyecto.dto.UsuarioYproducto(u.email,u.nombre,p) from Usuario u left join u.productosVenta P")
    // List<UsuarioYProducto> listarUsuariosYProductos();
}
