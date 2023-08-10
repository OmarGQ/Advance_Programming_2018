/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practica1;

import static java.lang.System.out;
import java.util.Scanner;

/**
 *
 * @author Alumnos
 */
public class matris {
    public matris(){
        int col=0, fil=0, vmayor=0, vmenor=0;
        String val;
        int [][] mat; 
        int [] scol;
        int [] sfil;
        boolean flag;
        
        System.out.println("Matriz");
        System.out.println("Ingrese la cantedad de columnas y filas");
        Scanner teclado = new Scanner(System.in);
        flag = false;
        try {
            val = teclado.nextLine();
            String[] parts = val.split(" ");
            col = Integer.parseInt(parts[0]);
            fil = Integer.parseInt(parts[1]);
        }
        catch(Exception e) {
          System.out.println("No ingreso los dos valores");
        }
        mat = new int [col][fil];
        scol = new int [col];
        sfil = new int [fil];
        for (int i = 0; i < fil; i++) {
            for (int j = 0; j < col; j++) {
                System.out.println("Ingrese un valor"+j+""+i);
                teclado = new Scanner(System.in);
                val = teclado.nextLine();
                mat[j][i] = Integer.parseInt(val);
                scol[j] += mat[j][i];
                sfil[i] += mat[j][i];
                if (flag == false) {
                    vmenor = mat[j][i];
                    flag = true;
                }
                if (mat[j][i] > vmayor) {
                    vmayor = mat[j][i];
                }
                if (mat[j][i] < vmenor) {
                    vmenor = mat[j][i];
                }
            }
        }
        for (int i = 0; i < col; i++) {
            System.out.println("suma de columna "+i+" "+scol[i]+"");
        }
        for (int i = 0; i < fil; i++) {
            System.out.println("suma de fila "+i+" "+sfil[i]+"");
        }
        System.out.println("valor mayor "+vmayor+"");
        System.out.println("valor menor "+vmenor+"");
    }
}
