using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace numeros_reales
{
    class Numeros_imaginarios
    {
        public int real { get; set; }
        public Numeros_imaginarios(int r)
        {
            real = r;
        }
        public void mostrar()
        {
            Console.Write(+real+"i");
        }
    }
}
