package com.example.Bookstore;

import com.example.Bookstore.domain.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class RepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createCategoryTest() {
        Category category = new Category("Test Category");
        categoryRepository.save(category);

        assertThat(category.getId()).isNotNull();
        assertThat(categoryRepository.findByName("Test Category")).isNotNull();

        categoryRepository.delete(category);
        assertThat(categoryRepository.findByName("Test Category")).hasSize(0);
    }

    @Test
    public void createBookTest() {
        // Tests do a rollback on the database so have to re-create the category
        categoryRepository.save(new Category("Test Category"));
        Book book = new Book(
                "Jpa Test",
                "Test author",
                1999,
                "asd",
                10F,
                categoryRepository.findByName("Test Category").get(0));

        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();

        List<Book> books = bookRepository.findByTitle("Jpa Test");
        assertThat(books).hasSize(1);

        bookRepository.delete(book);

        assertThat(bookRepository.findByTitle("Jpa Test")).hasSize(0);
    }

    @Test
    public void createUser() {
        User user = new User(
                "Potato",
                "$2a$12$czcinxQSa0dThdy6wb5z9u1XR5OehmioM7DrK1jYAx5r9cPO.XCcG",
                "ADMIN"
        );
        userRepository.save(user);

        assertThat(userRepository.findByUsername("Potato")).isNotNull();

        userRepository.delete(user);

        assertThat(userRepository.findByUsername("Potato")).isNull();
    }

}
