/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prychat85;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Liliana
 */
public class Servidor extends Observable implements Runnable{
    private int puerto;

    public Servidor(int puerto) {
        this.puerto = puerto;
    }
    
    @Override
    public void run() {
       //Configurar el socket del servidor para atender
       ServerSocket servidor=null;
       //Configurar el Socket para escuchar cliente
       Socket sc = null;
       //Objeto para recibir mensajes de cliente
       DataInputStream in = null;
       //Objeto para responder 
       DataOutputStream out = null;
       
        try {
            servidor = new ServerSocket(puerto,11);
            System.out.println("Servidor iniciando...");
            //Siempre escuche las peticiones clientes
            while(true){
                //Esperar la conexion de un cliente
                sc = servidor.accept();
                System.out.println("Cliente conectado");
                in = new DataInputStream(sc.getInputStream());
                //Leer el mensaje que se envía
                String mensaje = in.readUTF();
                //Utilizar los métodos de la clase Observable para
                //escuchar notificaciones
                this.setChanged();
                this.notifyObservers(mensaje);
                this.clearChanged();
                //Cerrar socket
                sc.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
       
       
    }
    
}
