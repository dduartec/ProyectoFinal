package Data;

import java.io.Serializable;

public class Venta implements Serializable{
    
    private Cliente usuario;
    private double precioTotal;
    private  String fecha;

    public Venta(Cliente usuario, double precioTotal, String fecha) {
        this.usuario = usuario;
        this.precioTotal = precioTotal;
        this.fecha = fecha;
    }
    

    public Cliente getUsuario() {
        return usuario;
    }

    public void setUsuario(Cliente usuario) {
        this.usuario = usuario;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    
}
