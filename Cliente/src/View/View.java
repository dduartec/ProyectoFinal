package View;

import Controller.Controller;
import Model.*;

public interface View {
    public Controller getController();
    public void setController(Controller controler);
    public Model getModel();
    public void setModel(Model model);
}
