using System;
using System.Linq;

namespace IgorDzierwaEFProducts
{
    class Program
    {
        static void Main(string[] args)
        {
            ProductContext productContext = new ProductContext();

            //a - stworzenie kilku produktow
            Console.WriteLine("Podaj nazwę produktu: ");
            string prodName1 = Console.ReadLine();
            Product prod1 = new Product { ProductName = prodName1 };

            Console.WriteLine("Podaj nazwę produktu: ");
            string prodName2 = Console.ReadLine();
            Product prod2 = new Product { ProductName = prodName2 };


            Console.WriteLine("Podaj nazwę produktu: ");
            string prodName3 = Console.ReadLine();
            Product prod3 = new Product { ProductName = prodName3 };

            //b - dodaj produkty do nowo stworzonego dostawcy
            Supplier supplier = new Supplier
            {
                CompanyName = "Apple",
                City = "Cupertino"
       
            };

            supplier.Products.Add(prod1);
            supplier.Products.Add(prod2);
            supplier.Products.Add(prod3);

            productContext.Suppliers.Add(supplier);
            productContext.Products.Add(prod1);
            productContext.Products.Add(prod2);
            productContext.Products.Add(prod3);

            productContext.SaveChanges();

            var query = from supp in productContext.Suppliers
                        select supp;


            foreach (var supp in query)
            {
                Console.WriteLine("Company name: " + supp.CompanyName);

                foreach (var prod in supp.Products)
                {
                    Console.WriteLine("Product name: " + prod.ProductName);
                }
            }
        }
    }
}
