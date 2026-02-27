package assignment.product_management.category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    // POST http://localhost:8080/categories
    @PostMapping
    public Category addCategory(@RequestBody Category c) {
        return categoryService.addCategory(c);
    }

    // GET http://localhost:8080/categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    // GET http://localhost:8080/categories/1
    @GetMapping("/{id}")
    public Category getCategoryById(@PathVariable int id) {
        return categoryService.getCategoryById(id);
    }

    // PUT http://localhost:8080/categories/1
    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable int id, @RequestBody Category c) {
        return categoryService.updateCategory(id, c);
    }

    // DELETE http://localhost:8080/categories/1
    @DeleteMapping("/{id}")
    public String deleteCategory(@PathVariable int id) {
        return categoryService.deleteCategory(id);
    }

    // GET http://localhost:8080/categories/paged?page=0&size=5
    @GetMapping("/paged")
    public Page<Category> getCategory(@RequestParam int page, @RequestParam int size) {
        return categoryService.getCategory(page, size);
 
    }
}