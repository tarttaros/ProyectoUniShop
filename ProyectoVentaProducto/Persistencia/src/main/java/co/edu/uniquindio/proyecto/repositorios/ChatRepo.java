package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepo extends JpaRepository<Chat, Integer>
{
    //se seleccionan todos los chats
    @Override
    Optional<Chat> findById(Integer integer);

    //Se seleccionan los chats de un usuario
    @Query("select chat from Chat chat where chat.usuarioComprador.codigo = ?1")
    List<Chat> chatUsuario(Integer codigo);

    //Se seleccionan los chats de un producto
    @Query("select chat from Chat chat where chat.productoComprar.codigoProducto = ?1")
    List<Chat> chatProducto(Integer codigo);

}
