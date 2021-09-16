package com.example.Bookstore.web;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping(value = {"/", "/booklist"})
    public String books(Model model) {

        model.addAttribute("books", bookRepository.findAll());

        return "booklist";
    }

    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addbook";
    }

    @RequestMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long bookId, Model model) {

        Optional<Book> book = bookRepository.findById(bookId);
        List<Category> categories = (List<Category>) categoryRepository.findAll();

        model.addAttribute("book", book);
        model.addAttribute("categories", categories);
        return "addbook";
    }

    @RequestMapping("/save")
    public String save(Book book) {
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

}
