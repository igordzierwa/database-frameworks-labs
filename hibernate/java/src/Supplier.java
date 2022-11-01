import javax.persistence.*;
import java.util.*;

@Entity
public class Supplier extends Company {
   private String bankAccountNumber;
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "supplier_FK")
    private Set<Product> productSet;

    public Supplier() {}

    public Supplier(String companyName, String street, String city,
                    String zipCode, String bankAccountNumber) {
        super(companyName, street, city, zipCode);
        this.bankAccountNumber = bankAccountNumber;
        this.productSet = new LinkedHashSet<>();
    }

    public void addProduct(Product product) {
        this.productSet.add(product);
    }

    @Override
    public String toString() {
        return super.toString() + this.bankAccountNumber;
    }
}





