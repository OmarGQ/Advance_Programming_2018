using System;
using System.Collections.Generic;
using System.Text;

namespace ConsoleApp1
{
    public class Class1
    {
        private int edad;
        public int Edad
        {
            get
            {
                return edad;
            }
            set
            {
                if (value >= 0)
                    edad = value;
            }
        }
        public Class1()
        {
            edad = -1;
        }
        public Class1(int e)
        {
            edad = e;
        }
        public void saluda()
        {
            Console.WriteLine("Hola tienes: " + Edad + " años");
            Console.ReadKey();
        }
    }
}
