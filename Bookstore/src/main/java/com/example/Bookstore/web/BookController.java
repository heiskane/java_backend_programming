package com.example.Bookstore.web;

import com.example.Bookstore.domain.BookRepository;
import com.example.Bookstore.domain.Book;
import com.example.Bookstore.domain.Category;
import com.example.Bookstore.domain.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Optional;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @RequestMapping("/login")
    public String login() { return "login"; }

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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @GetMapping("/books")
    public @ResponseBody List<Book> bookListREST() {
        return (List<Book>) bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public @ResponseBody
    Optional<Book> findBookREST(@PathVariable("id") Long BookId) {
        return bookRepository.findById(BookId);
    }

}
