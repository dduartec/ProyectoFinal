package View;

import Controller.Controller;
import Data.*;
import Model.Model;
import Model.ServicioCliente;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

public class AddTarjeta extends JFrameView{
    Cliente user;
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy kk:mm:ss");
        

    public AddTarjeta(Model model, Controller controller,Socket cliente,ObjectInputStream entrada,ObjectOutputStream salida,Tienda tienda,Cliente user) {
        super(model, controller,cliente,entrada,salida,tienda);
        this.user = user;        
        initComponents();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        numero = new javax.swing.JFormattedTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        propietario = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        fecha = new javax.swing.JFormattedTextField();
        codigo = new javax.swing.JFormattedTextField();
        jLabel5 = new javax.swing.JLabel();
        enviar = new javax.swing.JButton();

        numero.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                numeroMouseClicked(evt);
            }
        });
        numero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                numeroActionPerformed(evt);
            }
        });
        try
        {
            MaskFormatter mascara = new MaskFormatter("#### #### #### ####");
            numero = new JFormattedTextField(mascara);
        }
        catch (Exception e){}

        jLabel1.setText("Numero");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel2.setText("Añadir Tarjeta");

        jLabel3.setText("Propietario");

        jLabel4.setText("Fecha de Vencimiento");

        try
        {
            fecha = new JFormattedTextField(new FormatoFecha());
        }
        catch (ParseException ex){
            System.out.println("error fecha");
        }

        try
        {
            MaskFormatter mascara = new MaskFormatter("###");
            codigo = new JFormattedTextField(mascara);
        }
        catch (Exception e){}

        jLabel5.setText("Codigo de seguridad");

        enviar.setText("ENVIAR");
        enviar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                enviarKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel5)
                            .addComponent(jLabel4)
                            .addComponent(jLabel1)
                            .addComponent(numero)
                            .addComponent(jLabel3)
                            .addComponent(propietario)
                            .addComponent(fecha)
                            .addComponent(codigo, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(68, 68, 68)
                        .addComponent(jLabel2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(105, 105, 105)
                        .addComponent(enviar)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(numero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(propietario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(fecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(enviar)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void numeroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_numeroMouseClicked

    }//GEN-LAST:event_numeroMouseClicked

    private void numeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_numeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_numeroActionPerformed

    private void enviarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_enviarKeyPressed

    }//GEN-LAST:event_enviarKeyPressed


    public void enviar() throws IOException{
        try{
            long numero=Long.parseLong(this.numero.getText().replace(" ",""));
            String propietario= this.propietario.getText();
            String fecha=this.fecha.getText();
            int codigo=Integer.parseInt(this.codigo.getText());
            ServicioCliente servi=(ServicioCliente)getModel();
            servi.crearTarjeta(user, numero, propietario, fecha, codigo);
        }catch(Exception ex){
            JOptionPane.showMessageDialog(null,ex.getMessage());
        }
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JFormattedTextField codigo;
    private javax.swing.JButton enviar;
    private javax.swing.JFormattedTextField fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JFormattedTextField numero;
    private javax.swing.JTextField propietario;
    // End of variables declaration//GEN-END:variables

   
    public Cliente getUser() {
        return user;
    }

    public void setUser(Cliente user) {
        this.user = user;
    }

    public SimpleDateFormat getFormato() {
        return formato;
    }

    public void setFormato(SimpleDateFormat formato) {
        this.formato = formato;
    }

    public JFormattedTextField getCodigo() {
        return codigo;
    }

    public void setCodigo(JFormattedTextField codigo) {
        this.codigo = codigo;
    }

    public JFormattedTextField getFecha() {
        return fecha;
    }

    public void setFecha(JFormattedTextField fecha) {
        this.fecha = fecha;
    }

    public JFormattedTextField getNumero() {
        return numero;
    }

    public void setNumero(JFormattedTextField numero) {
        this.numero = numero;
    }

    public JTextField getPropietario() {
        return propietario;
    }

    public void setPropietario(JTextField propietario) {
        this.propietario = propietario;
    }

    public JButton getEnviar() {
        return enviar;
    }

    public void setEnviar(JButton enviar) {
        this.enviar = enviar;
    }
    

}
class FormatoFecha extends MaskFormatter{
    private SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yy");
    public FormatoFecha() throws ParseException    {
        super ("##/##/##");
    }
    
    public Object stringToValue(String text) throws ParseException{
        return formato.parseObject(text);
    }
    public String valueToString(Object value) throws ParseException
    {
        if (value instanceof Date)
            return formato.format((Date)value);
        return formato.format(new Date());
    }
}