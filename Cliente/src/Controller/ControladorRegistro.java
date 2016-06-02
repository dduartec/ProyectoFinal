package Controller;

import Data.*;
import Model.*;
import View.JFrameView;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorRegistro extends AbstractController{
    

    public ControladorRegistro(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda) throws IOException, ClassNotFoundException {
        setModel( new ServicioCliente(cliente,entrada,salida,tienda));
        setView(new RegistroCliente((ServicioCliente)getModel(), this,cliente,entrada,salida,tienda));
        ((JFrameView)getView()).setVisible(true);
        RegistroCliente vista = (RegistroCliente) this.getView();
        vista.setActionListener(vista.getEnviar(), new enviarRegListener(vista));
        vista.setActionListener(vista.getCancelar(), new cancelarRegLister(vista));
    }   
    
}
class enviarRegListener implements ActionListener{
    RegistroCliente vista;

    public enviarRegListener(RegistroCliente vista) {
        this.vista = vista;
    }    

    @Override
    public void actionPerformed(ActionEvent e) {           
        try {
            int codigo=0;  
            vista.getSalida().writeInt(codigo);
            vista.getSalida().flush(); 
            ServicioCliente servi=(ServicioCliente)vista.getModel(); 
            String nombre=vista.getNombre().getText();
            String nick=vista.getNick().getText();
            String contraseña=vista.getContraseña().getText();
            int edad=0;
            edad=Integer.parseInt(vista.getEdad().getText());
            String correo=vista.getCorreo().getText();
            servi.crearCliente(nombre,nick,contraseña,edad,correo);
            JOptionPane.showMessageDialog(null,"usuario registrado exitosamente");
            vista.setVisible(false);
            new ControladorInicio(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
            try {
                vista.getSalida().writeInt(-1);
                 vista.getSalida().flush();
            } catch (IOException ex1) {
                Logger.getLogger(enviarRegListener.class.getName()).log(Level.SEVERE, null, ex1);
            }
               
            } 
        } 
    }    
class cancelarRegLister implements ActionListener{
    RegistroCliente vista;

    public cancelarRegLister(RegistroCliente vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vista.setVisible(false);
        try {
            new ControladorInicio(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda());
        } catch (IOException ex) {
            Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}