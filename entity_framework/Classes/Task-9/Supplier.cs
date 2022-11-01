using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace IgorDzierwaEFProducts
{
    [Table("Suppliers")]
    public class Supplier: Company
    {
        public Supplier()
        {
            Products = new Collection<Product>();
        }

        public string BankAccountNumber { get; set; }
        public ICollection<Product> Products { get; set; }
    }
}

