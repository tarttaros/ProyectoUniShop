package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.servicios.AdminServicio;
import co.edu.uniquindio.proyecto.servicios.EmailSenderService;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

@Component
@ViewScoped
public class nuevaContraBean {

    @Getter @Setter
    private String correo;

    @Autowired
    private UsuarioServicio usuarioServicio;
    @Autowired
    private AdminServicio adminServicio;

    @Setter @Getter
    private String codigoNuevo;

    @Getter @Setter
    private String codigoMandado;

    @Autowired
    private EmailSenderService service;

    public void enviarContraseña(){

        System.out.println("aaa"+correo);
        if(usuarioServicio.obtenerUsuarioC(correo)) {
            service.sendSimpleEmail(correo,
                    "Contraseña: " + usuarioServicio.obtenerUsuarioEmail(correo).getPassword(),
                    "La contraseña es: ");
        }else if (adminServicio.obtenerAdministradorC(correo)) {
            service.sendSimpleEmail(correo,
                    "Contraseña: " + adminServicio.obtenerAdministradorEmail(correo).getPassword(),
                    "La contraseña es: ");
        }else{
            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Correo no valido",null);
            FacesContext.getCurrentInstance().addMessage("msj-bean",fm);
        }
    }
}
