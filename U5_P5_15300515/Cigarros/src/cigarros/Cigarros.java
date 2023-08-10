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
public class Cigarros {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Dealer dea = new Dealer();
        Fumador f1, f2, f3;
        f1 = new Fumador(dea, 1);
        f2 = new Fumador(dea, 2);
        f3 = new Fumador(dea, 3);
    }
    
}
