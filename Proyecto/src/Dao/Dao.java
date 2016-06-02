package Dao;

import Data.*;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Dao {

    public Dao() {
    }
    public void guardarUsuarios(ArrayList<Usuario> usuarios) throws FileNotFoundException, IOException{
        DataOutputStream dout=new DataOutputStream(new FileOutputStream("usuarios.txt"));
        for (int i = 0; i <usuarios.size(); i++) {
            guardarUsuario(usuarios.get(i),dout);
        }
    }
    public void guardarProductos(ArrayList<Producto> productos) throws FileNotFoundException, IOException{
        DataOutputStream dout=new DataOutputStream(new FileOutputStream("productos.txt"));
        for (int i = 0; i <productos.size(); i++) {
            guardarProducto(productos.get(i),dout);
        }
    }
     
    public void guardarUsuario(Usuario user,DataOutputStream dout) throws FileNotFoundException, IOException{
        String nombre,correo,nick,edad,password,usuario,tipo,nTarjetas=null;        
        nombre=user.getNombre();
        password=user.getClave();
        nick=user.getNick();
        tipo=user.getTipo();
        if(tipo.equals("cliente")){
            Cliente c=(Cliente) user;
            correo=c.getCorreo();
            edad=String.valueOf(c.getEdad());
            nTarjetas=String.valueOf(c.getTarjetas().size());
            String numeroTarjeta,propietario,fecha,codigo,saldo=null;
            String tarjeta="";
            if(c.getTarjetas().size()!=0){
            for (int i = 0; i < c.getTarjetas().size(); i++) {
                numeroTarjeta=String.valueOf(c.getTarjetas().get(i).getNumero());
                propietario=c.getTarjetas().get(i).getPropietario();
                fecha=c.getTarjetas().get(i).getFecha();
                codigo=String.valueOf(c.getTarjetas().get(i).getCodigo());
                saldo=String.valueOf(c.getTarjetas().get(i).getSaldo());
                tarjeta+=numeroTarjeta+","+propietario+","+fecha+","+codigo+","+saldo+",";
            }
            }
            usuario=nombre+","+password+","+nick+","+tipo+","+correo+","+edad+","+nTarjetas+","+tarjeta;
            dout.writeBytes(usuario);
        } else if(tipo.equals("admin")){
            Administrador a=(Administrador)user;
            String claveAdmin=a.getClaveAdm();
            usuario=nombre+","+password+","+nick+","+tipo+","+claveAdmin+",";
            dout.writeBytes(usuario);
        }

    }
    public ArrayList<Usuario> leerUsuario() throws FileNotFoundException{
        ArrayList<Usuario>usuarios=new ArrayList<>();
        Scanner sc=new Scanner(new File("usuarios.txt"));
        sc.useDelimiter(",");
        while(sc.hasNext()){
            String nombre =sc.next();
            String password=sc.next();
            String nick=sc.next();
            String tipo=sc.next();
            if(tipo.equals("cliente")){
                String correo=sc.next();
                int edad=Integer.parseInt(sc.next());
                int nTarjetas=Integer.parseInt(sc.next());
                String propietario,fecha=null;
                long numeroTarjeta;
                int codigo=0;
                double saldo=0;
                ArrayList<Tarjeta> tarjetas=new ArrayList<>();
                if(nTarjetas!=0){
                    for (int i = 0; i < nTarjetas; i++) {
                        numeroTarjeta=Long.parseLong(sc.next());
                        propietario=sc.next();
                        fecha=sc.next();
                        codigo=Integer.parseInt(sc.next());
                        saldo=Double.parseDouble(sc.next());
                        tarjetas.add(new Tarjeta(numeroTarjeta,propietario,fecha,codigo,saldo));
                    }
                }
                Cliente c=new Cliente(correo,edad,nombre,password,nick);
                c.setTarjetas(tarjetas);
                usuarios.add(c);
            }else if(tipo.equals("admin")){
                Administrador a=new Administrador(nombre,password,nick);
                String aux=sc.next();               
                usuarios.add(a);
            }
        }
        return usuarios;
    }
    public void guardarProducto(Producto p,DataOutputStream dout) throws FileNotFoundException, IOException{
        String nombre,codigo,tipo,plataforma,precio,producto,cantidad,dirImage="";
        nombre=p.getNombre();
        codigo=p.getCodigo();
        plataforma=p.getPlataforma();
        cantidad=String.valueOf(p.getCantidad());
        precio=String.valueOf(p.getPrecio());
        dirImage=p.getDirImage();
        tipo=p.getTipo();
        producto=nombre+","+codigo+","+precio+","+dirImage+","+tipo+","+plataforma+","+cantidad+",";
        dout.writeBytes(producto);     
    }
    public ArrayList<Producto> leerProducto() throws FileNotFoundException, IOException{
         ArrayList<Producto>productos=new ArrayList<>();
        Scanner sc=new Scanner(new File("productos.txt"));
        sc.useDelimiter(",");
        while(sc.hasNext()){
            String nombre=sc.next();
            String codigo=sc.next();
            double precio=Double.parseDouble(sc.next());
            String dirImage=sc.next();
            String tipo=sc.next();
            String plataforma=sc.next();
            int cantidad=Integer.parseInt(sc.next());
            Producto p=new Producto(nombre,codigo,precio,tipo,plataforma,cantidad,dirImage);
            productos.add(p);
        }
        return productos;
    }
    public ArrayList<Usuario> cargarUsuarios() throws FileNotFoundException{
        ArrayList<Usuario> usuarios=leerUsuario();
        return usuarios;        
    }
    public ArrayList<Producto> cargarProductos() throws FileNotFoundException, IOException{
        ArrayList<Producto> productos=leerProducto();
        return productos;
    }
    public ArrayList<Venta> cargarVentas() throws FileNotFoundException{
        ArrayList<Venta> ventas=leerVenta();
        return ventas;        
    }

public ArrayList<Venta> leerVenta() throws FileNotFoundException{
    
        ArrayList<Usuario> users = cargarUsuarios();
        ArrayList<Venta>ventas=new ArrayList<>();
        Scanner sc=new Scanner(new File("ventas.txt"));
        sc.useDelimiter(",");
        while(sc.hasNext()){
            Cliente c =null;
            String nickUsuario=sc.next();
            for (int i = 0; i < users.size(); i++) {
                if(users.get(i).getNick().equals(nickUsuario)){
                c = (Cliente) users.get(i);
                }
            }
            double precio=Double.parseDouble(sc.next());
            String fecha=sc.next();
            Venta v = new Venta(c,precio,fecha);
            ventas.add(v);
        }
        return ventas;
    }

 public void guardarVenta(Venta v) throws FileNotFoundException, IOException{
        Scanner sc=new Scanner (new File("ventas.txt"));
        String anterior="";
        sc.useDelimiter(",");
        while(sc.hasNext()){
            anterior+=sc.next().trim()+",";
        }
        DataOutputStream dout=new DataOutputStream(new FileOutputStream("ventas.txt"));
        String nickUsuario,precio,fecha,venta="";
        nickUsuario=v.getUsuario().getNick();
        precio=String.valueOf(v.getPrecioTotal());
        fecha=v.getFecha();
        venta=anterior+nickUsuario+","+precio+","+fecha+",";
        dout.writeBytes(venta);     
    }
}
