package Controller;

import Data.*;
import Model.*;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ControladorInicio extends AbstractController{
    public ControladorInicio(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda) throws IOException, ClassNotFoundException {
        setModel(new ServicioCliente(cliente,entrada,salida,tienda));
        setView(new InicioCliente(getModel(),this,cliente,entrada,salida,tienda));
        ((JFrameView)getView()).setVisible(true);
       InicioCliente vista = (InicioCliente) this.getView();
       vista.setActionListener(vista.getRegistrarse(), new ListenerRegistrarse(vista));
       vista.setActionListener(vista.getIniciarSesion(), new ListenerLogin(vista));
    }

    public void RegistrarseactionPerformed(ActionEvent e) {
        
    }
    
}
 class ListenerRegistrarse implements ActionListener{
    InicioCliente vista;
    public ListenerRegistrarse(InicioCliente vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            
            new ControladorRegistro(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda());
            vista.setVisible(false);
        } catch (IOException | ClassNotFoundException ex) {
            Logger.getLogger(InicioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
 class ListenerLogin implements ActionListener{
     InicioCliente vista;

    public ListenerLogin(InicioCliente vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            vista.setVisible(false);
            new ControladorLogin(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda());
        } catch (IOException ex) {
            Logger.getLogger(InicioCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(InicioCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
 }
