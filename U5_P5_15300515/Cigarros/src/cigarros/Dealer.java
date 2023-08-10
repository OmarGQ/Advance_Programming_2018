/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cigarros;

/**
 *
 * @author danie
 */
public class Dealer {
    private boolean ocupado;
    
    public Dealer(){
        ocupado=false;
    }
    
    public synchronized void comprar(boolean tabaco, boolean papel, boolean cerillos, int id){
        while(ocupado){
            try{
                wait();
            }catch(InterruptedException e){
                
            }
        }
        ocupado= true;
        if(!tabaco){
            System.out.println("F"+id+" tiene papel y cerillos");
            System.out.println("F"+id+" Comprando Tabaco al Dealer");
        }else if(!papel){
            System.out.println("F"+id+" tiene tabaco y cerillos");
            System.out.println("F"+id+" Comprando papel al Dealer");
        }else{
            System.out.println("F"+id+" tiene tabaco y papel");
            System.out.println("F"+id+" Comprando cerillos al Dealer");
        }
        System.out.println("F"+id+" Fumando");
        ocupado=false;
        try
        {
            Thread.sleep(5);
        }catch(InterruptedException e){}
        notify();
    }
}
