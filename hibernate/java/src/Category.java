import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int categoryID;
    private String name;
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Product> productList;

    public Category() {}

    public Category(String name) {
        this.name = name;
        this.productList = new LinkedList<>();
    }

    public void addProduct(Product product) {
        this.productList.add(product);
    }

    @Override
    public String toString() {
        return "CategoryName:" + this.name;
    }

    public String getName() {
        return name;
    }
}

