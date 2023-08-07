using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace actividad_2
{
    class Menu
    {
        public int sel, num;
        public int[] ar= { 9, 8, 7, 4, 3, 2, 6 };
        public string cad;
        public Menu()
        {
            Console.Write("Que quiere hacer\n");
            Console.Write("1-Mayoor, Menor o igual a 0\n");
            Console.Write("2-Imprimir el arreglo\n");
            cad = Console.ReadLine();
            try
            {
                sel = int.Parse(cad);
            }
            catch (FormatException)
            {
                sel = 0;
            }
            switch (sel)
            {
                case 1:
                    Op1();
                    break;
                case 2:
                    Op2();
                    break;
                default:
                    break;
            }
        }
        public void Op1()
        {
            Console.WriteLine("Ingrese un numero");
            cad = Console.ReadLine();
            try
            {
                num = int.Parse(cad);
            }
            catch (FormatException)
            {
                num = 0;
            }
            if (num<0)
            {
                Console.WriteLine("El numero es menor a 0");
            }
            else if (num==0)
            {
                Console.WriteLine("El numero es igual a 0");
            }
            else if (num>0)
            {
                Console.WriteLine("El numero es mayor a 0");
            }
            Console.ReadKey();
        }
        public void Op2()
        {
            foreach (int i in ar)
            {
                Console.Write($"{i} ");
            }
            Console.WriteLine();
            Console.ReadKey();
        }
    }
}
