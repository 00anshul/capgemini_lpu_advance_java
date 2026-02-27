package assignment.product_management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CategoryNotFoundException extends RuntimeException {
    
    public CategoryNotFoundException(int id) {
        super("Category with id " + id + " not found!");
    }
    
    public CategoryNotFoundException(String name) {
        super("Category with name " + name + " not found!");
    }
}