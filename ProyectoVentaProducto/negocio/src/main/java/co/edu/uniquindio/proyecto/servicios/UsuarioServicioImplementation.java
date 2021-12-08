package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Compra;
import co.edu.uniquindio.proyecto.entidades.DetalleCompra;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.repositorios.UsuarioRepo;
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
    public Usuario añadirFavorito(Producto p, Usuario u) throws Exception
    {
        Optional<Producto> buscado = usuarioRepo.obtenerUnProductoFavorito(u, p);
        if(buscado.isPresent())
        {
            throw new Exception("El producto ya esta en favoritos");
        }
        else
        {
            List<Producto> producto = u.getProductosFavoritos();
            producto.add(p);
            u.setProductosFavoritos(producto);
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Usuario eliminarFavorito(Producto p, Usuario u) throws Exception
    {
        Optional<Producto> buscado = usuarioRepo.obtenerUnProductoFavorito(u, p);
        if(buscado.isEmpty())
        {
            throw new Exception("El producto no esta en favoritos");
        }
        else
        {
            List<Producto> producto = u.getProductosFavoritos();
            producto.remove(p);
            u.setProductosFavoritos(producto);
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception
    {
        /*
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
         */
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isEmpty())
        {
            throw new Exception("El usuario no existe");
        }

        return usuarioRepo.save(u);
    }

    private Optional<Usuario> buscarPorEmail (String email) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findByEmail(email);
        if(buscado.isPresent())
        {
            throw new Exception("El email del usuario ya existe");
        }
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(Integer codigo) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty())
        {
            throw new Exception("El codigo del usuario no existe");
        }
        usuarioRepo.deleteById(codigo);
    }

    @Override
    public List<Producto> listarSubastasRealizadas(Usuario u)
    {
        return usuarioRepo.listarSubastasRealizadas(u);
    }

    @Override
    public boolean obtenerUsuarioC(String correo) {
        System.out.println("aaaaanoooooo: "+correo);
        if(usuarioRepo.findByEmail(correo)==null){
            return false;
        }
        return true;
    }

    @Override
    public Usuario obtenerUsuarioEmail(String correo) {
        Optional<Usuario> u=usuarioRepo.findByEmail(correo);
        return u.get();
    }

    @Override
    public List<Usuario> listarUsuarios()
    {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Producto> listarFavoritos(String email)
    {
        return usuarioRepo.obtenerProductosFavoritos(email);
    }

    @Override
    public List<DetalleCompra> listarComprados(Usuario usuario)
    {
        return usuarioRepo.productoComprado(usuario);
    }

    @Override
    public List<Producto> listarProductosUsuario(Usuario usuario)
    {
        return usuarioRepo.productosUsuario(usuario);
    }

    @Override
    public Usuario obtenerUsuario(int codigo) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isEmpty())
        {
            throw new Exception("EL Usuario no existe");
        }
        return buscado.get();
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception
    {
        Optional<Usuario> usuario = Optional.ofNullable(usuarioRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos")));
        return usuario.get();
    }


}
