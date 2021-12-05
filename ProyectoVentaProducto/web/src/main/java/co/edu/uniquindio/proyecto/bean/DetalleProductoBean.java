package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Comentario;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
@ViewScoped
public class DetalleProductoBean
{

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Getter @Setter
    private Double precioNuevo;

    @Value("#{param['producto']}")
    private String codigoProducto;

    @Value("#{seguridadBean.usuarioSesion}")
    private Usuario usuarioSesion;

    @Getter
    @Setter
    private String codigo;

    @Getter
    @Setter
    private Producto producto;

    @Getter
    @Setter
    private Comentario nuevoComentario;

    @Getter
    @Setter
    private List<Comentario> comentarios;

    @Getter
    @Setter
    private Integer calificacion;


    @PostConstruct
    public void inicializar() throws Exception
    {
        precioNuevo=0.0;
        nuevoComentario=new Comentario();
        if(codigoProducto!=null && !codigoProducto.isEmpty())
        {
            Integer codigo = Integer.parseInt(codigoProducto);
            producto = productoServicio.obtenerProducto(codigo);
            this.comentarios = producto.getComentarios();
            Integer califi = 0;
            Comentario comentar = new Comentario();
            if(producto.getComentarios().size() > 0)
            {
                for (int i = 0; i < producto.getComentarios().size(); i++)
                {
                    comentar = producto.getComentarios().get(i);
                    califi += comentar.getCalificacion();
                }
                this.calificacion = califi / producto.getComentarios().size();
            }
        }
    }

    public void crearComentario()
    {
        try
        {
            nuevoComentario.setProducto(producto);
            nuevoComentario.setUsuario(usuarioSesion);
            productoServicio.comentarProducto(nuevoComentario);
            this.comentarios.add(nuevoComentario);
            this.calificacion = (calificacion+nuevoComentario.getCalificacion()) / comentarios.size();
            this.nuevoComentario = new Comentario();

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public void añadirFavorito()
    {
        try
        {
            if(usuarioSesion != null)
            {
                Usuario user = usuarioServicio.obtenerUsuario(usuarioSesion.getCodigo());
                usuarioServicio.añadirFavorito(producto, user);
            }
        }
        catch (Exception e)
        {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            e.printStackTrace();
        }
    }

    public void eliminarFavorito()
    {
        try
        {
            if(usuarioSesion != null)
            {
                producto = productoServicio.obtenerProducto(Integer.parseInt(codigo));
                Usuario user = usuarioServicio.obtenerUsuario(usuarioSesion.getCodigo());
                usuarioServicio.eliminarFavorito(producto, user);
            }
        }
        catch (Exception e)
        {
            FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Alerta", e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, facesMsg);
            e.printStackTrace();
        }
    }

    public void subastarPuja() throws Exception {


        System.out.println(precioNuevo);

        productoServicio.pujarSubasta(producto, precioNuevo);
    }
}
