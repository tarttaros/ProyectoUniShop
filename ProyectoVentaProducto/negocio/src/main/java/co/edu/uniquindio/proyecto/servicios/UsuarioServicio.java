package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;

import java.util.List;


public interface UsuarioServicio
{
    Usuario registrarUsuario(Usuario u) throws Exception;

    Usuario añadirFavorito(Producto p, Usuario u) throws Exception;

    Usuario eliminarFavorito(Producto p, Usuario u) throws Exception;

    Usuario actualizarUsuario(Usuario u) throws Exception;

    Usuario obtenerUsuario(int codigo) throws Exception;

    Usuario iniciarSesion(String email, String password) throws Exception;

    List<Usuario> listarUsuarios();

    List<Producto> listarFavoritos(String email);

    List<DetalleCompra> listarComprados(Usuario usuario);

    List<Producto> listarProductosUsuario(Usuario usuario);

    void eliminarUsuario(Integer codigo) throws Exception;

    List<Producto> listarSubastasRealizadas(Usuario usuario);

    boolean obtenerUsuarioC(String correo);

    Usuario obtenerUsuarioEmail(String correo);
}

