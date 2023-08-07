using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace examen
{
    class Peliculas
    {
        private String nombre, categoria, productora;
        public int duracion, numCaracteres, ast;
        public String clasificcacion;
        public String Nombre
        {
            get
            {
                return nombre;
            }
            set
            {
                numCaracteres += value.Length;
                if (numCaracteres<10)
                {
                    ast = 10-numCaracteres;
                    for (int n = 0; n < ast; n++)
                    {
                        value = ""+value+"*";
                    }
                } 
                nombre = value;
            }
        }
        public String Categoria
        {
            get
            {
                return categoria;
            }
            set
            {
                categoria = value;
            }
        }
        public String Productora
        {
            get
            {
                return productora;
            }
            set
            {
                productora = value;
            }
        }
    }
}
