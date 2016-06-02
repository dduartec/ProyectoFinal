/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Data.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Diego
 */
public class ServicioCliente implements Model{
       private  Socket cliente;
       private Tienda tienda;
       private ObjectInputStream entrada;
       private ObjectOutputStream salida;

    public ServicioCliente(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda) throws IOException, ClassNotFoundException {
        this.tienda=tienda;
        this.cliente=cliente;
        this.entrada=entrada;
        this.salida=salida;
    }
    public void crearCliente(String nombre,String nick,String contraseña,int edad,String correo) throws IOException, ClassNotFoundException, Exception{
        int aux=1;
        if(nombre.length()>100){
            throw new Exception("Nombre muy largo.(Max 100 caracteres)");
        }
        for (int i = 0; i < tienda.getUsuarios().size(); i++) {
                if(nick.equals(tienda.getUsuarios().get(i).getNick())){
                    throw new Exception("nick ya registrado");
                }else{
                    if(nick.length()<5||nick.length()>20){
                        throw new Exception("nick demasiado corto o demasiado largo(Min 5, Max 50 caracteres");
                    }
                }
            }
        if(contraseña.length()<6||contraseña.equals("123456")){
                throw new Exception("contraseña demasiado corta o invalida");
            }
                    if(edad<18){
            throw new Exception("Edad minima: 18 años");
        }
                                for (int i = 0; i < tienda.getUsuarios().size(); i++) {
            if(tienda.getUsuarios().get(i).getTipo().equals("cliente")){
                Cliente c=(Cliente) tienda.getUsuarios().get(i);
                if(correo.equals(c.getCorreo())){
                    throw new Exception("correo ya registrado");
                }else{
                    if(!correo.endsWith(".com")||!correo.contains("@")){
                        throw new Exception("Correo con formato inválido(ejemplo@servicio.com");
                    }
                }
            }
        }
        salida.writeInt(1);
        salida.flush();
        Cliente client=new Cliente(correo,edad,nombre,contraseña,nick);
        tienda.getUsuarios().add(client);
        salida.writeObject(client);
    }
    public void crearTarjeta(Cliente user,long numero,String propietario,String fecha,int codigo) throws IOException{
        Tarjeta tarjeta=new Tarjeta(numero,propietario,fecha,codigo);
        for (int i = 0; i <tienda.getUsuarios().size(); i++) {
            if(user.equals(tienda.getUsuarios().get(i))){
                Cliente client=(Cliente)tienda.getUsuarios().get(i);
                client.addTarjeta(tarjeta);
            }
        }
        salida.writeInt(1);
        salida.flush();
        salida.writeObject(user);
        salida.flush();
        salida.writeObject(tarjeta);
        salida.flush();
    }
    public void crearVenta(Cliente user,String fecha,double precio) throws IOException{
        //Venta v=new Venta(user,precio,fecha);
        salida.writeObject(fecha);
        salida.flush();
        salida.writeObject(user);
        salida.flush();
        salida.writeDouble(precio);
        salida.flush();
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
    public ArrayList<Producto> buscarProducto(String busqueda) throws FileNotFoundException, Exception{
        ArrayList<Producto> coincidencias=new ArrayList<>();
        int aux=1;
        for (int i = 0; i < tienda.getPruductos().size(); i++) {
            if(tienda.getPruductos().get(i).getCodigo().equals(busqueda)||
                    tienda.getPruductos().get(i).getNombre().equals(busqueda)||
                    tienda.getPruductos().get(i).getPlataforma().equals(busqueda)||
                    tienda.getPruductos().get(i).getTipo().equals(busqueda)){
               coincidencias.add(tienda.getPruductos().get(i));
               aux=0;
            }
        }
        if(aux==1){
            throw new Exception("no se ha encontrado el producto");
        }else{
        return coincidencias;
        }
    }

    /*public ObjectInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjectInputStream entrada) {
        this.entrada = entrada;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public void setSalida(ObjectOutputStream salida) {
        this.salida = salida;
    }*/
    
}
