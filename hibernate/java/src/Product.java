import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int productID;
    private String productName;
    private int unitsInStock;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier_FK")
    private Supplier supplier;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "category_FK")
    private Category category;
    @ManyToMany(mappedBy = "productSet", cascade = CascadeType.PERSIST)
    private Set<Invoice> invoiceSet;

    public Product() {}

    public Product(String productName, int unitsInStock) {
        this.productName = productName;
        this.unitsInStock = unitsInStock;
        this.invoiceSet = new HashSet<>();
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void addInvoice(Invoice invoice) {
        this.invoiceSet.add(invoice);
    }

    @Override
    public String toString() {
        return "ProductID:" + this.productID + " ProductName:"
                + this.productName + " UnitsInStock:" + this.unitsInStock
                + " Category:" + this.category + " Supplier:" + this.supplier;
    }
}



