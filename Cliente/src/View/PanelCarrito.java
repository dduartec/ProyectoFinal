
package View;

import Controller.ControladorCarritoCliente;
import Data.Carrito;
import Data.Producto;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class PanelCarrito extends javax.swing.JPanel {

    Producto p;
    Carrito c;
    CarritoCliente cc;
  
    public PanelCarrito(Producto p,Carrito c,CarritoCliente cc) {
        this.p=p;
        this.c=c;
        this.cc=cc;
        initComponents();
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        quitarCarrito = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        setMinimumSize(new java.awt.Dimension(400, 39));
        setLayout(null);

        quitarCarrito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/minus.png"))); // NOI18N
        quitarCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarCarritoActionPerformed(evt);
            }
        });
        add(quitarCarrito);
        quitarCarrito.setBounds(300, 0, 40, 20);

        jLabel1.setText(p.getNombre() + "     " + p.getPrecio());
        add(jLabel1);
        jLabel1.setBounds(30, 0, 200, 20);
    }// </editor-fold>//GEN-END:initComponents

    private void quitarCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarCarritoActionPerformed
        c.quitarProducto(p);
        JOptionPane.showMessageDialog(null,"Se ha eliminado correctamente");
        cc.setVisible(false);
        try {
            new ControladorCarritoCliente(cc.getCliente(),cc.getEntrada(),cc.getSalida(),cc.getTienda(),cc.getUser());
        } catch (IOException ex) {
            Logger.getLogger(PanelCarrito.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(PanelCarrito.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_quitarCarritoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JButton quitarCarrito;
    // End of variables declaration//GEN-END:variables


    
    
}
