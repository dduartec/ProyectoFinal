package View;

import Model.Model;
import Controller.Controller;

public interface View {
    public Controller getController();
    public void setController(Controller controler);
    public Model getModel();
    public void setModel(Model model);
}
