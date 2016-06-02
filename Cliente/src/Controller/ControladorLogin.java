package Controller;

import Data.*;
import Model.ServicioCliente;
import View.JFrameView;
import View.LogIn;
import View.RegistroCliente;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorLogin extends AbstractController {
    public ControladorLogin(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda) throws IOException, ClassNotFoundException {
        setModel(new ServicioCliente(cliente,entrada,salida,tienda));
        setView(new LogIn(getModel(),this,cliente,entrada,salida,tienda));
        ((JFrameView)getView()).setVisible(true); 
        LogIn vista = (LogIn) this.getView();
        vista.setActionListener(vista.getIngresar(), new ingresarListener(vista));
        vista.setActionListener(vista.getCancelar(), new cancelarListener(vista));
    }   
}
class ingresarListener implements ActionListener{
    LogIn vista;

    public ingresarListener(LogIn vista) {
        this.vista = vista;
    }    

    @Override
    public void actionPerformed(ActionEvent e) {
        String nick=vista.getUsuario().getText();
        String password=vista.getContraseña().getText();
        String tipo="cliente";
        Cliente user=null;
        ServicioCliente servi=(ServicioCliente)vista.getModel();
        int aux=0;
            for(int i=0;i<servi.getTienda().getUsuarios().size();i++){
                if(password.equals(servi.getTienda().getUsuarios().get(i).getClave())&&nick.equals(servi.getTienda().getUsuarios().get(i).getNick())&&tipo.equals(servi.getTienda().getUsuarios().get(i).getTipo())){
                    aux+=1;
                    user=(Cliente) servi.getTienda().getUsuarios().get(i);  
                }
        }
            if(aux==1){
                JOptionPane.showMessageDialog(null,"ingresando");
                vista.setVisible(false);
            try {
                new ControladorInterCliente(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda(),user);
            } catch (IOException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LogIn.class.getName()).log(Level.SEVERE, null, ex);
            }
            }else{
                try {
                    aux=0;
                        throw new Exception("usuario o contraseña incorrectos");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
            }
    }    
}
class cancelarListener implements ActionListener{
    LogIn vista;

    public cancelarListener(LogIn vista) {
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