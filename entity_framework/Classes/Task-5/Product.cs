using System;
using System.ComponentModel.DataAnnotations.Schema;

namespace IgorDzierwaEFProducts
{
    public class Product
    {
        public int ProductID { get; set; }
        public string ProductName { get; set; }
        public int UnitsOnStock { get; set; }
    }
}


