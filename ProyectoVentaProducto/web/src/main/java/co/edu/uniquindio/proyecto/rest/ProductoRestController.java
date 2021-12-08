package co.edu.uniquindio.proyecto.rest;


import co.edu.uniquindio.proyecto.dto.Mensaje;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicioImpl;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("api/productos")
public class ProductoRestController {
    @Autowired
    private ProductoServicio productoServicio;
    @Autowired
    private CategoriaServicio categoriaServicio;

    @GetMapping
    public List<Producto> listarProductos() throws Exception {
        return productoServicio.listarTodosProducto();
    }

    @GetMapping("/{codigoProducto}")
    public ResponseEntity<?> obtenerProducto(@PathVariable(name = "codigoProducto") Integer id){
        try {
            return ResponseEntity.status(200).body(productoServicio.obtenerProducto(id));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }


    @GetMapping("/categoria/{nom}")
    public List<Producto> listarCategorias(@PathVariable(name = "nom") String cat)throws Exception {
        return productoServicio.listaProductos(categoriaServicio.obtenerCategoriaN(cat));
    }

    @GetMapping("/rango/{rango1}/{rango2}")
    public List<Producto> listarRangoPrecio(@PathVariable(name = "rango1") Integer precio1, @PathVariable(name = "rango2") Integer precio2)throws Exception {

        System.out.println(precio1+"");
        return productoServicio.listaProductosPrecio(precio1*1.0,precio2*1.0);
    }
    @GetMapping("/ciudad/{nom}")
    public List<Producto> listarCiudad(@PathVariable(name = "nom") String ciu)throws Exception {
        return productoServicio.listaProductosCiudad(ciu);
    }

    @PostMapping
    public ResponseEntity<Mensaje> crearProducto(@RequestBody Producto producto ){
        try {
            productoServicio.publicarProducto(producto);
            return ResponseEntity.status(201).body(new Mensaje("Producto Creado correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }
    }



    @PutMapping
    public ResponseEntity<Mensaje> actualizarProducto(@RequestBody Producto producto) throws Exception {
        try {
            productoServicio.actualizarProducto(producto);
            return ResponseEntity.status(200).body(new Mensaje("El producto se actualizó correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }

    @DeleteMapping("/{codigoProducto}")
    public ResponseEntity<Mensaje> borrarProducto(@PathVariable("codigoProducto") Integer id) throws Exception {
        try {
            productoServicio.eliminarProducto(id);
            return ResponseEntity.status(200).body(new Mensaje("El producto se eliminó correctamente"));
        }catch (Exception e){
            return ResponseEntity.status(500).body(new Mensaje(e.getMessage()));
        }

    }


}
