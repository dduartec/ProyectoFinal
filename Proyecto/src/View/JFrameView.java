package View;

import Model.ServicioCliente;
import Controller.Controller;
import Model.Model;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public abstract class JFrameView extends JFrame implements View{
     private Model model;
     private Controller controller;
     public JFrameView (Model model, Controller controller) {
        setModel(model);
        setController(controller);
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
    public void setActionListener(JButton boton,ActionListener ac){
        boton.addActionListener(ac);
    }
}