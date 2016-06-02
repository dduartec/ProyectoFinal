package Controller;

import Model.Model;
import View.View;

public interface Controller {
    public void setView(View view);
    public View getView();
    public Model getModel();
    public void setModel(Model model);
}
