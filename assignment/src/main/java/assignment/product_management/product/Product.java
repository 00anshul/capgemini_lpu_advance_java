package assignment.product_management.product;

import assignment.product_management.category.Category;
import jakarta.persistence.*;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productId;

    @Column(nullable = false)
    private String productName;

    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int id) { this.productId = id; }

    public String getProductName() { return productName; }
    public void setProductName(String name) { this.productName = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Category getCategory() { return category; }
    public void setCategory(Category category) { this.category = category; }
}