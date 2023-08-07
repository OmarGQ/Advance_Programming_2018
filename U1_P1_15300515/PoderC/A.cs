using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace PoderC
{
    class A:Procesos
    {
        public string cad;
        public int n, izqui, derec, tot;
        public bool val=false;
        public A()
        {
            do
            {
                if (val==true)
                {
                    Console.WriteLine("deben ser almenos 3 digitos");
                }
                Console.WriteLine("Bienvenido a la recuperacion de coigo de KTunes\nIngrese su codigo y coloque ? en el digito desconocido");
                cad = Console.ReadLine();
                n = cad.Length;
                val = true;
            } while (n<3);
            sumLados(cad);
            Console.WriteLine(izqui);
            Console.WriteLine(derec);
            tot=sumMayor(izqui, derec);
            Console.WriteLine(tot);
            tot=sumPlus(tot);
            Console.WriteLine("El digito faltante es: "+tot+"");
            Console.ReadKey();
        }

        public void sumLados(string cade)
        {
            bool ban = false;
            for (int i = 0; i < cade.Length; i++)
            {
                if (ban == true)
                {
                    derec += System.Convert.ToInt32(cade[i]);
                }
                if ((System.Convert.ToInt32(cade[i]) > 47 && System.Convert.ToInt32(cade[i]) < 58) && ban == false)
                {
                    izqui += System.Convert.ToInt32(cade[i]);
                }
                else
                {
                    ban = true;
                }
            }
        }

        public int sumMayor(int izq, int der)
        {
            int h=0;
            if (izq > der)
            {
                while (izq > 0)
                {
                    h = h + (izq % 10);
                    izq = izq / 10;
                }
            }
            else
            {
                while (der > 0)
                {
                    h = h + (der % 10);
                    der = der / 10;
                }
            }
            return h;
        }

        public int sumPlus(int h)
        {
            int y = 0;
            if (h >= 10)
            {
                while (h > 0)
                {
                    y = y + (h % 10);
                    h = h / 10;
                }
            }
            return y;
        }
    }
}
