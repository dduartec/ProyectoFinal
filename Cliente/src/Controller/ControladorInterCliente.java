/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Data.Cliente;
import Data.Producto;
import Data.Tienda;
import Model.ServicioCliente;
import View.InterCliente;
import View.JFrameView;
import View.PanelProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorInterCliente extends AbstractController {
    Cliente user;
    public ControladorInterCliente(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda,Cliente user) throws IOException, ClassNotFoundException {
        setModel( new ServicioCliente(cliente,entrada,salida,tienda));
        setView(new InterCliente((ServicioCliente)getModel(), this,cliente,entrada,salida,tienda,user));
        ((JFrameView)getView()).setVisible(true);
        InterCliente vista = (InterCliente) this.getView();
        vista.setActionListener(vista.getAddTarjeta(), new addTarjetaListener(vista));
        vista.setActionListener(vista.getCarrito(), new abrirCarritoListener(vista));
        vista.setActionListener(vista.getBuscar(), new buscarListener(vista));
        vista.setActionListener(vista.getCerrarSesion(), new cerrarSesionListener(vista));
    } 

    public Cliente getUser() {
        return user;
    }

    public void setUser(Cliente user) {
        this.user = user;
    }
    
}
class addTarjetaListener implements ActionListener{
    InterCliente vista;

    public addTarjetaListener(InterCliente vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {                 
              new ControladorAddTarjeta(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda(),vista.getUser());
        } catch (IOException ex) {
            System.out.println("error");
            Logger.getLogger(InterCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InterCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}

class abrirCarritoListener implements ActionListener{
    InterCliente vista;

    public abrirCarritoListener(InterCliente vista) {
        this.vista = vista;
    }


    @Override
    public void actionPerformed(ActionEvent ae) {
        try {
            new ControladorCarritoCliente(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda(),vista.getUser());
        } catch (IOException ex) {
            Logger.getLogger(abrirCarritoListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(abrirCarritoListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch(IllegalArgumentException ex){
            JOptionPane.showMessageDialog(null, "no hay ningun producto en el carrito actualmente");
        }
    }    
}
class buscarListener implements ActionListener{
    InterCliente vista;

    public buscarListener(InterCliente vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String busqueda=vista.getBarraBusqueda().getText();
        ArrayList<Producto> coincidencias=new ArrayList<>();
        int aux=0;
        try {
            coincidencias=((ServicioCliente)vista.getModel()).buscarProducto(busqueda);
        } catch (Exception ex) {
            aux=1;
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        }
        vista.getProductos().removeAll();
        if (aux==0) {
            for (int i = 0; i < coincidencias.size(); i++) {
            vista.getProductos().add(new PanelProducto(coincidencias.get(i),vista.getUser()));
        }        
        vista.getProductos().updateUI();
        vista.getProductos().repaint();
        }
        
    }
    
}
class cerrarSesionListener implements ActionListener{
    InterCliente vista;

    public cerrarSesionListener(InterCliente vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vista.setVisible(false);        
        try {
            new ControladorInicio(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda());
        } catch (IOException ex) {
            Logger.getLogger(cerrarSesionListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cerrarSesionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}