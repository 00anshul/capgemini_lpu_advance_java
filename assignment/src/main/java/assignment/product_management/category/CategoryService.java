package assignment.product_management.category;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import assignment.product_management.exception.CategoryNotFoundException;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepo;

    // ADD CATEGORY
    public Category addCategory(Category c) {
        if (categoryRepo.existsByCategoryName(c.getCategoryName())) {
            throw new IllegalArgumentException("Category name already exists: " + c.getCategoryName());
        }
        return categoryRepo.save(c);
    }

    // GET ALL
    public List<Category> getAllCategories() {
        return categoryRepo.findAll();
    }

    // GET BY ID
    public Category getCategoryById(int id) {
        return categoryRepo.findById(id)
               .orElseThrow(() -> new CategoryNotFoundException(id));
    }

    // UPDATE
    public Category updateCategory(int id, Category updated) {
        Category existing = categoryRepo.findById(id)
                           .orElseThrow(() -> new CategoryNotFoundException(id));
        existing.setCategoryName(updated.getCategoryName());
        existing.setDescription(updated.getDescription());
        return categoryRepo.save(existing);
    }

    // DELETE
    public String deleteCategory(int id) {
        categoryRepo.findById(id)
                    .orElseThrow(() -> new CategoryNotFoundException(id));
        categoryRepo.deleteById(id);
        return "Category " + id + " deleted successfully";
    }

 
    public Page<Category> getCategory(int page, int size) {
        return categoryRepo.findAll( PageRequest.of(page, size, Sort.by("categoryId").descending()));
    }
}