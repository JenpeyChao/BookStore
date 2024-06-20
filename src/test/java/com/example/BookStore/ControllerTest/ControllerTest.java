package com.example.BookStore.ControllerTest;

import com.example.BookStore.Controller.bookstoreController;
import com.example.BookStore.Entity.Book;
import com.example.BookStore.Services.bookstoreImpl;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(bookstoreController.class)
public class ControllerTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private bookstoreImpl bookstoreImpl;


    @Test
    public void testHome() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(status().isOk())
                .andExpect(content().string("<h1> Welcome to the bookstore </h1>"));
    }

    @Test
    public void testAllBooks() throws Exception {
        Book book1 = new Book("Title1","Author1",19.99);
        Book book2 = new Book("Title2","Author2",10.99);
        List<Book> books = Arrays.asList(book1,book2);

        Mockito.when(bookstoreImpl.getAllBooks()).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/bookstore"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[0].name",is("Title1")))
                .andExpect(jsonPath("$[0].author",is("Author1")))
                .andExpect(jsonPath("$[0].price",is(19.99)))
                .andExpect(jsonPath("$[1].name",is("Title2")))
                .andExpect(jsonPath("$[1].author",is("Author2")))
                .andExpect(jsonPath("$[1].price",is(10.99)));
    }

    @Test
    public void testFindBookByTitle() throws Exception {
        Book book1 = new Book("Title1","Author1",19.99);
        List<Book> books = List.of(book1);
        Mockito.when(bookstoreImpl.findBookByTitle("Title1")).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/name")
                        .param("name","Title1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].name",is("Title1")))
                .andExpect(jsonPath("$[0].author",is("Author1")))
                .andExpect(jsonPath("$[0].price",is(19.99)))
                ;
    }

    @Test
    public void testFindBookByAuthor() throws Exception {
        Book book1 = new Book("Title1","Author1",19.99);
        List<Book> books = List.of(book1);
        Mockito.when(bookstoreImpl.findBookByAuthor("Author1")).thenReturn(books);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/author")
                        .param("author","Author1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].name",is("Title1")))
                .andExpect(jsonPath("$[0].author",is("Author1")))
                .andExpect(jsonPath("$[0].price",is(19.99)))
        ;
    }

    @Test
    public void testFindBookById() throws Exception {
        Book book1 = new Book("Title1","Author1",19.99);
        Mockito.when(bookstoreImpl.getBookById(0)).thenReturn(book1);

        mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/{bookId}",0))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",is("Title1")))
                .andExpect(jsonPath("$.author",is("Author1")))
                .andExpect(jsonPath("$.price",is(19.99)))
        ;
    }
    @Test
    public void testSortByTitle() throws Exception {
        Book book1 = new Book("bas","Author1",19.99);
        Book book2 = new Book("aaa","Author2",10.99);
        List<Book> books = Arrays.asList(book1,book2);

        Mockito.when(bookstoreImpl.sortByName()).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/sortTitle"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[1].name",is("aaa")))
                .andExpect(jsonPath("$[1].author",is("Author2")))
                .andExpect(jsonPath("$[1].price",is(10.99)))
                .andExpect(jsonPath("$[0].name",is("bas")))
                .andExpect(jsonPath("$[0].author",is("Author1")))
                .andExpect(jsonPath("$[0].price",is(19.99)));
    }

    @Test
    public void testSortByAuthor() throws Exception {
        Book book1 = new Book("Title1","bbb",19.99);
        Book book2 = new Book("Title2","aaa",10.99);
        List<Book> books = Arrays.asList(book1,book2);

        Mockito.when(bookstoreImpl.sortByAuthor()).thenReturn(books);
        mockMvc.perform(MockMvcRequestBuilders.get("/bookstore/sortAuthor"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)))
                .andExpect(jsonPath("$[1].name",is("Title2")))
                .andExpect(jsonPath("$[1].author",is("aaa")))
                .andExpect(jsonPath("$[1].price",is(10.99)))
                .andExpect(jsonPath("$[0].name",is("Title1")))
                .andExpect(jsonPath("$[0].author",is("bbb")))
                .andExpect(jsonPath("$[0].price",is(19.99)));
    }

    @Test
    public void testAddBook() throws Exception {
        Book book1 = new Book("Title","Author",6);
        Mockito.when(bookstoreImpl.addBook(book1)).thenReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders.post("/bookstore")
                        .content(objectMapper.writeValueAsString(book1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name",is("Title")))
//                .andExpect(jsonPath("$.author",is("Author")))
//                .andExpect(jsonPath("$.price",is(6)));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book1 = new Book("Title","Author",6);
        Mockito.when(bookstoreImpl.updateBook(book1)).thenReturn(book1);
        mockMvc.perform(MockMvcRequestBuilders.put("/bookstore")
                        .content(objectMapper.writeValueAsString(book1))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.name",is("Title")))
//                .andExpect(jsonPath("$.author",is("Author")))
//                .andExpect(jsonPath("$.price",is(6)));
    }

    @Test
    public void testDeleteBook() throws Exception {
        long book = 0;
        Mockito.when(bookstoreImpl.deleteBook(book)).thenReturn("Successfully deleted the book");
        mockMvc.perform(MockMvcRequestBuilders.delete("/bookstore/{bookId}",book))
                .andExpect(status().isOk())
                .andExpect(content().string("Successfully deleted the book"));
    }
}

