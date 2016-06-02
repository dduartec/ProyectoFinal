package Controller;

import Data.*;
import Model.ServicioCliente;
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

public class ControladorAddTarjeta extends AbstractController{
    Cliente user;
    public ControladorAddTarjeta(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda,Cliente user) throws IOException, ClassNotFoundException {
        setModel( new ServicioCliente(cliente,entrada,salida,tienda));
        setView(new AddTarjeta(getModel(), this,cliente,entrada,salida,tienda,user));
        ((JFrameView)getView()).setVisible(true);
        AddTarjeta vista = (AddTarjeta) this.getView();
        vista.setActionListener(vista.getEnviar(), new enviarListener(vista));
    }
}
class enviarListener implements ActionListener{
    AddTarjeta vista;

    public enviarListener(AddTarjeta vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            int cod=1;
            vista.getSalida().writeInt(cod);
            vista.getSalida().flush();
        long numero=Long.parseLong(vista.getNumero().getText().replace(" ",""));
        int aux=1;
        for (int i = 0; i < vista.getTienda().getUsuarios().size(); i++) {
            if (vista.getTienda().getUsuarios().get(i).getTipo().equals("cliente")) {
                Cliente c=(Cliente) vista.getTienda().getUsuarios().get(i);
               for (int j = 0; j < c.getTarjetas().size() ;j++) {
                   if (numero==c.getTarjetas().get(j).getNumero()) {
                       aux=-1;
                   }                
                } 
            }
            
        }
        if(aux==-1){
            throw new Exception("tarjeta ya registrada");
        }
        String propietario= vista.getPropietario().getText();
        String fecha=vista.getFecha().getText();
        int codigo=Integer.parseInt(vista.getCodigo().getText());
            ServicioCliente servi=(ServicioCliente)vista.getModel();
            servi.crearTarjeta(vista.getUser(), numero, propietario, fecha, codigo);
            JOptionPane.showMessageDialog(null, "Operacion Exitosa");
            vista.setVisible(false);
        }catch(Exception ex){
            try {
                vista.getSalida().writeInt(-1);
                vista.getSalida().flush();
            } catch (IOException ex1) {
                Logger.getLogger(enviarListener.class.getName()).log(Level.SEVERE, null, ex1);
            }            
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        
    }
    
}
