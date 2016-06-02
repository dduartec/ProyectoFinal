package Data;

import java.util.ArrayList;

public class Cliente extends Usuario {
  
    private String correo;
    private int edad;
    private ArrayList<Tarjeta> tarjetas;
    private Carrito carrito;
    

    public Cliente(String correo, int edad, String nombre, String clave, String nick) {
        super(nombre, clave, nick);
        this.correo = correo;
        this.edad = edad;
        tarjetas= new ArrayList<>();
        tipo="cliente";
        this.carrito= new Carrito(this);
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Tarjeta> getTarjetas() {
        return tarjetas;
    }

    public void setTarjetas(ArrayList<Tarjeta> tarjetas) {
        this.tarjetas = tarjetas;
    }
    public void addTarjeta(Tarjeta tarjeta){
        tarjetas.add(tarjeta);
    }

    public Carrito getCarrito() {
        return carrito;
    }

    public void setCarrito(Carrito carrito) {
        this.carrito = carrito;
    }
       
}
