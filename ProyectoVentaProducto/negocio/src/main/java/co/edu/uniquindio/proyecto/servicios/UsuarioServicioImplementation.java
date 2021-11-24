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
        System.out.println(u.getCodigo());
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
        buscado = usuarioRepo.findByUserName(u.getUserName());
        if(buscado.isPresent())
        {
            throw new Exception("El email del usuario ya existe");
        }
        return usuarioRepo.save(u);
    }

    @Override
    public Usuario actualizarUsuario(Usuario u) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isEmpty()){
            throw new Exception("usuario no existe");
        }
        return usuarioRepo.save(u);
    }
    private Usuario buscarUsuario(Usuario u) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(u.getCodigo());
        if(buscado.isPresent() ) {
            throw new Exception("EL código del ysuarie ya existe");
        }
        buscado = buscarPorEmail(u.getEmail());

        if(buscado.isPresent() ) {
            throw new Exception("EL email del ysuario ya existe");
        }
        buscado = usuarioRepo.findByUserName(u.getUserName());
        if(buscado.isPresent() ) {
            throw new Exception("EL username del ysuario ya existe");
        }
        return usuarioRepo.save(u);
    }
    private Optional<Usuario> buscarPorEmail (String email){
        return usuarioRepo.findByEmail(email);
    }

    @Override
    public void eliminarUsuario(Integer codigo) throws Exception
    {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        System.out.println(codigo);

        if(buscado.isEmpty())
        {
            throw new Exception("El codigo del usuario no existe");
        }
        usuarioRepo.deleteById(codigo);
    }


    @Override
    public List<Usuario> listarUsuarios()
    {
        return usuarioRepo.findAll();
    }

    @Override
    public List<Producto> listarFavoritos(String email) throws Exception {
        Optional<Usuario> buscado = buscarPorEmail((email));
        if(buscado.isEmpty())
        {
            throw new Exception("El correo no existe");
        }
        return usuarioRepo.obtenerFavoritos(email);
    }

    @Override
    public Usuario obtenerUsuario(int codigo) throws Exception {
        Optional<Usuario> buscado = usuarioRepo.findById(codigo);
        if(buscado.isPresent() ) {
            throw new Exception("EL Usuario NO existe");
        }
        return buscado.get();
    }

    @Override
    public Usuario iniciarSesion(String email, String password) throws Exception {
        Optional<Usuario> usuario= usuarioRepo.findByEmailAndPassword(email,password);

        if(usuario.isEmpty()){
            throw new Exception("Los datos de autenticacion son incorrectos");
        }
        return usuario.get();
    }
}
