package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdministradorRepo extends JpaRepository<Administrador, Integer>
{

    Optional<Usuario> findByCodigo(Integer codigo);

    Optional<Usuario> findByEmail(String correo);

    Optional<Administrador> findByEmailAndPassword(String email, String password);
}
