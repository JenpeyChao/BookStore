package com.example.BookStore.Repository;

import com.example.BookStore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface bookstoreDAO extends JpaRepository<Book, Long> {

    @Query("SELECT b FROM Book b WHERE b.name = :name")
    List<Book> findBookByTitle(@Param("name") String name);

    @Query("SELECT b FROM Book b WHERE b.author = :author")
    List<Book> findBookByAuthor(@Param("author") String author);

    @Query(value = "select * from books b order by b.name", nativeQuery = true)
    List<Book> sortByName();

    @Query(value = "select * from books b order by b.author", nativeQuery = true)
    List<Book> sortByAuthor();
}
