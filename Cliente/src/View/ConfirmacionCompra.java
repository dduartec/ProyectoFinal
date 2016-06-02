package View;

import Controller.Controller;
import Data.Carrito;
import Data.Cliente;
import Data.Tienda;
import Model.Model;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JComboBox;

public class ConfirmacionCompra extends JFrameView {
    
    private Cliente user;
    

    public Cliente getUser() {
        return user;
    }

    public void setUser(Cliente user) {
        this.user = user;
    }

    public ConfirmacionCompra(Cliente user, Socket cliente, ObjectInputStream entrada, ObjectOutputStream salida, Tienda tienda, Model model, Controller controller) {
        super(model, controller,cliente,entrada,salida,tienda);
        this.user = user;
        initComponents();
    }
    
    
   

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        textCompra = new javax.swing.JLabel();
        comboTarjetas = new javax.swing.JComboBox<>();
        Comprar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cancelar = new javax.swing.JButton();

        textCompra.setText("Usuario:" + user.getNombre() + "\n"
            + "Precio Total: " + user.getCarrito().getPrecioTotal() +"\n"
            + "Seleccione tarjeta:");
        /*textCompra.setText("Usuario:" + user.getNombre() "\n"
            + "Precio Total: " + user.getCarrito().getPrecioTotal() +"\n"
            + "Seleccione tarjeta:");*/

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Confirmacion de Compra");

        user.getTarjetas().size();
        comboTarjetas.setMaximumRowCount(user.getTarjetas().size());
        comboTarjetas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ads" }));
        String[] numTarjetas=new String[user.getTarjetas().size()];
        for(int i=0;i<user.getTarjetas().size();i++){
            numTarjetas[i]=(String.valueOf(user.getTarjetas().get(i).getNumero()));
        }
        comboTarjetas.setModel(new javax.swing.DefaultComboBoxModel<>(numTarjetas));
        //comboTarjetas.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { user.getTarjetas().get(0), user.getTarjetas().get(1), user.getTarjetas().get(2)}));

        Comprar.setText("Comprar");

        jLabel1.setText("Seleccione la tarjeta:");

        jLabel2.setText("Usuario: ");
        jLabel2.setText("Usuario: "+user.getNombre());

        jLabel3.setText("Precio: ");
        jLabel3.setText("Precio: "+String.valueOf(user.getCarrito().getPrecioTotal()));

        cancelar.setText("Cancelar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1)
                    .addComponent(comboTarjetas, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 245, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Comprar, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(36, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboTarjetas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Comprar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    public JButton getComprar() {
        return Comprar;
    }

    public void setComprar(JButton Comprar) {
        this.Comprar = Comprar;
    }

    public JComboBox<String> getComboTarjetas() {
        return comboTarjetas;
    }

    public void setComboTarjetas(JComboBox<String> comboTarjetas) {
        this.comboTarjetas = comboTarjetas;
    }

    public JButton getCancelar() {
        return cancelar;
    }

    public void setCancelar(JButton cancelar) {
        this.cancelar = cancelar;
    }
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Comprar;
    private javax.swing.JButton cancelar;
    private javax.swing.JComboBox<String> comboTarjetas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel textCompra;
    // End of variables declaration//GEN-END:variables
}
