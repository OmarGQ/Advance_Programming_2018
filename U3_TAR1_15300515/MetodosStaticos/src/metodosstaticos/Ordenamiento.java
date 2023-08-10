/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodosstaticos;

/**
 *
 * @author Alumnos
 */
public class Ordenamiento {
    
    int [] arrm = {6,4,7,8,2,3,9,1,5,0};
    int [] arrb = {6,4,7,8,2,3,9,1,5,0};
    int [] arrs = {6,4,7,8,2,3,9,1,5,0};
    int [] arri = {6,4,7,8,2,3,9,1,5,0};
    
    public static int[] burbuja(int []e){
        int tempy;
	for(int x=0;x<e.length;x++)
	{
            for(int y=0;y<e.length-1;y++)
            {
                if(e[y]>e[y+1])
		{
                    tempy=e[y];
                    e[y]=e[y+1];
                    e[y+1]=tempy;
		}
            }
	}
        return e;
    }
    
    public static int[] seleccion(int []e){
        int pos, swap;
	for(int i=0;i<e.length;i++)
	{
		pos=i;
		for(int d=i+1;d<e.length;d++)
		{
			if(e[pos]>e[d])
			{
				pos=d;
			}
		}
		if(pos!=i)
		{
			swap=e[i];
			e[i]=e[pos];
			e[pos]=swap;
		}
	}
        return e;
    }
    
    public static int[] insercion(int []e){
        int aux, p;
	for(int i=1;i<e.length;i++)
	{
		aux=e[i];
		p=i-1;
		while(aux<e[p]&&p>=1)
		{
			e[p+1]=e[p];
			p=p-1;
		}
		if(e[p]<=aux)
		{
			e[p+1]=aux;
		}
		else
		{
			e[p+1]=e[p];
			e[p]=aux;
		}
	}
        return e;
    }
    
    public static int[] burbujam(int e[]){
        for(int i=e.length-1;i>0;i--){
            for(int j=0;j<i;j++){
                if(e[j]>e[j+1]){
                    int temp=e[j];
                    e[j]=e[j+1];
                    e[j+1]=temp;
                }
            }
        }
        return e;
    }
    
    public Ordenamiento(){
        System.out.println("Valores a ordenar");
        for (int i = 0; i < arrb.length; i++) {
            System.out.print(arrb[i]);
        }
        arrb = Ordenamiento.burbuja(arrb);
        System.out.println("\nOrdenado por burbuja");
        for (int i = 0; i < arrb.length; i++) {
            System.out.print(arrb[i]);
        }
        arrs = Ordenamiento.seleccion(arrs);
        System.out.println("\nOrdenado por seleccion");
        for (int i = 0; i < arrs.length; i++) {
            System.out.print(arrs[i]);
        }
        arri = Ordenamiento.insercion(arri);
        System.out.println("\nOrdenado por insercion");
        for (int i = 0; i < arri.length; i++) {
            System.out.print(arri[i]);
        }
        arrm = Ordenamiento.burbujam(arrm);
        System.out.println("\nOrdenado por burbuja mejorada");
        for (int i = 0; i < arrm.length; i++) {
            System.out.print(arrm[i]);
        }
    }
}