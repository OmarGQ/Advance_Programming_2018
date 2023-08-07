using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace calculadora
{
    class Calcular
    {
        public  string sel, res, n1, d1, n2, d2;
        public int op, u1, m1, u2, m2;
        public Calcular()
        {
            Console.WriteLine("Que quiere hacer:\n1-Sumar\n2-Restar\n3-Multiplicar\n4-Dividir");
            sel = Console.ReadLine();
            Console.WriteLine("Ingrese el numerador de la primera fraccion");
            n1 = Console.ReadLine();
            Console.WriteLine("Ingrese el denminador de la primera fraccion");
            d1 = Console.ReadLine();
            Console.WriteLine("Ingrese el numerador de la segunda fraccion");
            n2 = Console.ReadLine();
            Console.WriteLine("Ingrese el denminador de la segunda fraccion");
            d2 = Console.ReadLine();
            try
            {
                op = int.Parse(sel);
                u1 = int.Parse(n1);
                m1 = int.Parse(d1);
                u2 = int.Parse(n2);
                m2 = int.Parse(d2);
            }
            catch (FormatException)
            {
                op = 1;
            }
            switch (op)
            {
                case 1:
                    res=Suma(u1, m1, u2, m2);
                    break;
                case 2:
                    res=Resta(u1, m1, u2, m2);
                    break;
                case 3:
                    res=Multiplicacion(u1, m1, u2, m2);
                    break;
                case 4:
                    res=divicion(u1, m1, u2, m2);
                    break;
                default:
                    Console.Write("Opcion no valida");
                    break;
            }
            Console.Write(res);
            Console.ReadKey();
        }

        public string divicion(int a, int b, int c, int d)
        {
            int result, mul;
            mul = (b * c);
            result = (a * d);
            return "" + result + "¬" + mul + "";
        }

        public string Multiplicacion(int a, int b, int c, int d)
        {
            int result, mul;
            mul = (b * d);
            result = (a * c);
            return "" + result + "¬" + mul + "";
        }

        public string Resta(int a, int b, int c, int d)
        {
            int result, mul;
            if (b!=d)
            {
                mul = (b * d);
                a = (d * a);
                c = (b * c);
            }
            else
            {
                mul = b;
            }
            result = (a - c);
            return "" + result + "¬" + mul + "";
        }

        public string Suma(int a, int b, int c, int d)
        {
            int result, mul;
            if (b!=d)
            {
                mul = (b * d);
                a = (d * a);
                c = (b * c);
            }
            else
            {
                mul = b;
            }
            result = (a + c);
            return "" + result + "¬" + mul + "";
        }
    }
}
