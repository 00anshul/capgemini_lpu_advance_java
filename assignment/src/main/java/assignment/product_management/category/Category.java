package assignment.product_management.category;

import java.util.List;
import assignment.product_management.product.Product;
import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int categoryId;

    @Column(unique = true, nullable = false)
    private String categoryName;

    private String description;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    @JsonIgnore  // ← stops Jackson from trying to load products list
    private List<Product> products;

    // Getters and Setters
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int id) { this.categoryId = id; }

    public String getCategoryName() { return categoryName; }
    public void setCategoryName(String name) { this.categoryName = name; }

    public String getDescription() { return description; }
    public void setDescription(String desc) { this.description = desc; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) { this.products = products; }
}