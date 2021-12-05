package co.edu.uniquindio.proyecto.servicios;

import co.edu.uniquindio.proyecto.entidades.Categoria;

import java.util.List;

public interface CategoriaServicio
{

    List<Categoria> listarCategorias();

    Categoria obtenerCategoria(Integer id) throws Exception;

    Categoria obtenerCategoriaN(String nombre) throws Exception;
}
