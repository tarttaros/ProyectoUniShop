package co.edu.uniquindio.proyecto.bean;

import co.edu.uniquindio.proyecto.dto.ProductoCarrito;
import co.edu.uniquindio.proyecto.entidades.Administrador;
import co.edu.uniquindio.proyecto.entidades.Producto;
import co.edu.uniquindio.proyecto.entidades.Usuario;
import co.edu.uniquindio.proyecto.servicios.AdminServicio;
import co.edu.uniquindio.proyecto.servicios.ProductoServicio;
import co.edu.uniquindio.proyecto.servicios.UsuarioServicio;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Component
@Scope("session")
public class SeguridadBean implements Serializable {

    @Getter @Setter
    private boolean autenticado;

    @Getter @Setter
    private boolean autenticadoAdm;

    @Getter @Setter
    private String email,password;

    @Getter @Setter
    Usuario usuarioSesion;

    @Getter @Setter
    Administrador administradorSesion;

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private AdminServicio adminServicio;

    @Autowired
    private ProductoServicio productoServicio;

    @Getter @Setter
    private ArrayList<ProductoCarrito> productosCarrito;
    @Getter @Setter
    private double subtotal;

    @PostConstruct
    public  void inicializar()
    {
        this.subtotal=0F;
        this.productosCarrito=new ArrayList<>();

    }


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
    public String iniciarSesionAdm(){

        System.out.println(email+":"+password);

        if(!email.isEmpty()&&!password.isEmpty()){

            try {
                System.out.println(email+":"+password);
                administradorSesion = adminServicio.iniciarSesionAdm(email, password);
                autenticadoAdm = true;
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

    public void agregarAlCarrito(Integer id,Double precio,String nombre,String imagen){

        ProductoCarrito pc=new ProductoCarrito(id,nombre,imagen,precio,1);
        if(!productosCarrito.contains(pc)){
            productosCarrito.add(pc);
            subtotal+=precio;

            FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"alerta","Producto agregado al carrito");
            FacesContext.getCurrentInstance().addMessage("add-cart",fm);

        }

    }

    public void eliminarDelCarrito(int indice){
        subtotal-=productosCarrito.get(indice).getPrecio()*productosCarrito.get(indice).getUnidades();
        productosCarrito.remove(indice);

    }
    public void actualizarSubtotal(){
        subtotal=0;
        for(ProductoCarrito p:productosCarrito){

            subtotal+=p.getPrecio()*p.getUnidades();
        }
    }

    public void comprar() throws Exception
    {
        if(usuarioSesion!=null&&!productosCarrito.isEmpty())
        {

            boolean validarUnidades=true;

            for (ProductoCarrito p : productosCarrito)
            {

                if(productoServicio.obtenerProducto(p.getId()).getCantidad()< p.getUnidades()){
                    validarUnidades=false;
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"alerta","Las unidades del producto "+p.getNombre()+ " no estan disponibles");
                    FacesContext.getCurrentInstance().addMessage("compra-msj",fm);
                }
            }

            if(validarUnidades==true){

                try{
                    productoServicio.comprarProductos(usuarioSesion,productosCarrito,"SFE");
                    productosCarrito.clear();
                    subtotal=0;
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_INFO,"alerta","Compra realizada satisfactoriamente");
                    FacesContext.getCurrentInstance().addMessage("compra-msj",fm);
                }
                catch (Exception e){
                    FacesMessage fm = new FacesMessage(FacesMessage.SEVERITY_ERROR,"alerta",e.getMessage());
                    FacesContext.getCurrentInstance().addMessage("compra-msj",fm);
                }
            }
        }


    }


}

