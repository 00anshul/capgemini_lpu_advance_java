package assignment.product_management.product;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import assignment.product_management.category.Category;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findByCategory(Category category);
    List<Product> findByProductNameContaining(String keyword);
    List<Product> findByPriceLessThan(double price);
}