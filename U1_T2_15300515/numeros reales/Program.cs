using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace numeros_reales
{
    class Program
    {
        static void Main(string[] args)
        {
            Numeros_complejos a, b, c;
            a = new Numeros_complejos(4, 3);
            b = new Numeros_complejos(3, 7);
            c = a.suma(b);
            c.mostrarc();
            c = a.resta(b);
            c.mostrarc();
            Console.ReadKey();
        }
    }
}
