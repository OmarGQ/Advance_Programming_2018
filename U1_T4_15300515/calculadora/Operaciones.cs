using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace calculadora
{
    interface Operaciones
    {
        string Suma(int a, int b, int c, int d);
        string Resta(int a, int b, int c, int d);
        string Multiplicacion(int a, int b, int c, int d);
        string divicion(int a, int b, int c, int d);
    }
}
