package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Tienda implements Serializable{
    private ArrayList<Usuario> usuarios=new ArrayList<>();
    private ArrayList<Producto> productos=new ArrayList<>();
    private ArrayList<Venta> ventas=new ArrayList<>();
    private String claveTienda;

    public Tienda(String claveTienda) {
        this.claveTienda = claveTienda;
    }
    
    public String getClaveTienda() {
        return claveTienda;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(ArrayList<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public ArrayList<Producto> getPruductos() {
        return productos;
    }

    public void setPruductos(ArrayList<Producto> pruductos) {
        this.productos = pruductos;
    }

    public ArrayList<Venta> getVentas() {
        return ventas;
    }

    public void setVentas(ArrayList<Venta> ventas) {
        this.ventas = ventas;
    }
    

    public void añadirUsuario(Usuario user){
        usuarios.add(user);
    }
    public void añadirProducto(Producto p){
        productos.add(p);
    }

    
    
}
