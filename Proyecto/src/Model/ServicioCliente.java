package Model;

import Dao.Dao;
import Data.Tienda;
import Data.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ServicioCliente implements Model{
    private Tienda tienda;
    private Dao dao;
    Socket cliente;

    public ServicioCliente(Socket cliente) throws IOException {
        tienda=new Tienda("1234");
        dao=new Dao();
        this.cliente=cliente;
    }
    public void enviarDatos(ObjectInputStream entrada,ObjectOutputStream salida) throws FileNotFoundException, IOException, Exception{
        tienda.setUsuarios(dao.cargarUsuarios());
        tienda.setPruductos(dao.cargarProductos());   
        Tienda tienda=this.tienda;
        salida.writeObject(tienda);
        salida.flush();
        for (int i = 0; i <tienda.getPruductos().size() ; i++) {            
             BufferedImage imagen = ImageIO.read(new File(tienda.getPruductos().get(i).getDirImage()));
             ImageIcon img=new ImageIcon(imagen);
            salida.writeObject(img);
            salida.flush();
        }
        seleccion(entrada,salida);
    }
    public void seleccion(ObjectInputStream entrada,ObjectOutputStream salida) throws FileNotFoundException, IOException, ClassNotFoundException, Exception{
        System.out.println("x");
        int codigo=-1;
        int aux=0;
        codigo=entrada.readInt();
        System.out.println(codigo);
        switch(codigo){
            case 0:
                aux=entrada.readInt();
                if(aux==1){
                    crearCliente(entrada);
                    break;
                }else{
                    seleccion(entrada,salida);
                }
            case 1:
                aux=entrada.readInt();
                if(aux==1){
                    agregarTarjeta(entrada);
                    break;
                }else{
                    seleccion(entrada,salida);
                }
                
            case 2:
                
                crearVenta(entrada);
                break;
        }
    }
    public void crearCliente(ObjectInputStream entrada) throws IOException,Exception{
          Cliente user=(Cliente) entrada.readObject();
       tienda.añadirUsuario(user);
       dao.guardarUsuarios(tienda.getUsuarios());           
    }
    public void agregarTarjeta(ObjectInputStream entrada) throws FileNotFoundException, IOException, ClassNotFoundException{
        Cliente user=(Cliente) entrada.readObject();
        Tarjeta tarjeta=(Tarjeta)entrada.readObject();
        for (int i = 0; i <tienda.getUsuarios().size(); i++) {
            if(user.getNick().equals(tienda.getUsuarios().get(i).getNick())){                
                ((Cliente)tienda.getUsuarios().get(i)).addTarjeta(tarjeta);
            }
        }
        dao.guardarUsuarios(tienda.getUsuarios());
        
    }
    public void crearVenta(ObjectInputStream entrada) throws IOException, ClassNotFoundException{
      String fecha=(String)entrada.readObject();
        Cliente user=(Cliente)entrada.readObject();
        double precio=entrada.readDouble();
        Venta venta=new Venta(user,precio,fecha);
        for (int i = 0; i <tienda.getUsuarios().size(); i++) {
            if(user.getNick().equals(tienda.getUsuarios().get(i).getNick())){
               ((Cliente) tienda.getUsuarios().get(i)).setTarjetas(user.getTarjetas());
            }
        }
        dao.guardarUsuarios(tienda.getUsuarios());
        dao.guardarVenta(venta);
    }
    
    public Producto buscarProducto(String codigo) throws FileNotFoundException, Exception{
        tienda.setPruductos(dao.cargarProductos());
        Producto p = null;
        for (int i = 0; i < tienda.getPruductos().size(); i++) {
            if(tienda.getPruductos().get(i).getCodigo().equals(codigo)){
                p=tienda.getPruductos().get(i);
            }else{
                try{
                    throw new Exception("no se ha encontrado el producto");
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
            }
        }
        return p;
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

