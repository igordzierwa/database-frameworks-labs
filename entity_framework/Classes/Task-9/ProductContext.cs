﻿using System;
using Microsoft.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore.Sqlite;

namespace IgorDzierwaEFProducts
{
    public class ProductContext : DbContext
    {
        public DbSet<Product> Products { get; set; }

        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        {
            base.OnConfiguring(optionsBuilder);
            optionsBuilder.UseSqlite("Datasource=ProductsDatabase");
        }

        public DbSet<Invoice> Invoices { get; set; }

        public DbSet<InvoiceProduct> invoiceProducts { get; set; }

        public DbSet<Company> Companies { get; set; }

        //konfiguracja klucza kompozytowego
        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<InvoiceProduct>().HasKey(sc => new { sc.ProductID, sc.InvoiceID });
            modelBuilder.Entity<Supplier>().ToTable("Suppliers");
            modelBuilder.Entity<Customer>().ToTable("Customers");
        }
    }
}


