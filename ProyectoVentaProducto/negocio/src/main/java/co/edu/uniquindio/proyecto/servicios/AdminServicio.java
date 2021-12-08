package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;

public interface AdminServicio {
    Administrador iniciarSesionAdm(String email, String password) throws Exception;

    boolean obtenerAdministradorC(String correo);

    Administrador obtenerAdministradorEmail(String correo);
}
