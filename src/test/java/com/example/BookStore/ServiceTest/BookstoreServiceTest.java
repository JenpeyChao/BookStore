package com.example.BookStore.ServiceTest;

import com.example.BookStore.Entity.Book;
import com.example.BookStore.Repository.bookstoreDAO;
import com.example.BookStore.Services.bookstoreImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;


public class BookstoreServiceTest {


    @Mock
    public bookstoreDAO bookstore;

    @InjectMocks
    private bookstoreImpl bookstoreImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllBooks(){
        Book book1 = new Book("Title1","Author1",19.99);
        Book book2 = new Book("Title2","Author2",10.99);
        List<Book> books = Arrays.asList(book1,book2);
        // Mock the behavior of the repository
        when(bookstore.findAll()).thenReturn(books);

        // Act
        List<Book> result = bookstoreImpl.getAllBooks();

        // Assert
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getName());
        assertEquals("Title2", result.get(1).getName());

    }
    @Test
    public void testGetBookByIdFound(){
        Book book1 = new Book("Title1","Author1",19.99);
        when(bookstore.findById(1L)).thenReturn(Optional.of(book1));
        Book book = bookstoreImpl.getBookById(1L);
        assertEquals(book,book1);

    }
    @Test
    public void testGetBookByIdNotFound(){
        when(bookstore.findById(1L)).thenReturn(Optional.empty());


        Exception exception = assertThrows(RuntimeException.class, () -> {
            bookstoreImpl.getBookById(1L);
        });

        assertEquals("1 Not Found", exception.getMessage());

    }

    @Test
    public void testAddBook(){
        Book book1 = new Book("Title1","Author1",19.99);
        when(bookstore.save(book1)).thenReturn(book1);
        Book book = bookstoreImpl.addBook(book1);
        assertEquals(book,book1);
    }

    @Test
    public void testUpdateBook(){
        Book book1 = new Book("Title1","Author1",19.99);
        when(bookstore.save(book1)).thenReturn(book1);
        Book book = bookstoreImpl.updateBook(book1);
        assertEquals(book,book1);
    }

    @Test
    public void testDeleteBook(){
        doNothing().when(bookstore).deleteById(1L);
        String result = bookstoreImpl.deleteBook(1L);

        assertEquals("Successfully deleted the book", result);

        verify(bookstore).deleteById(1L);
    }

    @Test
    public void testFindBookByTitle(){
        Book book1 = new Book("Title1","Author1",19.99);
        Book book2 = new Book("Title1","Author2",10.99);
        List<Book> books = Arrays.asList(book1,book2);
        // Mock the behavior of the repository
        when(bookstore.findBookByTitle("Title1")).thenReturn(books);

        // Act
        List<Book> result = bookstoreImpl.findBookByTitle("Title1");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getName());
        assertEquals("Author1", result.get(0).getAuthor());
        assertEquals(19.99, result.get(0).getPrice());
        assertEquals("Title1", result.get(1).getName());
        assertEquals("Author2", result.get(1).getAuthor());
        assertEquals(10.99, result.get(1).getPrice());

    }

    @Test
    public void testFindBookByAuthor(){
        Book book1 = new Book("Title1","Author1",19.99);
        Book book2 = new Book("Title2","Author1",10.99);
        List<Book> books = Arrays.asList(book1,book2);
        // Mock the behavior of the repository
        when(bookstore.findBookByTitle("Author1")).thenReturn(books);

        // Act
        List<Book> result = bookstoreImpl.findBookByTitle("Author1");

        // Assert
        assertEquals(2, result.size());
        assertEquals("Title1", result.get(0).getName());
        assertEquals("Author1", result.get(0).getAuthor());
        assertEquals(19.99, result.get(0).getPrice());
        assertEquals("Title2", result.get(1).getName());
        assertEquals("Author1", result.get(1).getAuthor());
        assertEquals(10.99, result.get(1).getPrice());

    }

    @Test
    public void testSortByName(){
        Book book1 = new Book("A Title", "Author1", 19.99);
        Book book2 = new Book("B Title", "Author2", 29.99);
        List<Book> sortedBooks = Arrays.asList(book1, book2);

        // Mock the behavior of the repository to return a sorted list of books
        when(bookstore.sortByName()).thenReturn(sortedBooks);

        // Act
        List<Book> result = bookstoreImpl.sortByName();

        // Assert
        assertEquals(2, result.size());
        assertEquals("A Title", result.get(0).getName());
        assertEquals("Author1", result.get(0).getAuthor());
        assertEquals(19.99, result.get(0).getPrice());
        assertEquals("B Title", result.get(1).getName());
        assertEquals("Author2", result.get(1).getAuthor());
        assertEquals(29.99, result.get(1).getPrice());
    }

    @Test
    public void testSortByAuthor(){
        Book book1 = new Book("A Title", "A Author1", 19.99);
        Book book2 = new Book("A Title", "B Author2", 29.99);
        List<Book> sortedBooks = Arrays.asList(book1, book2);

        // Mock the behavior of the repository to return a sorted list of books
        when(bookstore.sortByAuthor()).thenReturn(sortedBooks);

        // Act
        List<Book> result = bookstoreImpl.sortByAuthor();

        // Assert
        assertEquals(2, result.size());
        assertEquals("A Title", result.get(0).getName());
        assertEquals("A Author1", result.get(0).getAuthor());
        assertEquals(19.99, result.get(0).getPrice());
        assertEquals("A Title", result.get(1).getName());
        assertEquals("B Author2", result.get(1).getAuthor());
        assertEquals(29.99, result.get(1).getPrice());
    }
}
