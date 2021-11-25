package co.edu.uniquindio.proyecto.repositorios;

import co.edu.uniquindio.proyecto.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepo extends JpaRepository<Categoria, Integer>
{
    @Query("select categoria, avg(comentario.calificacion) from Categoria categoria, in (categoria.listaProductos) producto, in (producto.comentarios) comentario")
    List<Object[]> categoriaCalificacion();

}
