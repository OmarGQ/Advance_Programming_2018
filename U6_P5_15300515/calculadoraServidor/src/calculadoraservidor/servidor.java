/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package calculadoraservidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pablo
 */
public class servidor {
    private float a, b, r;
    Hilo hilo;
    public servidor(){
        hilo = new Hilo();
        hilo.start();
    }
    public void evaluar(String dato){
        System.out.println(dato);
        String[] partes = dato.split("/");
        if(partes.length == 4){
            try{
                a = Float.parseFloat(partes[2]);
                b = Float.parseFloat(partes[3]);
            }catch (Exception ex) {
                r = 000;
            }
            System.out.println(a);
            System.out.println(b);
            switch(partes[1]){
                case "1":
                    r = a + b;
                    break;
                case "2":
                    r = a - b;
                    break;
                case "3":
                    r = a * b; 
                    break;
                case "4":
                    r = a / b;
                    break;
            }
        }else{
            r = 0000;
        }
        try{
            Socket socket = new Socket(partes[0], 1010);
            DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
            mensaje.writeUTF(""+r);
            mensaje.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public class Hilo extends Thread {
        public Hilo() {
            super();
        }
        public void run(){
            try{
                ServerSocket servidor = new ServerSocket(999);
                while(true){
                    Socket socket = servidor.accept();
                    DataInputStream entrada = new DataInputStream(socket.getInputStream());
                    String mensaje = entrada.readUTF();
                    socket.close();
                    evaluar(mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(servidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
