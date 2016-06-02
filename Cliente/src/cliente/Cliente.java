package cliente;

import Controller.ControladorInicio;
import Data.Tienda;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Socket cliente= new Socket("localhost",8000);
        ObjectOutputStream salida=new ObjectOutputStream(cliente.getOutputStream());
        ObjectInputStream entrada=new ObjectInputStream(cliente.getInputStream());
        Tienda tienda=(Tienda) entrada.readObject();
        for (int i = 0; i < tienda.getPruductos().size(); i++) {  
            ImageIcon icon=(ImageIcon) entrada.readObject();
            BufferedImage bi = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics g = bi.createGraphics();
            icon.paintIcon(null, g, 0,0);
            g.dispose();                      
            String dirImagen="src\\Recursos\\"+tienda.getPruductos().get(i).getCodigo()+".png";
            tienda.getPruductos().get(i).setDirImage(dirImagen);
            tienda.getPruductos().get(i).setImagen(bi);
            ImageIO.write(bi, "jpg", new File(dirImagen));
        } 
        new ControladorInicio(cliente,entrada,salida,tienda);
    }
    
}
