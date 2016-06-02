package Controller;

import Data.*;
import Model.ServicioCliente;
import View.CarritoCliente;
import View.JFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JOptionPane;

public class ControladorCarritoCliente extends AbstractController{
    Cliente user;
    public ControladorCarritoCliente(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda,Cliente user) throws IOException, ClassNotFoundException {
        setModel( new ServicioCliente(cliente,entrada,salida,tienda));
        setView(new CarritoCliente(user,getModel(), this,cliente,entrada,salida,tienda));
        ((JFrameView)getView()).setVisible(true);
        CarritoCliente vista = (CarritoCliente) this.getView();
        vista.setActionListener(vista.getConfirmarCompra(), new ConfirmarCompraListener(vista));
    }
    
}
class ConfirmarCompraListener implements ActionListener{
    CarritoCliente vista;

    public ConfirmarCompraListener(CarritoCliente vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(vista.getUser().getTarjetas().size()==0){
                throw new Exception("debe añadir una tarjeta para poder comprar");
            }
            new ControladorConfirmar(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda(),vista.getUser());
            vista.setVisible(false);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        }
    }
    
}

