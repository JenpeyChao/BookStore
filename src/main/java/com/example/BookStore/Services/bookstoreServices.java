package com.example.BookStore.Services;

import com.example.BookStore.Entity.Book;

import java.util.List;

public interface bookstoreServices {
    List<Book> getAllBooks();
    Book getBookById(long bookId);
    Book addBook(Book book);
    Book updateBook(Book book);
    String deleteBook(long bookId);

}
