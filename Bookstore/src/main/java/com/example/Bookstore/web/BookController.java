package com.example.Bookstore.web;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository repository;

    @RequestMapping(value = {"/", "/booklist"})
    public String books(Model model) {

        model.addAttribute("books", repository.findAll());

        return "booklist";
    }

    @RequestMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        return "addbook";
    }

    @RequestMapping("/edit/{id}")
    public String updateBook(@PathVariable("id") Long bookId, Model model) {

        Optional<Book> book = repository.findById(bookId);

        model.addAttribute("book", book);
        return "addbook";
    }

    @RequestMapping("/save")
    public String save(Book book) {
        repository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        repository.deleteById(bookId);
        return "redirect:/booklist";
    }

}
