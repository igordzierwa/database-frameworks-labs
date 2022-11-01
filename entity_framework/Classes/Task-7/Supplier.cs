using System;
using System.Collections.Generic;
using System.Collections.ObjectModel;
using System.ComponentModel.DataAnnotations.Schema;

namespace IgorDzierwaEFProducts
{
    public class Supplier
    {
        public Supplier()
        {
            Products = new Collection<Product>();
        }
        public int SupplierID { get; set; }
        public string CompanyName { get; set; }
        public string Street { get; set; }
        public string City { get; set; }
        public ICollection<Product> Products { get; set; }
    }
}
