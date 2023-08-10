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
public class Comensal implements Runnable{
    private Barra barra;
    
    public Comensal(Barra barra){
        this.barra=barra;
        new Thread(this,"Comensal").start();
    }
    public void run(){
        int i =0;
        while(true)
        {
            i++;
            barra.quitar();
        }
    }
}