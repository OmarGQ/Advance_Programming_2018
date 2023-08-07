using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace lambda
{
    class Program
    {
        private delegate double del<i>(i value);

        public static void Main(string[] args)
        {
            int val;
            string vol;
            del<double> exponente = delegate (double value) { return Math.Pow(value, 2); };
            del<int> raiz = delegate (int value) { return Math.Sqrt(value); };
            Console.WriteLine("ingrese un numero");
            vol = Console.ReadLine();
            try
            {
                val = int.Parse(vol);
            }
            catch (FormatException)
            {
                val = 0;
            }
            Console.WriteLine("exponente");
            Console.WriteLine(exponente(val));
            Console.WriteLine("raiz cuadrada");
            Console.Write(raiz(val));
            Console.ReadKey();
        }

    }
}
