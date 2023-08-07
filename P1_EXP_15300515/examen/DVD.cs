using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace examen
{
    class DVD : Peliculas, Lectora
    {
        public delegate void del();
        private String pais, dat;
        public DVD(string nom, string cat, string pro, string cla, string pa, int du)
        {
            del imprimirDuracion;
            Nombre = nom;
            Categoria = cat;
            Productora = pro;
            clasificcacion = cla;
            duracion = du;
            pais = pa;
            Console.Write("Disculpe si no lo hice como queria pero no logre interpretar bien la practica e hice lo que pude y entendi\n");
            dat = leerCadena();
            Console.Write(dat);
            imprimirDuracion = Dur;
            imprimirDuracion();
            Console.ReadKey();
        }

        public void Dur()
        {
            Console.Write("\nDuracion: " + duracion + "min");
        }

        public string leerCadena()
        {
            return "Nombre: "+Nombre+"\nClasificacion: "+clasificcacion+"\nProductora: "+Productora+"\nCategoria: "+Categoria+"\nPais: "+pais+"";

        }

        public int leerEntero()
        {
            throw new NotImplementedException();
        }

        public float leeReal()
        {
            throw new NotImplementedException();
        }
    }
}
