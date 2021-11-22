package co.edu.uniquindio.proyecto.test;

import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Categoria;
import co.edu.uniquindio.proyecto.repositorios.CategoriaRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CategoriaTest
{
    //variable que representa al repositorio
    @Autowired
    private CategoriaRepo categoriaRepo;

    //metodo que prueba el crear una categoria
    @Test
    public void registrarCategoriaTest()
    {
        //se inicializa la categoria
        Categoria c = new Categoria("Deporte");

        //Guardamos el registro
        Categoria guardado = categoriaRepo.save(c);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el crear una categoria
    @Test
    @Sql("classpath:Categorias.sql")
    public void registrarCategoriaTestSql()
    {
        //se inicializa la categoria
        Categoria c = new Categoria("Hogar");

        //Guardamos el registro
        Categoria guardado = categoriaRepo.save(c);

        //Comprobamos que si haya quedado
        Assertions.assertNotNull(guardado);
    }

    //metodo que prueba el eliminar una categoria
    @Test
    public void eliminarCategoriaTest()
    {
        //se inicializa la categoria
        Categoria c = new Categoria("Deporte");

        //Guardamos el registro
        Categoria guardado = categoriaRepo.save(c);

        //Luego lo eliminamos
        categoriaRepo.delete(guardado);

        //Por último, verificamos que si haya quedado borrado
        Categoria buscado = categoriaRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el eliminar una categoria
    @Test
    @Sql("classpath:Categorias.sql")
    public void eliminarCategoriaTestSql()
    {
        //Buscamos la categoria a eliminar
        Categoria eliminar = categoriaRepo.findById(1).orElse(null);

        //Luego lo eliminamos
        categoriaRepo.delete(eliminar);

        //Por último, verificamos que si haya quedado borrado
        Categoria buscado = categoriaRepo.findById(1).orElse(null);
        Assertions.assertNull(buscado);
    }

    //metodo que prueba el actualizar una categoria
    @Test
    public void actualizarCategoriaTest()
    {
        //se inicializa la categoria
        Categoria c = new Categoria("Deporte");

        //Guardamos el registro
        Categoria guardado = categoriaRepo.save(c);

        //Modificamos el nombre
        guardado.setNombre("tecnologia");

        //Con save guardamos el registro modificado
        categoriaRepo.save(guardado);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("tecnologia", guardado.getNombre());
    }

    //metodo que prueba el actualizar una categoria
    @Test
    @Sql("classpath:Categorias.sql")
    public void actualizarCategoriaTestSql()
    {
        //Buscamos la categoria a actualizar
        Categoria actualizar = categoriaRepo.findById(1).orElse(null);

        //Modificamos el nombre
        actualizar.setNombre("Hogar");

        //Con save guardamos el registro modificado
        categoriaRepo.save(actualizar);

        //Por último, verificamos que si haya quedado actualizado
        Assertions.assertEquals("Hogar", actualizar.getNombre());
    }

    //metodo que lista las categorias almacenadas
    @Test
    public void listarCategoriaTest()
    {
        //se inicializa la categoria
        Categoria c = new Categoria("Deporte");

        //Guardamos el registro
        Categoria guardado = categoriaRepo.save(c);

        //Obtenemos la lista de todas las categorias
        List<Categoria> lista = categoriaRepo.findAll();

        //Imprimimos la lista
        for (Categoria categ : lista)
        {
            System.out.println(categ);
        }
    }

    //metodo que lista las categorias almacenadas
    @Test
    @Sql("classpath:Categorias.sql")
    public void listarCategoriaTestSql()
    {
        //Obtenemos la lista de todas las categorias
        List<Categoria> lista = categoriaRepo.findAll();

        //Imprimimos la lista
        for (Categoria categ : lista)
        {
            System.out.println(categ);
        }
    }

    //solucion 2 del ejercicio de consultas JPQL
    @Test
    @Sql("classpath:Categorias.sql")
    public void punto2Jpql()
    {
        List<Object[]> lista = categoriaRepo.categoriaCalificacion();
        //lista.forEach(System.out::println);

        for(Object[] i  : lista)
        {
            System.out.println("1: "+i[0]+" 2: "+i[1]);
            //System.out.println(i);
        }

    }
}
