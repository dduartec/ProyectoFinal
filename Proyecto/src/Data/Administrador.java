package Data;

public class Administrador extends Usuario{
    private String claveAdm;
    Tienda tienda;   

    public Administrador( String nombre, String clave, String nick) {
        super(nombre, clave, nick);
        this.tienda=new Tienda("1234");
        this.claveAdm = tienda.getClaveTienda();
        tipo="admin";
    }

    public String getClaveAdm() {
        return claveAdm;
    }

    public void setClaveAdm(String claveAdm) {
        this.claveAdm = claveAdm;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }

    @Override
    public String toString() {
        return super.toString()+"Administrador{" + "claveAdm=" + claveAdm + ", tienda=" + tienda + '}';
    }
    
    
}
