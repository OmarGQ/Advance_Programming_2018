/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ktoons;

import java.util.Scanner;

/**
 *
 * @author danie
 */
public class proceso {
    int n, izq=0, der=0, tot;
    public proceso(){
        String cad;
        boolean val=false;
        
        do{
            if (val==true) {
                System.out.println("deben ser almenos 3 digitos");
            }
            System.out.println("Bienvenido a la recuperacion de coigo de KTunes\nIngrese su codigo y coloque * en el digito desconocido");
            Scanner teclado = new Scanner(System.in);
            cad = teclado.nextLine();
            n = cad.length();
            val = true;
        }while(n<3);
        sumLados(cad);
        System.out.println(izq);
        System.out.println(der);
        tot=sumMayor(izq, der);
        System.out.println(tot);
        tot=sumPlus(tot);
        System.out.println("El digito faltante es: "+tot);
    }
    public void sumLados(String cade)
        {
            String r, l;
            char v;
            String[] parts = cade.split("\\*");
            r = parts[1];
            l = parts[0];
            for (int i = 0; i < r.length(); i++) {
                v = r.charAt(i);
                der += (int)r.toCharArray()[i];
            }
            for (int i = 0; i < l.length(); i++) {
                v = l.charAt(i);
                izq += (int)l.toCharArray()[i];
            }
        }
    public int sumMayor(int izq, int der)
        {
            int h=0;
            if (izq > der)
            {
                while (izq > 0)
                {
                    h = h + (izq % 10);
                    izq = izq / 10;
                }
            }
            else
            {
                while (der > 0)
                {
                    h = h + (der % 10);
                    der = der / 10;
                }
            }
            return h;
        }
    public int sumPlus(int h)
        {
            int y = 0;
            if (h >= 10)
            {
                while (h > 0)
                {
                    y = y + (h % 10);
                    h = h / 10;
                }
            }
            return y;
        }
}
