using System;
using System.Linq;

namespace IgorDzierwaEFProducts
{
    class Program
    {
        static void Main(string[] args)
        {
            ProductContext productContext = new ProductContext();

            Console.WriteLine("Podaj nazwę produktu: ");
            string prodName = Console.ReadLine();

            //a - stworzenie nowego dostawcy
            Supplier supplier = new Supplier { CompanyName = "Apple" };
            productContext.Suppliers.Add(supplier);
            productContext.SaveChanges();
            Supplier supplier1 = new Supplier { CompanyName = "Google" };
            productContext.Suppliers.Add(supplier1);
            productContext.SaveChanges();

            Product productDefault = new Product { ProductName = "DefaultProduct" };
            productDefault.SupplierID = supplier.SupplierID;
            productContext.Add(productDefault);
            productContext.SaveChanges();

            Product product = new Product { ProductName = prodName };
            product.SupplierID = supplier.SupplierID;
            productContext.Products.Add(product);
            productContext.SaveChanges();  

            //b - Znajdź poprzednio wprowadzony produkt i ustaw jego dostawce na właśnie dodanego
            var recentProduct = (from prod in productContext.Products
                                        select prod).First();
            recentProduct.SupplierID = supplier1.SupplierID;
            productContext.SaveChanges();

            Console.WriteLine("Poniżej lista produktów zarejestrowanych w naszej bazie danych");

            var query = from prod in productContext.Products
                        select prod;


            foreach (var prod in query)
            {
                Console.WriteLine("Name: " + prod.ProductName + " Supplier: " + prod.Supplier.CompanyName);
            }
        }
    }
}
