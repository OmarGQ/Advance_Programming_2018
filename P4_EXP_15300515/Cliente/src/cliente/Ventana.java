/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author danie
 */
public class Ventana extends JFrame{
    private String text, thisIp, modo = "", ipt, carpeta="C:/servidor";
    private int portt;
    private JTextField mensaje, texip;
    private JPanel texport;
    private JTextArea historial;
    private JScrollPane scroll;
    private JButton send;
    private JLabel port, message, ip;
    private boolean conectBan = false;
    Hilo hilo;
    
    public Ventana()
    {
        super();
        send=new JButton("Ejecutar");
        send.setLocation(650,300);
        send.setSize(100,30);
        texip=new JTextField();
        texip.setLocation(130,30);
        texip.setSize(100,30);
        texip.setEditable(false);
        texport=new JPanel();
        texport.setLocation(400,30);
        texport.setSize(100,30);
        texport.setBackground(Color.red);
        historial=new JTextArea("Escribe 'ayuda' para ver lista de comandos");
        historial.setEditable(false);
        historial.setFont(new Font("Arial", Font.PLAIN, 16));
        historial.setLineWrap(true);
        scroll = new JScrollPane(historial);
        scroll.setLocation(100,70);
        scroll.setSize(600,200);
        mensaje=new JTextField("conectar 192.168.1.75,999");
        mensaje.setLocation(200,300);
        mensaje.setSize(400,30);
        port=new JLabel("Estadod de conexion:");
        port.setLocation(260,30);
        port.setSize(150,30);
        message=new JLabel("Comando:");
        message.setLocation(100,300);
        message.setSize(100,30);
        ip=new JLabel("Ip:");
        ip.setLocation(100,30);
        ip.setSize(70,30);
        try //obtener direccion Ip del equipo
        {
            thisIp = InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException e) 
        {
            e.printStackTrace();
        }
        File folder = new File(carpeta);
        if (!folder.exists()) {
            folder.mkdir();
        }
        
        setLayout(null);
        
        send.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent ae) {
                acciones();
            }
        });
        
        add(send);
        add(texip);
        add(texport);
        add(scroll);
        add(mensaje);
        add(port);
        add(message);
        add(ip);
        setSize(800, 400);
        Toolkit tk;
        tk=Toolkit.getDefaultToolkit ();
        Dimension tamPant=tk.getScreenSize();
        setLocation((tamPant.width-getSize().width)/2,(tamPant.height-getSize().height)/2);
        setTitle("Cliente");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }
    public void acciones(){
        text = mensaje.getText();
        mensaje.setText("");
        historial.append("\nTu: " + text);
        String[] partes = text.split(" ");
        switch(partes[0]){
            case "conectar":
                if(partes.length == 2){
                    String[] direccion = partes[1].split(",");
                    String[] octetos = direccion[0].split("\\.");
                    if(conectBan == false){
                        if(direccion.length == 2 && octetos.length == 4){
                            historial.append("\n    Estableciendo conexion con"+direccion[0]+":"+direccion[1]);
                            try{
                                Socket socket = new Socket(direccion[0], Integer.parseInt(direccion[1]));
                                DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
                                mensaje.writeUTF(thisIp+"/1/");
                                mensaje.close();
                                socket.close();
                                texip.setText(direccion[0]+":"+direccion[1]);
                                texport.setBackground(Color.GREEN);
                                conectBan = true;
                                ipt = direccion[0];
                                portt = Integer.parseInt(direccion[1]);
                                historial.append("\n    Se establecio conexion con"+direccion[0]+":"+direccion[1]);
                                hilo = new Hilo();
                                hilo.start();
                            } catch (IOException ex) {
                                    historial.append("\n    No se logro conectar con"+direccion[0]+":"+direccion[1]);
                                    System.out.println(ex.getMessage());
                            }
                        }else{
                            historial.append("\n    La estructura del comando es incorrecta");
                        }
                    }else{
                        historial.append("\n    Ya hay una conexion abierta");
                    }
                }else{
                   historial.append("\n    La estructura del comando es incorrecta");
                }
            break;
            case "desconectar":
                if(partes.length == 1){
                    if(conectBan == true){
                        texip.setText("");
                        texport.setBackground(Color.red);
                        conectBan = false;
                        ipt = null;
                        portt = 0;
                        historial.append("\n   Desconexion exitosa");
                    }else{
                        historial.append("\n    No hay una conexion que cerrar");
                    }
                }else{
                    historial.append("\n    La estructura del comando es incorrecta");
                }
                historial.setCaretPosition(historial.getDocument().getLength());
            break;
            case "cargar":
                String url = partes[1];
                for (int i = 2; i < partes.length; i++) {
                    url +=" "+partes[i];
                    System.out.println(url);
                }
                String[] parte = url.split("\\.");
                if(conectBan == true){
                    switch (modo) {
                        case "texto":
                            if(partes.length >= 2){
                                File f = new File(url);
                                String nombre = f.getName();
                                if(f.exists()){
                                    String cadena="", tex;
                                    try {
                                        FileReader fis = new FileReader(url);
                                        BufferedReader b = new BufferedReader(fis);
                                        while((tex = b.readLine())!=null) {
                                            cadena += tex;
                                        }
                                        envio(thisIp+"/3/369A"+nombre+"369A"+cadena);
                                        b.close();
                                    } catch (FileNotFoundException ex) {
                                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                                    } catch (IOException ex) {
                                        Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                }else{
                                    historial.append("\n    No se encontro el archivo");
                                }
                            }else{
                                historial.append("\n    La estructura del comando es incorrecta");
                            }
                        break;
                        case "binario":
                            try{
                                Socket clientSocket = new Socket(ipt,portt);
                                DataOutputStream mensaje = new DataOutputStream(clientSocket.getOutputStream());
                                File f = new File(url);
                                String nom = f.getName();
                                mensaje.writeUTF(thisIp+"/3/"+nom);
                                PrintStream out = new PrintStream(clientSocket.getOutputStream());
                                FileInputStream file = new FileInputStream(url);
                                byte[] buf = new byte[4096];
                                int len; 
                                while((len=file.read(buf))>0){ 
                                    out.write(buf,0,len); 
                                }
                                historial.append("\n    Se envio el archivo");
                                file.close();
                                mensaje.close();
                                out.close();
                                clientSocket.close();
                            }catch (UnknownHostException e){
                                System.out.println(e);
                                historial.append("\n    Algo salio mal");
                            }catch (IOException e) {
                                System.out.println(e);
                                historial.append("\n    Algo salio mal");
                            }
                        break;
                        default:
                            historial.append("\n    Algo salio mal");
                        break;
                    }
                }else{
                    historial.append("\n    No hay una conexion para ejecutar el comando");
                }
            break;
            case "modo":
                if(conectBan == true){
                    if(partes.length == 1){
                        envio(thisIp+"/4");
                    }else if(partes.length == 2){
                        switch(partes[1]){
                            case "texto":
                                envio(thisIp+"/4/texto");
                            break;
                            case "binario":
                                envio(thisIp+"/4/binario");
                            break;
                            default:
                                historial.append("\n    Opcion no valida");
                        }
                    }else{
                        historial.append("\n    La estructura del comando es incorrecta");
                    }                            
                }else{
                    historial.append("\n    No hay una conexion para ejecutar el comando");
                }
            break;
            case "pw":
                switch (partes.length) {
                    case 1:
                        historial.append("\n    La carpeta de descargas es: "+carpeta);
                    break;
                    case 2:
                        File folder = new File(partes[1]);
                        historial.append("\n    Actualizando directorio a: "+partes[1]);
                        if (!folder.exists()) {
                            folder.mkdir();
                        }
                        if (folder.exists()) {
                            carpeta = partes[1];
                            historial.append("\n    Se actualizo el directorio con exito");
                        }else{
                            historial.append("\n    No se actualizo el directorio");
                        }
                    break;
                    default:
                        historial.append("\n    La estructura del comando es incorrecta");
                        break;
                }
                historial.setCaretPosition(historial.getDocument().getLength());
            break;
            case "descargar":
                if(conectBan == true){
                    if(partes.length == 2){
                        envio(thisIp+"/6/"+partes[1]);
                    }else{
                        historial.append("\n    La estructura del comando es incorrecta");
                    }
                }else{
                    historial.append("\n    No hay una conexion para ejecutar el comando");
                }
            break;
            case "dir":
                if(conectBan == true){
                    if(partes.length == 1){
                        envio(thisIp+"/7");
                    }else{
                        historial.append("\n    La estructura del comando es incorrecta");
                    }
                }else{
                    historial.append("\n    No hay una conexion para ejecutar el comando");
                }
            break;
            case "limpiar":
                historial.setText("");
            break;
            case "ayuda":
                historial.append("\n    conectar ip,puerto - Establece conexion con un servidor");
                historial.append("\n    desconectar - Cierra la conexion con un servidor");
                historial.append("\n    dir - Ver archivos que contiene el servidor");
                historial.append("\n    pw - Ver la direccion donde se guardan las descargas");
                historial.append("\n    pw ubicacion - Cambiar directorio de descargas");
                historial.append("\n    modo - Ver el modo de escritura que esta activado");
                historial.append("\n    modo tipo -Cambiar el modo de escritura a texto o binario"); 
                historial.append("\n    cargar origen - Subir archivo al servidor");
                historial.append("\n    descargar archivo - Descargar archivo del servidor");
                historial.append("\n    limpiar - vaciar pantalla de comandos");
                historial.setCaretPosition(historial.getDocument().getLength());
            break;
            default: 
                historial.append("\n    No se reconose el comando");
            break;
        }
    }
    public class Hilo extends Thread {
        public Hilo() {
            super();
        }
        public void run(){
            try{
                ServerSocket servidor = new ServerSocket(1010);
                while(true){
                    Socket socket = servidor.accept();
                    DataInputStream entrada = new DataInputStream(socket.getInputStream());
                    String mensaje = entrada.readUTF();
                    String[] dat = mensaje.split("/");
                    if(dat[0].equals("6")&& modo.equals("binario")){
                        InputStream out =socket.getInputStream();
                        FileOutputStream file = new FileOutputStream(carpeta+"/"+dat[1]);
                        byte[] buf = new byte[4096];
                        int len; 
                        while((len=out.read(buf))>0){ 
                            file.write(buf,0,len); 
                        } 
                        out.close();
                        file.close();
                        
                    }
                    socket.close();
                    respuesta(mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public void envio(String contenido){
        try{
            Socket socket = new Socket(ipt, portt);
            DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
            mensaje.writeUTF(contenido);
            mensaje.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        send.setEnabled(false);
    }
    public void respuesta(String dato){
        String[] partes = dato.split("/");
        switch(partes[0]){
            case "1":
                modo = partes[1];
            break;
            case "3":
               if (partes[1].equals("1")){
                    historial.append("\n    El archivo se cargo con exito");
               }else if(partes[1].equals("0")){
                   historial.append("\n    No se logro subir el archivo");
               }
            break;
            case "4":
                if(partes[1].equals("binario")){
                    modo = "binario";
                    historial.append("\n    El modo es "+modo);
                }else if(partes[1].equals("texto")){
                    historial.append("\n    El modo es "+modo);
                    modo = "texto";
                }else if (partes[1].equals("1")){
                    if(partes[2].equals("binario")){
                    modo = "binario";
                    }else if(partes[2].equals("texto")){
                        modo = "texto";
                    }
                    historial.append("\n    Modificacion con exito");
                }else{
                    historial.append("\n    No se logro la accion");
                }
                send.setEnabled(true);
            break;
            case "6":
                if(modo.equals("texto")){
                    String[] direccion = dato.split("369A");
                    String nombre = carpeta+"/"+direccion[1];
                    if(direccion[1].equals(" ")){
                        historial.append("\n    No se encontro el archivo");
                    }else if(direccion[1].equals("1")){
                        historial.append("\n    La descarga fue exitosa");
                    }else{
                        if(modo.equals("texto")){
                            File archivo = new File(carpeta);
                            BufferedWriter bw;
                            try{
                                if(archivo.exists()) {
                                    bw = new BufferedWriter(new FileWriter(nombre));
                                    bw.write(direccion[2]);
                                    historial.append("\n    La descarga fue exitosa");
                                } else {
                                    bw = new BufferedWriter(new FileWriter(nombre));
                                    bw.write(direccion[2]);
                                    historial.append("\n    La descarga fue exitosa");
                                }
                                bw.close();
                            } catch (IOException ex) {
                                Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                                historial.append("\n    Fallo la descarga");
                            }
                        }
                    }
                }else{
                    historial.append("\n    La descarga fue exitosa");
                }
            break;
            case "7":
                historial.append("\n    El contenido del servidor es:");
                String[] arch = partes[1].split("<>");
                for(int i=0;i<arch.length;i++){
                     historial.append("\n   "+arch[i]);
                }
            break;
            default: 
                historial.append("\n    Algo salio mal");
        }
        send.setEnabled(true);
        historial.setCaretPosition(historial.getDocument().getLength());
    }
    private void mensajeKeyPressed(java.awt.event.KeyEvent evt){
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
            System.out.println("333333");
        }
    }
}
