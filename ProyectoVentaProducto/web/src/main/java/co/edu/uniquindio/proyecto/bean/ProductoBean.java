package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Ciudad;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.io.IOUtils;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@ViewScoped
public class ProductoBean implements Serializable {

    @Getter @Setter
    private Producto producto;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private Categoria categoria;

    @Getter @Setter
    private List<Categoria> categorias;

    @Getter @Setter
    private ArrayList<String> imagenes;

    @Value("${upload.url}")
    private String urlUploads;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar()
    {
        this.producto=new Producto();
        this.imagenes=new ArrayList<>();
        categorias = categoriaServicio.listarCategorias();
        ciudades = ciudadServicio.listarCiudades();
    }

    public void crearProducto()
    {
        try
        {
            // usuario quemado borrar cuando se llegue a sesiones
            // nose si colocar la fecha en el formulario
            if(usuarioSesion!=null) {
                if (!imagenes.isEmpty()) {

                    producto.setVendedor(usuarioSesion);
                    producto.setFecha(LocalDateTime.now().plusMonths(1));
                    producto.setImagenes(imagenes);
                    productoServicio.publicarProducto(producto);

                    FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Producto creado satisfactoriamente");
                    FacesContext.getCurrentInstance().addMessage(null, facesMsg);

                }
            }
            else
            {
                FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Alerta", "Es necesario subir al menos una imagen");
                FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            }

        }
        catch (Exception e)
        {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            e.printStackTrace();
        }

    }

    public void subirImagenes(FileUploadEvent event)
    {
        UploadedFile imagen = event.getFile();
        String nombreImagen = subirImagen(imagen);
        if (nombreImagen != null)
        {
            imagenes.add(nombreImagen);
        }
    }

    public String subirImagen(UploadedFile imagen)
    {
        try
        {
            File archivo = new File(urlUploads + "/" + imagen.getFileName());
            OutputStream outputStream = new FileOutputStream(archivo);
            IOUtils.copy(imagen.getInputStream(), outputStream);
            return imagen.getFileName();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    public void eliminarProducto() throws Exception {
        productoServicio.eliminarProducto(producto.getCodigoProducto());
    }
}