import javax.persistence.Entity;

@Entity
public class Customer extends Company {
    private double discount;

    public Customer() {}

    public Customer(String companyName, String street, String city,
                    String zipCode, double discount) {
        super(companyName, street, city, zipCode);
        this.discount = discount;
    }

    @Override
    public String toString() {
        return super.toString() + this.discount;
    }
}

