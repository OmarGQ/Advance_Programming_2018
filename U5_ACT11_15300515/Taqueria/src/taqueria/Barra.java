/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package taqueria;

/**
 *
 * @author Alumnos
 */
public class Barra {
    private int n;
    private boolean ocupado;
    
    public Barra(){
        n=0;
        ocupado=false;
    }
    public synchronized void poner(){
        while(ocupado){
            try{
                wait();
            }catch(InterruptedException e){
                
            }
        }
        ocupado= true;
        n++;
        System.out.println("Sirviendo tacos");
        ocupado=false;
        notify();
    }
    public synchronized void quitar(){
        while(ocupado && n<1){
            try{
                wait();
            }catch(InterruptedException e){
                
            }
        }
        ocupado=true;
        System.out.println("Tomando tacos");
        ocupado=false;
        notify();
    }
}
