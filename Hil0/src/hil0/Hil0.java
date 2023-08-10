/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hil0;

import static java.lang.Thread.yield;

/**
 *
 * @author Alumnos
 */
/*
public class Hil0 extends Thread{
    private int contador;
    private static int nhilo = 0;
    public Hil0(){
        super("Hilo "+(++nhilo));
        contador=0;
    }
    @Override
    public void run(){
        while(contador<10){
            System.out.println(getId()+":"+(contador++));
            try{
                yield();
            }catch(Exception e){
                
            }
        }
    }
    public static void main(String orgs[]){
        for(int i =0; i<10; i++){
            Thread hilo=new Hil0();
            hilo.start();
        }
    }
}
*/
public class Hil0 implements Runnable{
    private String id;
    private int contador;
    private static int nhilo = 0;
    public Hil0(){
        id = "Hilo "+(++nhilo);
        contador = 0;
    }
    @Override
    public String toString(){
        return id;
    }
    @Override
    public void run(){
        while(contador<10){
            System.out.println(this +":"+contador);
            contador++;
            try{
                Thread.sleep(1000);
            }catch(InterruptedException e){
                
            }
        }
    }
    public static void main(String args[]){
        for(int i=0; i<10; i++){
            Thread hilo = new Thread(new Hil0());
            hilo.start();
        }
    }
}