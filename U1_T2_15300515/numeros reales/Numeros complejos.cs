using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace numeros_reales
{
    class Numeros_complejos:Numeros_imaginarios
    {
        private int realc=4;
        public Numeros_complejos(int i, int c):base(i)
        {
            realc = c;
        }
        public void mostrarc()
        {
            Console.WriteLine();
            Console.Write(realc);
            if (real>=0)
            {
                Console.Write("+");
            }
            mostrar();
        }
        public Numeros_complejos suma(Numeros_complejos s)
        {
            Numeros_complejos c = new Numeros_complejos(real + s.real, realc + s.realc);
            return c;
        }
        public Numeros_complejos resta(Numeros_complejos r) 
        {
            Numeros_complejos c = new Numeros_complejos(real - r.real, realc - r.realc);
            return c;
        }
    }
}
