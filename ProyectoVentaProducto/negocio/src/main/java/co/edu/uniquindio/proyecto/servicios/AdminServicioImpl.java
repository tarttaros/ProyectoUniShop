package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.repositorios.AdministradorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServicioImpl implements AdminServicio{

    @Autowired
    private final AdministradorRepo administradorRepo;

    public AdminServicioImpl(AdministradorRepo administradorRepo) {
        this.administradorRepo = administradorRepo;
    }

    @Override
    public Administrador iniciarSesionAdm(String email, String password) throws Exception {
        Optional<Administrador> administrador = Optional.ofNullable(administradorRepo.findByEmailAndPassword(email, password).orElseThrow(() -> new Exception("Los datos de autenticacion son incorrectos")));
        return administrador.get();
    }
}
