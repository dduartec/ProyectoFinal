package Controller;

import Model.ServicioCliente;
import View.View;

public abstract class AbstractController implements Controller {
    private View view;
    private ServicioCliente model;
    
    @Override
    public void setModel(ServicioCliente model) {
        this.model = model;
    }
    @Override
    public ServicioCliente getModel() {
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
