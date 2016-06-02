
package Controller;

import Model.*;
import View.*;

public interface Controller {
    public void setView(View view);
    public View getView();
    public ServicioCliente getModel();
    public void setModel(ServicioCliente model);
}
