using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace calificaciones
{
    class Calificaciones
    {
        private int[] arr;
        private int num, prom;
        private string cad;
        public Calificaciones()
        {
            Console.Write("Dame un  numero");
            cad = Console.ReadLine();
            try
            {
                num = int.Parse(cad);
            }
            catch (FormatException)
            {
                num = 0;
            }
            Console.ReadKey();
            arr = new int[num];
        }
        public void calcular()
        {
            prom = 0;
            for (int i = 0; i < num; i++)
            {
                Console.Write("Dame una calificacion");
                cad = Console.ReadLine();
                try
                {
                    arr[i] = int.Parse(cad);
                }
                catch (FormatException)
                {
                    arr[i] = 0;
                }
                prom += arr[i];
            }
            prom /= num;
            Console.WriteLine(prom);
        }
    }
}
