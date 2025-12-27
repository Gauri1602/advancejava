package com.example.library.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.library.entity.Book;
import com.example.library.repository.BookRepository;

@Service
public class BookService {

    private final BookRepository repo;

    public BookService(BookRepository repo) {
        this.repo = repo;
    }

    public void saveBook(Book book) {
        repo.save(book);
    }

    public List<Book> getAllBooks() {
        return repo.findAll();
    }

    public Book getBookById(int id) {
        Optional<Book> opt = repo.findById(id);
        return opt.orElse(null);
    }
}
