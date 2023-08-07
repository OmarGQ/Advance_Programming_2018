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
        public delegate void del<TArg0, TArg1, TArg2, TArg3, TResult>(TArg0 a, TArg1 b, TArg2 c, TArg3 d);
        public Calcular()
        {
            //del man;
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
                    del<int, int, int, int, int> suma = (int a, int b, int c, int d) =>
                    {
                        int result, mul;
                        if (b != d)
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
                        Console.Write("" + result + "¬" + mul + "");
                    };
                    suma(u1, m1, u2, m2);
                    break;
                case 2:
                    del<int, int, int, int, int> Resta = (int a, int b, int c, int d) =>
                    {
                        int result, mul;
                        if (b != d)
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
                        Console.Write("" + result + "¬" + mul + "");
                    };
                    Resta(u1, m1, u2, m2);
                    break;
                case 3:
                    del<int, int, int, int, int> Multiplicacion = (int a, int b, int c, int d) =>
                    {
                        int result, mul;
                        mul = (b * d);
                        result = (a * c);
                        Console.Write("" + result + "¬" + mul + "");
                    };
                    Multiplicacion(u1, m1, u2, m2);
                    break;
                case 4:
                    del<int, int, int, int, int> Divicion = (int a, int b, int c, int d)=>
                    {
                        int result, mul;
                        mul = (b * c);
                        result = (a * d);
                        Console.Write("" + result + "¬" + mul + "");
                    };
                    Divicion(u1, m1, u2, m2);
                    break;
                default:
                    Console.Write("Opcion no valida");
                    break;
            }
            Console.ReadKey();
        }
    }
}
