package Controller;

import Data.Cliente;
import Data.Tienda;
import Model.ServicioCliente;
import View.ConfirmacionCompra;
import View.JFrameView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ControladorConfirmar extends AbstractController{
    
    Cliente user;
  
    
    public ControladorConfirmar(Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda,Cliente user) throws IOException, ClassNotFoundException {
        this.user=user;
        setModel( new ServicioCliente(cliente,entrada,salida,tienda));
        setView(new ConfirmacionCompra(user,cliente,entrada,salida,tienda,this.getModel(),this));
        ((JFrameView)getView()).setVisible(true);
        ConfirmacionCompra vista = (ConfirmacionCompra) this.getView();
        vista.setActionListener(vista.getComprar(),new comprarListener(vista));
        vista.setActionListener(vista.getCancelar(), new cancelarCompraListener(vista));
    }
    
}
class comprarListener implements ActionListener{
    ConfirmacionCompra vista;

    public comprarListener(ConfirmacionCompra vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String t=String.valueOf(vista.getComboTarjetas().getSelectedItem());        
        for (int i = 0; i < vista.getUser().getTarjetas().size(); i++) {
            if(t.equals(vista.getUser().getTarjetas().get(i))){
                double nuevoSaldo=vista.getUser().getTarjetas().get(i).getSaldo()-vista.getUser().getCarrito().getPrecioTotal();
                vista.getUser().getTarjetas().get(i).setSaldo(nuevoSaldo);
            }
        }
        String fecha= String.valueOf(new Date());
        vista.getUser().getCarrito().getLista().clear();
        try{
            int codigo=2;
            vista.getSalida().writeInt(codigo);
            vista.getSalida().flush();
            ((ServicioCliente)vista.getModel()).crearVenta(vista.getUser(), fecha, vista.getUser().getCarrito().getPrecioTotal());
            JOptionPane.showMessageDialog(null,"venta creada con exito");
            vista.setVisible(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        
    }
}
class cancelarCompraListener implements ActionListener{
    ConfirmacionCompra vista;

    public cancelarCompraListener(ConfirmacionCompra vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new ControladorCarritoCliente(vista.getCliente(),vista.getEntrada(),vista.getSalida(),vista.getTienda(),vista.getUser());
            vista.setVisible(false);
        } catch (IOException ex) {
            Logger.getLogger(cancelarCompraListener.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(cancelarCompraListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}    