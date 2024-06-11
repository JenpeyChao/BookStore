package com.example.BookStore.Repository;

import com.example.BookStore.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface bookstoreDAO extends JpaRepository<Book, Long> {
}
