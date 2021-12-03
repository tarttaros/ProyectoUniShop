package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private String email,password;

    @Getter @Setter
    Usuario usuarioSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    public String iniciarSesion(){

        System.out.println(email+":"+password);

        if(!email.isEmpty()&&!password.isEmpty()){

            try {
                System.out.println(email+":"+password);
                usuarioSesion = usuarioServicio.iniciarSesion(email, password);
                autenticado = true;
                return "/index?faces-redirect=true";

                    }catch (Exception e){
                FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"alerta",e.getMessage());
                FacesContext.getCurrentInstance().addMessage("msj-bean",fm);
                }
            }
            return null;
        }

        public String cerrarSesion(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "/index?faces-redirect=true";
        }
    }

