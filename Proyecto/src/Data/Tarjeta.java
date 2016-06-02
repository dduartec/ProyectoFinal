package Data;

import java.io.Serializable;

public class Tarjeta implements Serializable{
    private long numero;
    private String propietario;
    private String fecha;
    private int codigo;
    private double saldo;

    public Tarjeta(long numero, String propietario, String fecha,int codigo) {
        this.numero = numero;
        this.propietario = propietario;
        this.fecha = fecha;
        this.codigo = codigo;
        this.saldo =1000000;
    }

    public Tarjeta(long numero, String propietario, String fecha, int codigo, double saldo) {
        this.numero = numero;
        this.propietario = propietario;
        this.fecha = fecha;
        this.codigo = codigo;
        this.saldo = saldo;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getPropietario() {
        return propietario;
    }

    public void setPropietario(String propietario) {
        this.propietario = propietario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public String toString() {
        return "Tarjeta{" + "numero=" + numero + ", propietario=" + propietario + ", fecha=" + fecha + ", codigo=" + codigo + ", saldo=" + saldo + '}';
    }
    

    
}
