package co.edu.uniquindio.proyecto.repositorios;

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

    Optional<Usuario> findByEmailAndPassword (String email, String password);

    Optional<Usuario> findByEmail (String email);

    Optional<Usuario> findByNombre (String nombre);

    @Query("select producto from Usuario usuario, in (usuario.productosFavoritos) producto where usuario.email = ?1 ")
    List<Producto> obtenerProductosFavoritos(String email);
}
