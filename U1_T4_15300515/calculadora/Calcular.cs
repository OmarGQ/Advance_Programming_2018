using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace calculadora
{
    class Calcular:Operaciones
    {
        public  string sel, res, n1, d1, n2, d2;
        public int op, u1, m1, u2, m2;
        public delegate string del(int a, int b, int c, int d);
        public Calcular()
        {
            del man;
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
                    man=Suma;
                    break;
                case 2:
                    man=Resta;
                    break;
                case 3:
                    man=Multiplicacion;
                    break;
                case 4:
                    man=divicion;
                    break;
                default:
                    man = null;
                    Console.Write("Opcion no valida");
                    break;
            }
            res= man(u1, m1, u2, m2);
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
