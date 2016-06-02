package View;

import Controller.Controller;
import Data.Tienda;
import Model.ServicioCliente;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;

public class InicioCliente extends JFrameView{
    public InicioCliente(ServicioCliente model, Controller controller,Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda) {
        super(model, controller,cliente,entrada,salida,tienda);
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        IniciarSesion = new javax.swing.JButton();
        Registrarse = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setAutoRequestFocus(false);
        setResizable(false);

        IniciarSesion.setText("Log In");
        IniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                IniciarSesionActionPerformed(evt);
            }
        });

        Registrarse.setText("Registrarse");

        jLabel1.setFont(new java.awt.Font("Microsoft New Tai Lue", 0, 18)); // NOI18N
        jLabel1.setText("BIENVENIDO");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(IniciarSesion)
                        .addGap(18, 18, 18)
                        .addComponent(Registrarse)))
                .addContainerGap(25, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(IniciarSesion)
                    .addComponent(Registrarse))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void IniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_IniciarSesionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_IniciarSesionActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton IniciarSesion;
    private javax.swing.JButton Registrarse;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables


    public JButton getIniciarSesion() {
        return IniciarSesion;
    }

    public void setIniciarSesion(JButton IniciarSesion) {
        this.IniciarSesion = IniciarSesion;
    }

    public JButton getRegistrarse() {
        return Registrarse;
    }

    public void setRegistrarse(JButton Registrarse) {
        this.Registrarse = Registrarse;
    }
    

    
}
