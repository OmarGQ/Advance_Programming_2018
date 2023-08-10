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
public class Taqueria {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Barra barra = new Barra();
        Taquero t1, t2, t3;
        Comensal c1, c2, c3;
        t1 = new Taquero(barra);
        t2 = new Taquero(barra);
        t3= new Taquero(barra);
        c1 = new Comensal(barra);
        c2 = new Comensal(barra);
        c3 = new Comensal(barra);
    }
    
}
