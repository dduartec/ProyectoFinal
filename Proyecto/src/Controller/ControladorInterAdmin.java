package Controller;

import Data.Administrador;
import Data.Cliente;
import Data.Producto;
import Data.Venta;
import Model.ServicioAdmin;
import View.CambiarContraseña;
import View.InterAdmin;
import View.JFrameView;
import View.PanelAdmin;
import View.PanelVentas;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class ControladorInterAdmin extends AbstractController {
    private Administrador admin;
    public ControladorInterAdmin(Administrador admin) throws IOException {
        this.admin=admin;
        setModel( new ServicioAdmin());
        setView(new InterAdmin((ServicioAdmin)getModel(), this));
        ((JFrameView)getView()).setVisible(true);
        InterAdmin vista=(InterAdmin) this.getView();
        vista.setActionListener(vista.getBuscarUser(), new buscarUserListener(vista));
        vista.setActionListener(vista.getBuscarP(), new buscarProductoListener(vista));
        vista.setActionListener(vista.getModificarCant(),new modificarCantidadListener(vista) );
        vista.setActionListener(vista.getAddProducto(),new agregarProductoListener(vista));
        vista.setActionListener(vista.getCerrarSesion(), new cerrarSesionListener(vista));
        vista.setActionListener(vista.getDatosPersonales(), new datosPersonalesListener(vista));
        vista.setActionListener(vista.getCambiarContraseña(), new cambiarConAdminListener(vista) );
        vista.setActionListener(vista.getListarVentas(), new listarVentasListener(vista));
    }

    public Administrador getAdmin() {
        return admin;
    }

    public void setAdmin(Administrador admin) {
        this.admin = admin;
    }
    
}
class buscarUserListener implements ActionListener{
    InterAdmin vista;

    public buscarUserListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String aux=vista.getTextC().getText();
        Cliente client=null;
        try {
            client = vista.getServi().buscarCliente(aux);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(vista, ex.getMessage());
        }
                vista.getNombre().setText(client.getNombre());
                vista.getNick().setText(client.getNick());
                vista.getEdad().setText(String.valueOf(client.getEdad()));
                vista.getCorreo().setText(client.getCorreo());
    }
}
class buscarProductoListener implements ActionListener{
    InterAdmin vista;

    public buscarProductoListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String aux=vista.getTextP().getText();
        try {
            Producto p= vista.getServi().buscarProducto(aux);
            vista.getNombre1().setText(p.getNombre());
            vista.getCodigo().setText(p.getCodigo());
            vista.getPrecio().setText(String.valueOf(p.getPrecio()));
            vista.getTipo().setText(p.getTipo());
            vista.getPlataforma().setText(p.getPlataforma());
            vista.getCantidad().setText(String.valueOf(p.getCantidad()));
            ImageIcon icon=new ImageIcon(p.getDirImage());
            Image img= icon.getImage();
            Image auximg=img.getScaledInstance(vista.getImgProducto().getWidth(),vista.getImgProducto().getHeight(),java.awt.Image.SCALE_SMOOTH);
            ImageIcon otroicon = new ImageIcon(auximg);
            vista.getImgProducto().setIcon(otroicon);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
}
class modificarCantidadListener implements ActionListener{
    InterAdmin vista;

    public modificarCantidadListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         String aux=vista.getTextP().getText();
         int nuevaCant=0;
         try{
             nuevaCant=Integer.parseInt(vista.getModCantidad().getText());
             vista.getServi().modificarCantidad(aux, nuevaCant);
             JOptionPane.showMessageDialog(vista, "cantidad cambiada con exito");
             vista.getCantidad().setText(String.valueOf(nuevaCant));
         }catch(Exception ex){
             JOptionPane.showMessageDialog(vista, ex.getMessage());
         }
        
    }
}
class agregarProductoListener implements ActionListener{
    InterAdmin vista;

    public agregarProductoListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            new ControladorAddProducto();
        } catch (IOException ex) {
            Logger.getLogger(agregarProductoListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
class cerrarSesionListener implements ActionListener{
    InterAdmin vista;

    public cerrarSesionListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        vista.setVisible(false);
        try {
            new Controlador();
        } catch (IOException ex) {
            Logger.getLogger(cerrarSesionListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
class datosPersonalesListener implements ActionListener{
    InterAdmin vista;

    public datosPersonalesListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new PanelAdmin(((ControladorInterAdmin)vista.getController()).getAdmin()).setVisible(true);
    }
    
}
class cambiarConAdminListener implements ActionListener{
    InterAdmin vista;

    public cambiarConAdminListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
         new CambiarContraseña(((ControladorInterAdmin)vista.getController()).getAdmin()).setVisible(true);
        try {
            ((ServicioAdmin)vista.getModel()).getDao().guardarUsuarios(((ServicioAdmin)vista.getModel()).getDao().cargarUsuarios());
        } catch (IOException ex) {
            Logger.getLogger(cambiarConAdminListener.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
            

}
class listarVentasListener implements ActionListener{
    InterAdmin vista;

    public listarVentasListener(InterAdmin vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ArrayList<Venta> ventas = null;
        try {
            ventas = ((ServicioAdmin)vista.getModel()).getDao().cargarVentas();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(listarVentasListener.class.getName()).log(Level.SEVERE, null, ex);
        }
        new PanelVentas(ventas).setVisible(true);
    }
    
}
