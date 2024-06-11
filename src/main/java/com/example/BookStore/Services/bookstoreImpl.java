package com.example.BookStore.Services;

import com.example.BookStore.Entity.Book;
import com.example.BookStore.Repository.bookstoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class bookstoreImpl implements bookstoreServices{

    @Autowired
    public bookstoreDAO bookstore;

    @Override
    public List<Book> getAllBooks() {
        return this.bookstore.findAll();
    }

    @Override
    public Book getBookById(long bookId) {
        return this.bookstore.getReferenceById(bookId);
    }

    @Override
    public Book addBook(Book book) {
        return this.bookstore.save(book);
    }

    @Override
    public Book updateBook(Book book) {
        return this.bookstore.save(book);
    }

    @Override
    public String deleteBook(long bookId) {
        this.bookstore.deleteById(bookId);
        return "Successfully deleted the book";
    }

    public List<Book> findBookByTitle(String name){
        return this.bookstore.findBookByTitle(name);
    }
    public List<Book> findBookByAuthor(String name){
        return this.bookstore.findBookByAuthor(name);
    }
}
