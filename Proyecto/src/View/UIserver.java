package View;

import Model.Servidor;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class UIserver extends JFrame{
    private JTextArea texto;
    private Servidor server;

    public UIserver(Servidor server) {
        this.server = server;
        texto=new JTextArea();
         setLayout(new BorderLayout());
        add(new JScrollPane(texto), BorderLayout.CENTER);
        setTitle("Server");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public JTextArea getTexto() {
        return texto;
    }

    public void setTexto(JTextArea texto) {
        this.texto = texto;
    }
    
    
    
}
