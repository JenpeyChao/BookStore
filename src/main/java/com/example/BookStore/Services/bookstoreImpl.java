package com.example.BookStore.Services;

import com.example.BookStore.Entity.Book;
import com.example.BookStore.Repository.bookstoreDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        Optional<Book> check = this.bookstore.findById(bookId);
        Book task = null;
        if (check.isPresent()){
            task = check.get();
        }else{
            throw new RuntimeException(bookId+ " Not Found");
        }

        return task;
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

    public List<Book> findBookByAuthor(String author){
        return this.bookstore.findBookByAuthor(author);
    }

    public List<Book> sortByName(){
        return this.bookstore.sortByName();
    }

    public List<Book> sortByAuthor(){
        return this.bookstore.sortByAuthor();
    }
}
