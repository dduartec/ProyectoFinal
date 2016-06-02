package Data;

import java.io.Serializable;

public abstract class Usuario implements Serializable {
    protected String nombre;
    protected String clave;
    protected String nick;
    protected String tipo;

    public Usuario(String nombre, String clave, String nick) {
        this.nombre = nombre;
        this.clave = clave;
        this.nick = nick;
    }    

    public  String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Usuario{" + "nombre=" + nombre + ", clave=" + clave + ", nick=" + nick + ", tipo=" + tipo + '}';
    }
    
}
