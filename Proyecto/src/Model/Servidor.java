package Model;

import View.UIserver;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Servidor {
    
    UIserver vista;

    public Servidor() {
        vista=new UIserver(this);
        try {
            ServerSocket serverSocket = new ServerSocket(8000);
            vista.getTexto().append("Inicializando servidor: " + new Date() + '\n');
            int clienteN = 1;
            while (true) {
                Socket cliente = serverSocket.accept();
                vista.getTexto().append("Iniciando hilo para el cliente N° " + clienteN + " : " + new Date() + '\n');
                InetAddress inetAddress = cliente.getInetAddress();
                vista.getTexto().append(" La IP del Cliente N° " + clienteN + " es:  "+ inetAddress.getHostAddress() + "\n");
                HandleAClient tarea = new HandleAClient(cliente,this,clienteN);
                vista.getTexto().append(tarea.getMensaje());
                Thread hilo=new Thread(tarea);
                hilo.start();
                clienteN++;
            }
        }catch(IOException ex) {
      System.err.println(ex);
    }
        
  }

    public UIserver getVista() {
        return vista;
    }

    public void setVista(UIserver vista) {
        this.vista = vista;
    }
  class HandleAClient implements Runnable {
    private Socket cliente;
    private String mensaje="";
    private Servidor servidor;
    private int nCliente;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;
    
    public HandleAClient(Socket cliente,Servidor servidor, int nCliente) throws IOException{
      this.cliente = cliente;
      this.servidor=servidor;
      this.nCliente=nCliente;
      entrada=new ObjectInputStream(this.cliente.getInputStream());
      salida=new ObjectOutputStream(this.cliente.getOutputStream());
    }
    public void run(){
      try {
        while (true) {
            
            ServicioCliente vista=new ServicioCliente(cliente);
            vista.enviarDatos(entrada, salida);
        }
      }
      catch(IOException e) {
          servidor.getVista().getTexto().append("Se ha desconectado el cliente N° "+nCliente+"\n");
      } catch (Exception ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

        public String getMensaje() {
            return mensaje;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }
    
  }
}