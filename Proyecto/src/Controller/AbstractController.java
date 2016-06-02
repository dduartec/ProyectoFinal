package Controller;

import Model.Model;
import View.View;

public abstract class AbstractController implements Controller {
    private View view;
    private Model model;
    @Override
    public void setModel(Model model) {
        this.model = model;
    }
    @Override
    public Model getModel() {
        return model;
    }
    @Override
    public View getView() {
        return view;
    }
    @Override
    public void setView(View view) {
        this.view = view;
    }
}
