package Controller;

import Data.Administrador;
import Data.Usuario;
import Model.*;
import View.JFrameView;
import View.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Controlador extends AbstractController{

    public Controlador() throws IOException {
        setModel( new ServicioAdmin());
        setView(new InicioSesionAdmin((ServicioAdmin)getModel(), this));
        ((JFrameView)getView()).setVisible(true);
        InicioSesionAdmin vista=(InicioSesionAdmin) this.getView();
        vista.ingresar.addActionListener(new ingresarListener(vista));
    }
}
class ingresarListener implements ActionListener{
    InicioSesionAdmin vista;

    public ingresarListener(InicioSesionAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ServicioAdmin servi=(ServicioAdmin) vista.getModel();
        try {
            servi.getTienda().setUsuarios(servi.getDao().cargarUsuarios());
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        String nick=vista.getNick().getText();
        String password=vista.getContraseña().getText();
        String claveTienda=vista.getContraseñaTienda().getText();
        String tipo="admin";
        Usuario user=null;
        int aux=0;
        System.out.println(servi.getTienda().getUsuarios().toString());
            for(int i=0;i<servi.getTienda().getUsuarios().size();i++){
                if(password.equals(servi.getTienda().getUsuarios().get(i).getClave())&&nick.equals(servi.getTienda().getUsuarios().get(i).getNick())&&tipo.equals(servi.getTienda().getUsuarios().get(i).getTipo())){
                    JOptionPane.showMessageDialog(null,"ingresando");
                    vista.setVisible(false);
                    user=servi.getTienda().getUsuarios().get(i);
                    aux=1;
                    try {
                        new ControladorInterAdmin((Administrador) user);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(null,ex.getMessage());
                    }
                }
        }
        if(aux==0){
            try {
                throw new Exception("usuario o contraseña incorrectos");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
        }
        if(!claveTienda.equals(servi.getTienda().getClaveTienda())){
            try {
                throw new Exception("la clave escrita no coincide con la clave de la tienda");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null,ex.getMessage());
            }
            }
    }
    
}
