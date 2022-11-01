using System;
using System.Linq;

namespace IgorDzierwaEFProducts
{
    class Program
    {
        static void Main(string[] args)
        {
            ProductContext productContext = new ProductContext();

            //a - stworzenie kilku produktów i “sprzedanie” ich na kilku transakcjach.
            Console.WriteLine("Podaj nazwę produktu dla Invoice Number 1: ");
            string prodName1 = Console.ReadLine();
            Product prod1 = new Product { ProductName = prodName1 };

            Console.WriteLine("Podaj nazwę produktu dla Invoice Number 2: ");
            string prodName2 = Console.ReadLine();
            Product prod2 = new Product { ProductName = prodName2 };

            Console.WriteLine("Podaj nazwę produktu dla Invoice Number 2: ");
            string prodName3 = Console.ReadLine();
            Product prod3 = new Product { ProductName = prodName3 };

            Supplier supplier = new Supplier { CompanyName = "Apple", City = "Cupertino" };

            supplier.Products.Add(prod1);
            supplier.Products.Add(prod2);
            supplier.Products.Add(prod3);

            productContext.Suppliers.Add(supplier);
            productContext.Products.Add(prod1);
            productContext.Products.Add(prod2);
            productContext.Products.Add(prod3);
            productContext.SaveChanges();

            Invoice invoice1 = new Invoice { InvoiceNumber = 1, Quantity = 4 };
            Invoice invoice2 = new Invoice { InvoiceNumber = 2, Quantity = 3 };
            productContext.Invoices.Add(invoice1);
            productContext.Invoices.Add(invoice2);

            InvoiceProduct invoiceProduct1 = new InvoiceProduct { Invoice = invoice1, Product = prod1 };
            InvoiceProduct invoiceProduct2 = new InvoiceProduct { Invoice = invoice2, Product = prod2 };
            InvoiceProduct invoiceProduct3 = new InvoiceProduct { Invoice = invoice2, Product = prod3 };

            prod1.InvoiceProducts.Add(invoiceProduct1);
            prod2.InvoiceProducts.Add(invoiceProduct2);
            prod3.InvoiceProducts.Add(invoiceProduct3);
            invoice1.InvoiceProducts.Add(invoiceProduct1);
            invoice2.InvoiceProducts.Add(invoiceProduct2);
            invoice2.InvoiceProducts.Add(invoiceProduct3);
            productContext.SaveChanges();

            //b - Pokaż produkty sprzedane w ramach wybranej faktury/transakcji
            //w tym wypadku z faktury o InvoiceNumber = 2
            Console.WriteLine("Podaj numer faktury, której sprzedane produkty chcesz zobaczyć: ");
            int invoiceNumber = Int32.Parse(Console.ReadLine());

            var query1 = from inv in productContext.invoiceProducts
                         where inv.Invoice.InvoiceNumber == invoiceNumber
                         select inv.Product;

            foreach (var prod in query1)
            {
                Console.WriteLine("Product name: " + prod.ProductName);
            }

            //c - Pokaż faktury w ramach których był sprzedany wybrany produkt
            Console.WriteLine("Podaj nazwę produkty, w celu wyświetlenia faktur, w których był sprzedany: ");
            string soldProdcutName = Console.ReadLine();

            var query2 = from prod in productContext.invoiceProducts
                         where prod.Product.ProductName == soldProdcutName
                         select prod.Invoice;

            foreach (var inv in query2)
            {
                Console.WriteLine("Invoice Number: " + inv.InvoiceNumber);
            }
        }
    }
}
