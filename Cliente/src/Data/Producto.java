package Data;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import javax.swing.ImageIcon;

public  class Producto implements Serializable {
    protected String nombre;
    protected String codigo;
    protected double precio;
    protected String tipo;
    protected String plataforma;
    protected int cantidad;
    protected String dirImage;
    private BufferedImage imagen;

    public Producto(String nombre, String codigo, double precio, String tipo, String plataforma, int cantidad, String dirImagen) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.precio = precio;
        this.tipo = tipo;
        this.plataforma = plataforma;
        this.cantidad = cantidad;
        this.dirImage=dirImagen;
    }
    


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }


    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDirImage() {
        return dirImage;
    }

    public void setDirImage(String dirImage) {
        this.dirImage = dirImage;
    }

    public BufferedImage getImagen() {
        return imagen;
    }

    public void setImagen(BufferedImage imagen) {
        this.imagen = imagen;
    }
    
    
    
}
