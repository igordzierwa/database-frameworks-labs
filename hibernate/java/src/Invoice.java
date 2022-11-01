import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int invoiceID;
    private int quantity;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Product> productSet;

    public Invoice() {}

    public Invoice(int quantity) {
        this.quantity = quantity;
        this.productSet = new HashSet<>();
    }

    public void addProduct(Product product) {
        this.productSet.add(product);
    }

    @Override
    public String toString() {
        return "InvoiceID:" + this.invoiceID + " Quantity:"
                + this.quantity + " ProductSet:" + this.productSet;
    }
}

