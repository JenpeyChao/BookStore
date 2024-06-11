package com.example.BookStore.Controller;

import com.example.BookStore.Entity.Book;
import com.example.BookStore.Repository.bookstoreDAO;
import com.example.BookStore.Services.bookstoreImpl;
import com.example.BookStore.Services.bookstoreServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class bookstoreController {
    @Autowired
    public bookstoreImpl bookstoreImpl;

    @GetMapping("/")
    public String home(){
        return "<h1> Welcome to the bookstore </h1>";
    }

    @GetMapping("/bookstore")
    public List<Book> getAllBooks() {
        return this.bookstoreImpl.getAllBooks();
    }

    @GetMapping("/bookstore/name")
    public List<Book> findBookByTitle(@RequestParam String name){
        return this.bookstoreImpl.findBookByTitle(name);
    }
    @GetMapping("/bookstore/author")
    public List<Book> findBookByAuthor(@RequestParam String name){
        return this.bookstoreImpl.findBookByAuthor(name);
    }
    @GetMapping("/bookstore/{bookId}")
    public Book getBookById(@PathVariable long bookId) {
        return this.bookstoreImpl.getBookById(bookId);
    }

    @PostMapping("/bookstore")
    public Book addBook(@RequestBody Book book) {
        return this.bookstoreImpl.addBook(book);
    }

    @PutMapping("/bookstore")
    public Book updateBook(@RequestBody Book book) {
        return this.bookstoreImpl.updateBook(book);
    }

    @DeleteMapping("/bookstore/{bookId}")
    public String deleteBook(@PathVariable long bookId) {
        return this.bookstoreImpl.deleteBook(bookId);
    }

}
