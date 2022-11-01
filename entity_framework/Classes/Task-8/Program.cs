using System;
using System.Linq;

namespace IgorDzierwaEFProducts
{
    class Program
    {
        static void Main(string[] args)
        {
            ProductContext productContext = new ProductContext();
            Customer customer1 = new Customer
            {
                CompanyName = "Customer Company 1",
                Street = "Customer Street 1",
                City = "Customer Street 1",
                Zipcode = "Customer Zipcode 1",
                Discount = 100
            };
            Customer customer2 = new Customer
            {
                CompanyName = "Customer Company 2",
                Street = "Customer Street 2",
                City = "Customer Street 2",
                Zipcode = "Customer Zipcode 2",
                Discount = 200
            };
            Supplier supplier1 = new Supplier
            {
                CompanyName = "Supplier Company 1",
                Street = "Supplier Street 1",
                City = "Supplier Street 1",
                Zipcode = "Supplier Zipcode 1",
                BankAccountNumber = "11114015601081110181488249"
            };
            Supplier supplier2 = new Supplier
            {
                CompanyName = "Supplier Company 2",
                Street = "Supplier Street 2",
                City = "Supplier Street 2",
                Zipcode = "Supplier Zipcode 2",
                BankAccountNumber = "22224015601081110181488249"
            };

            productContext.Companies.Add(customer1);
            productContext.Companies.Add(customer2);
            productContext.Companies.Add(supplier1);
            productContext.Companies.Add(supplier2);
            productContext.SaveChanges();

            var query = from company in productContext.Companies
                        select company;

            foreach (var company in query)
            {
                Console.WriteLine("Company name: " + company.CompanyName);
            }
        }
    }
}
