package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.servicios.CategoriaServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import java.io.Serializable;
import java.util.List;


@Component
@ViewScoped
public class BusquedaBeanC implements Serializable {

    @Getter
    @Setter
    private Categoria busqueda;

    @Getter @Setter
    @Value("#{param['busqueda']}")
    private Categoria busquedaParam;

    @Getter @Setter
    private List<Producto> productos;

    @Autowired
    private ProductoServicio productoServicio;

    @Autowired
    private CategoriaServicio categoriaServicio;

    @PostConstruct
    public void inicializar() throws Exception {


        if(busquedaParam!=null) {
            try {
                System.out.println("noooo" + busquedaParam);
                busquedaParam = categoriaServicio.obtenerCategoriaN(busquedaParam.getNombre());
            }catch (Exception e){

            }
            if (busquedaParam != null && busquedaParam.getCodigo() != null) {

                System.out.println(busquedaParam);
                productos = productoServicio.buscarCategorias(busquedaParam);
            }
        }
    }

    public String buscar()
    {
        System.out.println(" nooooooo"+busqueda.getNombre());
        return "resultado_busquedaC?faces-redirect=true&amp;busqueda="+busqueda.getNombre();
    }
}

