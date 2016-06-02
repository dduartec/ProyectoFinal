package Model;

import Dao.Dao;
import Data.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class ServicioAdmin implements Model {
    private Tienda tienda;
    private Dao dao;
    

    public ServicioAdmin() throws IOException {
        tienda=new Tienda("1234");
        dao=new Dao();
         
    }
    
    public void crearAdmin(String nombre, String clave, String nick,String claveTienda) throws Exception{
        tienda.setUsuarios(dao.cargarUsuarios());
            Usuario user=new Administrador(nombre,nick,clave);
            tienda.añadirUsuario(user);
            dao.guardarUsuarios(tienda.getUsuarios());
    }
    public void crearProducto(String nombre, String codigo, double precio, String tipo, String plataforma, int cantidad,String esrb,String genero,String dirImage) throws Exception{
        tienda.setPruductos(dao.cargarProductos());
        Producto p=new Producto(nombre,codigo,precio,tipo,plataforma,cantidad,dirImage);
        tienda.añadirProducto(p);
        dao.guardarProductos(tienda.getPruductos());        
    }
    public void modificarCantidad(Producto p,int nuevaCantidad) throws FileNotFoundException{
        p.setCantidad(nuevaCantidad);
    }
    public Producto buscarProducto(String codigo) throws FileNotFoundException, Exception{
        tienda.setPruductos(dao.cargarProductos());
        Producto p = null;
        int aux=0;
        for (int i = 0; i < tienda.getPruductos().size(); i++) {
            if(tienda.getPruductos().get(i).getCodigo().equals(codigo)){
                p=tienda.getPruductos().get(i);
                aux=1;
            }
        }
        if(aux!=1){
            try{
                throw new Exception("no se ha encontrado el producto");
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
        return p;
    }
    public Cliente buscarCliente(String aux) throws FileNotFoundException, Exception{
        ArrayList<Usuario> usuarios=dao.cargarUsuarios();
        Cliente client=null;
        int aux1=0;
        for (int i = 0; i < usuarios.size(); i++) {
            if(aux.equalsIgnoreCase(usuarios.get(i).getNick())){
                if (usuarios.get(i).getTipo().equals("admin")) {
                    JOptionPane.showMessageDialog(null,"El usuario es de tipo admin,no posee acceso");
                }else{
                 client=(Cliente) usuarios.get(i);
                 aux1=1;
                }
            }
        }
        if (aux1!=1) {
                throw new Exception("no se ha encontrado el cliente");
        }
        return client;
    }
    public void modificarCantidad(String codigo,int nuevaCant) throws FileNotFoundException, Exception{
        ArrayList<Producto> productos=dao.cargarProductos();
        Producto p=buscarProducto(codigo);
        for (int i = 0; i < productos.size();i++) {
            if(p.getCodigo().equals(productos.get(i).getCodigo())){
                productos.get(i).setCantidad(nuevaCant);
            }
        }
        dao.guardarProductos(productos);
        
        
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    public Dao getDao() {
        return dao;
    }

    public void setDao(Dao dao) {
        this.dao = dao;
    }

    
    
}
