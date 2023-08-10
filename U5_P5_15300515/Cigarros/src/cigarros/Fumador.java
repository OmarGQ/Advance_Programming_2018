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
public class Fumador implements Runnable{
    private Dealer dealer;
    private int random = 0;
    private boolean tabaco = false;
    private boolean papel = false;
    private boolean cerillos = false;
    private int id;
    
    public Fumador(Dealer dealer, int ide){
        this.dealer=dealer;
        id = ide;
        new Thread(this,"Fumador ").start();
    }
    
    public void run(){
        while(true)
        {
            do{
                random = (int) (Math.random() * 4) + 3;
            }while(random == 4);
            switch(random){
                case 3:
                    tabaco = true;
                    papel = true;
                    cerillos = false;
                break;
                case 5:
                    tabaco = true;
                    papel = false;
                    cerillos = true;
                break;
                case 6:
                    tabaco = false;
                    papel = true;
                    cerillos = true;
                break;
            }
            dealer.comprar(tabaco, papel, cerillos, id);
        }
    }
}
