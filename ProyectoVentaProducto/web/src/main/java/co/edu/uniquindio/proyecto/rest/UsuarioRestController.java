package co.edu.uniquindio.proyecto.rest;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/usuarios")
public class UsuarioRestController {

    @Autowired
    private UsuarioServicio usuarioServicio;
    @GetMapping
    public List<Usuario> listarUsuarios(){
        return usuarioServicio.listarUsuarios();
    }

    @GetMapping("/{id}")
    public Usuario obtenerUsuario(@PathVariable("id") Integer idUsuario) throws Exception {
        return usuarioServicio.obtenerUsuario(idUsuario);
    }
    @PostMapping
    public Usuario crearUsuario(@RequestBody Usuario usuario) throws Exception {

        return usuarioServicio.registrarUsuario(usuario);
    }
    @DeleteMapping("/{id}")
    public void borrarUsuario(@PathVariable("id") Integer id) throws Exception {

        usuarioServicio.eliminarUsuario(id);

    }
    @PutMapping
    public Usuario actualizarUsuario(@RequestBody Usuario usuario) throws Exception {

        return usuarioServicio.actualizarUsuario(usuario);

    }

}
