package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class BusquedaBean implements Serializable {

    @Getter @Setter
    private String busqueda;

    public String buscar(){
        return "resultado_busqueda?faces-redirect=true&amp;busqueda="+busqueda;
    }
}
