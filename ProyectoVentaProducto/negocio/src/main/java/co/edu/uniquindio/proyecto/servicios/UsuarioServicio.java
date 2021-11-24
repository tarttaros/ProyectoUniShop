package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;

public interface UsuarioServicio
{

    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    Usuario obtenerUsuario(Integer codigo) throws Exception;

    void eliminarUsuario(Integer codigo) throws Exception;

    List<Usuario> listarUsuarios();

    List<Producto> listarProductosFavoritos(String email) throws Exception;

    public Usuario iniciarSesion(String email, String password) throws Exception;
}
