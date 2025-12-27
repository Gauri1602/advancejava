package com.example.library.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.library.entity.Book;
import com.example.library.service.BookService;

@Controller
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String home() {
        return "add-book";
    }

    @PostMapping("/saveBook")
    public String saveBook(Book book) {
        service.saveBook(book);
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String listBooks(Model model) {
        model.addAttribute("books", service.getAllBooks());
        return "book-list";
    }

    @GetMapping("/search")
    public String searchPage() {
        return "search-book";
    }

    @GetMapping("/searchBook")
    public String searchBook(@RequestParam int bookId, Model model) {
        Book book = service.getBookById(bookId);
        model.addAttribute("book", book);
        return "search-book";
    }
}
