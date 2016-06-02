
package View;

import Model.ServicioCliente;
import Controller.Controller;
import Data.Tienda;
import Model.Model;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class JFrameView extends JFrame implements View{
     protected Model model;
     protected Controller controller;
     protected Socket cliente;
     protected ObjectInputStream entrada;
     protected ObjectOutputStream salida;
     protected Tienda tienda; 

    public JFrameView(Model model, Controller controller, Socket cliente, ObjectInputStream entrada, ObjectOutputStream salida, Tienda tienda) {
        this.model = model;
        this.controller = controller;
        this.cliente = cliente;
        this.entrada = entrada;
        this.salida = salida;
        this.tienda = tienda;
    }
     
    public Controller getController() {
        return controller;
    }
     @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }
    @Override
    public Model getModel() {
        return model;
    }
    @Override
    public void setModel(Model model) {
        this.model = model;
    }

    public Socket getCliente() {
        return cliente;
    }

    public void setCliente(Socket cliente) {
        this.cliente = cliente;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public void setEntrada(ObjectInputStream entrada) {
        this.entrada = entrada;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public void setSalida(ObjectOutputStream salida) {
        this.salida = salida;
    }

    public Tienda getTienda() {
        return tienda;
    }

    public void setTienda(Tienda tienda) {
        this.tienda = tienda;
    }
    
    public void setActionListener(JButton boton,ActionListener ac){
        boton.addActionListener(ac);
    }
    
}