package co.edu.uniquindio.proyecto.bean;

import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import java.io.Serializable;

@Component
@ViewScoped
public class inicioBean implements Serializable
{
    @Getter
    private String mensaje = "mi primera pagina en jsf";
}
