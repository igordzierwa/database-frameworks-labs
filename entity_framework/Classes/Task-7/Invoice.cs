using System;
using System.Collections;
using System.Collections.Generic;
using System.Collections.ObjectModel;

namespace IgorDzierwaEFProducts
{
    public class Invoice
    {
        public Invoice()
        {
            InvoiceProducts = new Collection<InvoiceProduct>();
        }

        public int InvoiceID { get; set; }
        public int InvoiceNumber { get; set; }
        public int Quantity { get; set; }
        public ICollection<InvoiceProduct> InvoiceProducts { get; set; }
    }
}

