using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace calificaciones
{
    class Program
    {
        static void Main(string[] args)
        {
            Calificaciones c;
            c = new Calificaciones();
            c.calcular();
            Console.ReadKey();
        }
    }
}
