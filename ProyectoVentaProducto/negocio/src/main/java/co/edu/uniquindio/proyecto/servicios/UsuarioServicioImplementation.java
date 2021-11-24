package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServicioImplementation implements UsuarioServicio
{

    private final UsuarioRepo usuarioRepo;

    public UsuarioServicioImplementation(UsuarioRepo usuarioRepo)
    {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public Usuario registrarUsuario(Usuario u) throws Exception
    {

        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isPresent())
        {
            throw new Exception("El codigo del usuario ya existe");
        }

        buscado = usuarioRepo.findByEmail(u.getEmail());
        if(buscado.isPresent())
        {
            throw new Exception("El email del usuario ya existe");
        }

        buscado = usuarioRepo.findByNombre(u.getNombre());
        if(buscado.isPresent())
        {
            throw new Exception("El nombre del usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findByEmail(u.getEmail());
        if(buscado.isPresent())
        {
            throw new Exception("El email del usuario ya existe");
        }

        buscado = usuarioRepo.findByNombre(u.getNombre());
        if(buscado.isPresent())
        {
            throw new Exception("El nombre del usuario ya existe");
        }

        return usuarioRepo.save(u);
    }

    @Override
    public void eliminarUsuario(Integer codigo) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty())
        {
            throw new Exception("El usuario no existe");
        }

        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Usuario> listarUsuarios()
    {
        return usuarioRepo.findAll();
    }


    @Override
    public List<Producto> listarProductosFavoritos(String email) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findByEmail(email);
        if(buscado.isEmpty())
        {
            throw new Exception("El usuario no existe");
        }

        return usuarioRepo.obtenerProductosFavoritos(email);
    }
}
