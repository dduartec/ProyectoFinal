package Controller;

import Data.Producto;
import Model.ServicioAdmin;
import View.BuscarImagen;
import View.JFrameView;
import View.addProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class ControladorAddProducto extends AbstractController{
    public ControladorAddProducto() throws IOException {
        setModel( new ServicioAdmin());
        setView(new addProducto((ServicioAdmin)getModel(), this));
        ((JFrameView)getView()).setVisible(true);
        addProducto vista=(addProducto) this.getView();
        vista.enviarProducto.addActionListener(new enviarProductoListener(vista));
        vista.examinar.addActionListener(new examinarListener(vista));
    }
}
class enviarProductoListener implements ActionListener{
    addProducto vista;

    public enviarProductoListener(addProducto vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            ArrayList<Producto> productos=vista.servi.getDao().cargarProductos();
            int aux=-1;
            String nombre=vista.nombre.getText();
            String codigo =vista.codigo.getText();
            for (int i = 0; i < productos.size(); i++) {
                if(codigo.equals(productos.get(i).getCodigo())){
                    aux=0;
                }
            }
            if(aux==0){
                throw new Exception("codigo ya registrado");
            }else{              
                double precio =Double.parseDouble(vista.precio.getText());
                String tipo=String.valueOf(vista.tipo.getSelectedItem());
                String plataforma=String.valueOf(vista.plataforma.getSelectedItem());
                int cantidad=Integer.parseInt(vista.cantidad.getText());
                String dirImagen=vista.imagen.getText();
                BufferedImage imagen = ImageIO.read(new File(dirImagen));
                dirImagen="src\\Recursos\\"+codigo+".png";
                ImageIO.write(imagen, "png", new File(dirImagen));
                vista.servi.crearProducto(nombre, codigo, precio, tipo, plataforma, cantidad, tipo, tipo, dirImagen);
                JOptionPane.showMessageDialog(null, "Producto creado satisfactoriamente");
                vista.setVisible(false);
            }
    }catch(Exception ex){
        JOptionPane.showMessageDialog(null,ex.getMessage());
    }        
    }    
}
class examinarListener implements ActionListener{
    addProducto vista;

    public examinarListener(addProducto vista) {
        this.vista = vista;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       BuscarImagen img=new BuscarImagen(vista);
       img.setVisible(true);
    }
     
}