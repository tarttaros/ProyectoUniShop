package co.edu.uniquindio.proyecto.rest;


import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/{codigo}")
    public ResponseEntity<?> obtenerUsuario(@PathVariable(name = "codigo") Integer id){
        try {
            return ResponseEntity.status(200).body(usuarioServicio.obtenerUsuario(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }
    @PostMapping
    public ResponseEntity<Mensaje> crearUsuario(@RequestBody Usuario usuario){
        try {
            usuarioServicio.registrarUsuario(usuario);
            return ResponseEntity.status(201).body(new Mensaje("Usuario Creado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }

    @PutMapping
    public ResponseEntity<Mensaje> actualizarUsuario(@RequestBody Usuario usuario) throws Exception {
        try {
            usuarioServicio.actualizarUsuario(usuario);
            return ResponseEntity.status(200).body(new Mensaje("El usuario se actualizó correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @DeleteMapping("/{codigo}")
    public ResponseEntity<Mensaje> borrarUsuario(@PathVariable("codigo") Integer id) throws Exception {
        try {
            usuarioServicio.eliminarUsuario(id);
            return ResponseEntity.status(200).body(new Mensaje("El usuario se eliminó correctamente"));
        }catch (Exception e){
           return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
       }

    }

    @GetMapping("/favoritos/{email}")
    public ResponseEntity<?> obtenerFavoritos(@PathVariable("email") String email){
        try {
        List<Producto> lista=    usuarioServicio.listarFavoritos(email);
            return ResponseEntity.status(200).body(lista);
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


}
