package assignment.product_management.category;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import assignment.product_management.exception.CategoryNotFoundException;

@Service  // ← tells Spring this is a service bean
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepo;

    // ADD CATEGORY
    public Category addCategory(Category c) {
        // business rule — no duplicate category names
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
}