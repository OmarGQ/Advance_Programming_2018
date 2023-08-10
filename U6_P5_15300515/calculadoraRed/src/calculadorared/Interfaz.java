/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadorared;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author Alumnos
 */
public class Interfaz extends JFrame{
    
    private JTextField j1, j2, j3, ip;
    private JLabel l1, l2, l3, l4;
    private JButton sum, res, mul, div;
    private String thisIp, ipt, op1, op2;
    Hilo hilo;
    
    public Interfaz(){
        super();
        j1=new JTextField();
        j1.setLocation(200,30);
        j1.setSize(100,30);
        j2=new JTextField();
        j2.setLocation(200,80);
        j2.setSize(100,30);
        j3=new JTextField();
        j3.setLocation(200,130);
        j3.setSize(100,30);
        j3.setEditable(false);
        l1=new JLabel("Operador 1");
        l1.setLocation(120,30);
        l1.setSize(70,30);
        l2=new JLabel("Operador 2");
        l2.setLocation(120,80);
        l2.setSize(70,30);
        l3=new JLabel("Resultado");
        l3.setLocation(120,130);
        l3.setSize(70,30);
        l4=new JLabel("Ip servidor");
        l4.setLocation(120,220);
        l4.setSize(70,30);
        sum=new JButton("Suma");
        sum.setLocation(30,180);
        sum.setSize(100,30);
        res=new JButton("Resta");
        res.setLocation(140,180);
        res.setSize(100,30);
        mul=new JButton("Multiplicacion");
        mul.setLocation(250,180);
        mul.setSize(100,30);
        div=new JButton("Divicion");
        div.setLocation(360,180);
        div.setSize(100,30);
        ip=new JTextField();
        ip.setLocation(200,220);
        ip.setSize(100,30);
        hilo = new Hilo();
        hilo.start();
        
        try //obtener direccion Ip del equipo
        {
            thisIp = InetAddress.getLocalHost().getHostAddress();
        }
        catch(UnknownHostException e) 
        {
            e.printStackTrace();
        }
        
        sum.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ipt = ip.getText();
                String[] octetos = ipt.split("\\.");
                if(octetos.length == 4){
                    op1 = j1.getText();
                    op2 = j2.getText();
                    try{
                        Socket socket = new Socket(ipt, 999);
                        DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
                        mensaje.writeUTF(thisIp+"/1/"+op1+"/"+op2+"");
                        mensaje.close();
                        socket.close();
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                    }
                }
            }
        });
        res.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ipt = ip.getText();
                String[] octetos = ipt.split("\\.");
                if(octetos.length == 4){
                    op1 = j1.getText();
                    op2 = j2.getText();
                    try{
                        Socket socket = new Socket(ipt, 999);
                        DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
                        mensaje.writeUTF(thisIp+"/2/"+op1+"/"+op2+"");
                        mensaje.close();
                        socket.close();
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                    }
                }
            }
        });
        mul.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ipt = ip.getText();
                String[] octetos = ipt.split("\\.");
                if(octetos.length == 4){
                    op1 = j1.getText();
                    op2 = j2.getText();
                    try{
                        Socket socket = new Socket(ipt, 999);
                        DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
                        mensaje.writeUTF(thisIp+"/3/"+op1+"/"+op2+"");
                        mensaje.close();
                        socket.close();
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                    }
                }
            }
        });
        div.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae) {
                ipt = ip.getText();
                String[] octetos = ipt.split("\\.");
                if(octetos.length == 4){
                    op1 = j1.getText();
                    op2 = j2.getText();
                    try{
                        Socket socket = new Socket(ipt, 999);
                        DataOutputStream mensaje = new DataOutputStream(socket.getOutputStream());
                        mensaje.writeUTF(thisIp+"/4/"+op1+"/"+op2+"");
                        mensaje.close();
                        socket.close();
                    } catch (IOException ex) {
                         System.out.println(ex.getMessage());
                    }
                }
            }
        });
        
        setLayout(null);
        
        add(j1);
        add(j2);
        add(j3);
        add(l1);
        add(l2);
        add(l3);
        add(l4);
        add(sum);
        add(res);
        add(mul);
        add(div);
        add(ip);
        setSize(500, 300);//se define el tamaño de pantalla
        Toolkit tk;
        tk=Toolkit.getDefaultToolkit ();
        Dimension tamPant=tk.getScreenSize();
        setLocation((tamPant.width-getSize().width)/2,(tamPant.height-getSize().height)/2);
        setTitle("Calculadora en red");//se define el nombre de la ventana
        setDefaultCloseOperation(EXIT_ON_CLOSE);//se define lo cuando se ciierre la vantana se termine el proceso
        setResizable(false);//no permite que el usuario modifique el tamaño de la ventana
        setVisible(true);//para que sea visible (siempre va al final
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
                    socket.close();
                    j3.setText(mensaje);
                }
            } catch (IOException ex) {
                Logger.getLogger(Interfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
