package com.library.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.library.entity.Author;
import com.library.exception.ResourceNotFoundException;
import com.library.repository.AuthorRepository;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    // constructor injection
    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    // CREATE
    @Transactional
    public Author addAuthor(Author author) {
        return authorRepository.save(author);
    }

    // READ by ID
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Author not found with id: " + id));
    }

    // READ all
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    // UPDATE
    @Transactional
    public Author updateAuthor(Long id, Author updatedAuthor) {
        // first check it exists — throws exception if not found
        Author existing = getAuthorById(id);

        // update fields
        existing.setName(updatedAuthor.getName());
        existing.setBiography(updatedAuthor.getBiography());

        // save() on existing managed entity = UPDATE
        return authorRepository.save(existing);
    }

    // DELETE
    @Transactional
    public void deleteAuthor(Long id) {
        // check exists first
        getAuthorById(id);
        authorRepository.deleteById(id);
    }
}