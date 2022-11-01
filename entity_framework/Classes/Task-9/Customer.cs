using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace IgorDzierwaEFProducts
{
    [Table("Customers")]
    public class Customer: Company
    {
        public int Discount { get; set; }
    }
}

