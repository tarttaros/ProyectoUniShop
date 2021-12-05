package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaServicioImpl implements  CategoriaServicio
{

    @Autowired
    private CategoriaRepo categoriaRepo;

    @Override
    public List<Categoria> listarCategorias()
    {
        return  categoriaRepo.findAll();
    }

    @Override
    public Categoria obtenerCategoria(Integer id) throws Exception
    {
        return  categoriaRepo.findById(id).orElseThrow(() -> new Exception("el id no corresponde a ninguna ciudad"));
    }

    @Override
    public Categoria obtenerCategoriaN(String nombre) throws Exception {
        return  categoriaRepo.findByNombre(nombre).orElseThrow(() -> new Exception("el id no corresponde a ninguna ciudad"));

    }
}
