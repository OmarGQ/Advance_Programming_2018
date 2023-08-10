/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 *
 * @author danie
 */
public class Ventana extends JFrame{
    
    private JTextArea historial;
    private JScrollPane scroll;
    private String modo = "texto", carpeta="C:\\documentos";
    private boolean ban = false;
    Hilo hilo;
    
    public Ventana(){
        super();
        historial=new JTextArea();
        historial.setEditable(false);
        scroll = new JScrollPane(historial);
        scroll.setLocation(100,70);
        scroll.setSize(600,200);
        hilo=new Hilo();
        hilo.start();
        
        File folder = new File(carpeta);
        if (!folder.exists()) {
            folder.mkdir();
        }
        
        setLayout(null);
        
        add(scroll);
        setSize(800, 400);
        Toolkit tk;
        tk=Toolkit.getDefaultToolkit ();
        Dimension tamPant=tk.getScreenSize();
        setLocation((tamPant.width-getSize().width)/2,(tamPant.height-getSize().height)/2);
        setTitle("Servidor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
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
                    historial.append("\n"+mensaje);
                    System.out.println(mensaje);///////////////////////////////////////////////////////////////////////
                    String[] dat = mensaje.split("/");
                    if(dat[1].equals("3")&& modo.equals("binario")){
                        InputStream out =socket.getInputStream();
                        FileOutputStream file = new FileOutputStream(carpeta+"/"+dat[2]);
                        byte[] buf = new byte[4096];
                        int len; 
                        while((len=out.read(buf))>0){ 
                            file.write(buf,0,len); 
                        } 
                        out.close();
                        file.close();
                        
                    }
                    socket.close();
                    Peticion(mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void Peticion(String dato){
        System.out.println(dato);///////////////////////////////////////////////////////////////////////
        String respuesta = null;
        String[] partes = dato.split("/");
        switch(partes[1]){
            case "1":
                respuesta = "1/"+modo;
            break;
            case "3":
                if(modo.equals("texto")){
                    String[] direccion = dato.split("369A");
                    String nombre = carpeta+"/"+direccion[1];
                    File archivo = new File(carpeta);
                    BufferedWriter bw;
                    try{
                        if(archivo.exists()) {
                        bw = new BufferedWriter(new FileWriter(nombre));
                        bw.write(direccion[2]);
                        } else {
                            bw = new BufferedWriter(new FileWriter(nombre));
                            bw.write(direccion[2]);
                        }
                        bw.close();
                        respuesta = "3/1";
                    } catch (IOException ex) {
                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                        respuesta = "3/0";
                    }
                }if (modo.equals("binario")){
                    respuesta = "3/1";
                }
            break;
            case "4":
                if(partes.length == 2){
                    respuesta = "4/"+modo;
                }else if(partes.length == 3){
                    modo = partes[2];
                    respuesta = "4/1/"+modo;
                }else{
                    respuesta = "4/0";
                }
            break;
            case "6":
                switch(modo){
                    case "texto":
                        if(partes.length == 3){
                        String url = carpeta+"/"+partes[2];
                        File f = new File(url);
                        String documento = f.getName();
                        if(f.exists())
                        {
                            String cadena="", tex;
                            try {
                            FileReader fis = new FileReader(url);
                            BufferedReader b = new BufferedReader(fis);
                            while((tex = b.readLine())!=null) {
                                cadena += tex;
                            }
                            respuesta = "6/369A"+documento+"369A"+cadena;
                            b.close();
                            } catch (FileNotFoundException ex) {
                                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                                respuesta = "6/369A 369A";
                            } catch (IOException ex) {
                                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                                respuesta = "6/369A 369A";
                            }
                        }else{
                            respuesta = "6/369A 369A";
                        }
                    }else{
                        respuesta = "6/369A 369A";
                    }
                    break;
                    case "binario":
                        try{
                            Socket clientSocket = new Socket(partes[0],1010);
                            DataOutputStream mensaje = new DataOutputStream(clientSocket.getOutputStream());
                            File f = new File(carpeta+"/"+partes[2]);
                            String nom = f.getName();
                            mensaje.writeUTF("6/"+nom);
                            PrintStream out = new PrintStream(clientSocket.getOutputStream());
                            FileInputStream file = new FileInputStream(carpeta+"/"+partes[2]);
                            byte[] buf = new byte[4096];
                            int len; 
                            while((len=file.read(buf))>0){ 
                                out.write(buf,0,len); 
                            }
                            respuesta = "6/1";
                            file.close();
                            mensaje.close();
                            out.close();
                            clientSocket.close();
                        }catch (UnknownHostException e){
                            System.out.println(e);
                            respuesta = "6/0";
                        }catch (IOException e) {
                            System.out.println(e);
                            respuesta = "6/0";
                        }
                    break;
                }
            break;
            case "7":
                String arch = "";
                File dir = new File(carpeta);
                String[] ficheros = dir.list();
                if (ficheros == null)
                    arch = "";
                else { 
                    for (int i=0;i<ficheros.length;i++)
                    arch += ficheros[i]+"<>";
                }
                respuesta = "7/"+arch;
            break;
            default: 
                historial.append("\n    No se reconose el comando");
        }
         try{
            System.out.println(respuesta);///////////////////////////////////////////////////////////////////////
            Socket socket = new Socket(partes[0],1010);
            DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
            mensaje.writeUTF(respuesta);
            mensaje.close();
            socket.close();
            } catch (IOException ex) {
            System.out.println(ex.getMessage());
            }
    }
}
