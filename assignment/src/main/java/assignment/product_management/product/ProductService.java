package assignment.product_management.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assignment.product_management.category.Category;
import assignment.product_management.category.CategoryRepository;
import assignment.product_management.exception.CategoryNotFoundException;
import assignment.product_management.exception.ProductNotFoundException;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private CategoryRepository categoryRepo;  // ← service can use multiple repos!

    // ADD PRODUCT TO A CATEGORY
    public Product addProduct(int categoryId, Product p) {
        // validate price
        if (p.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive!");
        }
        // validate name
        if (p.getProductName() == null || p.getProductName().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty!");
        }
        // check category exists
        Category category = categoryRepo.findById(categoryId)
                           .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        
        p.setCategory(category);  // link product to category
        return productRepo.save(p);
    }

    // GET ALL PRODUCTS
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    // GET BY ID
    public Product getProductById(int id) {
        return productRepo.findById(id)
               .orElseThrow(() -> new ProductNotFoundException(id));
    }

    // GET ALL PRODUCTS BY CATEGORY
    public List<Product> getProductsByCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId)
                           .orElseThrow(() -> new CategoryNotFoundException(categoryId));
        return productRepo.findByCategory(category);
    }

    // SEARCH BY NAME
    public List<Product> searchByName(String keyword) {
        return productRepo.findByProductNameContaining(keyword);
    }

    // UPDATE
    public Product updateProduct(int id, Product updated) {
        Product existing = productRepo.findById(id)
                          .orElseThrow(() -> new ProductNotFoundException(id));
        if (updated.getPrice() <= 0) {
            throw new IllegalArgumentException("Price must be positive!");
        }
        existing.setProductName(updated.getProductName());
        existing.setPrice(updated.getPrice());
        return productRepo.save(existing);
    }

    // DELETE
    public String deleteProduct(int id) {
        productRepo.findById(id)
                   .orElseThrow(() -> new ProductNotFoundException(id));
        productRepo.deleteById(id);
        return "Product " + id + " deleted successfully";
    }
}