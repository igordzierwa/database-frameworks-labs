using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace IgorDzierwaEFProducts
{
    public class Product
    {
        public Product()
        {
            InvoiceProducts = new Collection<InvoiceProduct>();
        }
        public int ProductID { get; set; }
        public string ProductName { get; set; }
        public int UnitsOnStock { get; set; }
        public Supplier Supplier { get; set; }
        public ICollection<InvoiceProduct> InvoiceProducts { get; set; }
    }
}


