package Data;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrito implements Serializable{
    private ArrayList<Producto> lista;
    private double precioTotal;
    
    private Cliente cliente;

    public Carrito(Cliente cliente) {
       lista=new ArrayList<>();    
    }
    public void addProducto(Producto producto) throws Exception{
        lista.add(producto);
        if(lista.size()>20){
            throw new Exception("solo se pueden añadir 20 productos al carrito");
    }
    }
    public void quitarProducto(Producto producto){
        
        for (int i = 0; i < lista.size(); i++) {    
            if(lista.get(i).equals(producto)){        
            lista.remove(i);         
            }        
        }   

    }
    public void calcularPrecio(){
        double precio=0;
        for (Producto lista1 : lista) {
            precio += lista1.getPrecio();
        }
        precioTotal=precio;
    }

    public ArrayList<Producto> getLista() {
        return lista;
    }

    public void setLista(ArrayList<Producto> lista) {
        this.lista = lista;
    }

 
    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    
    
}
