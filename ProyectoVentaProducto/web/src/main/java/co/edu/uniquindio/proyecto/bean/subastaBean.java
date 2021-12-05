package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Component
@ViewScoped
public class subastaBean implements Serializable {

    @Autowired
    ProductoServicio productoServicio;

    @Getter @Setter
    private Categoria categoria;

    @Getter @Setter
    private List<Producto> productos;

    @PostConstruct
    public void inicializar () throws Exception
    {
        this.productos = productoServicio.listaProductosSubastados();

    }

    public String irADetalle(String id)
    {
        return "/producto_subasta?faces-redirect=true&amp;producto="+id;
    }
}

