package assignment.product_management.product;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST http://localhost:8080/products/category/1
    @PostMapping("/category/{categoryId}")
    public Product addProduct(@PathVariable int categoryId, @RequestBody Product p) {
        return productService.addProduct(categoryId, p);
    }

    // GET http://localhost:8080/products
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    // GET http://localhost:8080/products/1
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable int id) {
        return productService.getProductById(id);
    }

    // GET http://localhost:8080/products/category/1
    @GetMapping("/category/{categoryId}")
    public List<Product> getProductsByCategory(@PathVariable int categoryId) {
        return productService.getProductsByCategory(categoryId);
    }

    // GET http://localhost:8080/products/search/phone
    @GetMapping("/search/{keyword}")
    public List<Product> searchProducts(@PathVariable String keyword) {
        return productService.searchByName(keyword);
    }

    // PUT http://localhost:8080/products/1
    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable int id, @RequestBody Product p) {
        return productService.updateProduct(id, p);
    }

    // DELETE http://localhost:8080/products/1
    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable int id) {
        return productService.deleteProduct(id);
    }
}