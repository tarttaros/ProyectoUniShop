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
    //para loggin
    Optional<Usuario> findByEmailAndPassword (String email, String password);

    //registro y actualizacion usuario
    Optional<Usuario> findByNombre (String nombre);

    //busca un usuario por email
    Optional<Usuario> findByEmail (String email);

    //para listar productos favoritos
    @Query("select producto from Usuario usuario, in (usuario.productosFavoritos) producto where usuario.email = ?1 ")
    List<Producto> obtenerProductosFavoritos(String email);
}
