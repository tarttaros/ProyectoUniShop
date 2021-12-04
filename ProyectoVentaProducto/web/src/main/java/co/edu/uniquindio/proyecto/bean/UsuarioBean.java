package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.*;
import co.edu.uniquindio.proyecto.servicios.CiudadServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import jdk.jfr.Name;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.service.spi.InjectService;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.NamedEvent;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;

@Component
@ViewScoped
@Name("dtEditView")
public class UsuarioBean implements Serializable
{

    @Getter
    @Setter
    private Usuario usuario;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private CiudadServicio ciudadServicio;

    @Getter @Setter
    private Ciudad ciudad;

    @Getter @Setter
    private List<Ciudad> ciudades;

    @Getter @Setter
    private List<Producto> productosFavoritos;

    @Getter @Setter
    private List<Producto> productosUsuario;

    @Getter @Setter
    private List<Usuario> usuariosA;

    @Getter @Setter
    private List<DetalleCompra> productosComprados;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @PostConstruct
    public void inicializar()
    {
        usuario=new Usuario();
        ciudades = ciudadServicio.listarCiudades();
        try
        {
            this.usuariosA = usuarioServicio.listarUsuarios();
            this.productosFavoritos = usuarioServicio.listarFavoritos(usuarioSesion.getEmail());
            this.productosComprados = usuarioServicio.listarComprados(usuarioSesion);
            this.productosUsuario = usuarioServicio.listarProductosUsuario(usuarioSesion);

        }
        catch (Exception e)
        {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            e.printStackTrace();
        }

    }

    public void registrarUsuario()
    {
        try
        {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Alerta", "Registro exitoso");
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            usuarioServicio.registrarUsuario(usuario);
        }
        catch (Exception e)
        {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            e.printStackTrace();
        }
    }



    public void onRowEdit(RowEditEvent<Usuario> event) throws Exception {
        FacesMessage msg = new FacesMessage("Product Edited", String.valueOf(event.getObject().getCodigo()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
        Usuario u = usuarioServicio.obtenerUsuario(event.getObject().getCodigo());
        Ciudad c= ciudadServicio.obtenerCiudad(event.getObject().getCodigo());
        u.setNombre(event.getObject().getNombre());
        u.setEmail(event.getObject().getEmail());
        u.setCiudad(event.getObject().getCiudad());
        usuarioServicio.actualizarUsuario(u);
    }

    public void onRowCancel(RowEditEvent<Usuario> event) {
        FacesMessage msg = new FacesMessage("Edit Cancelled", String.valueOf(event.getObject().getCodigo()));
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
