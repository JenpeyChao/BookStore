package com.example.BookStore.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "books")
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "bookId")
    public long bookId;

    @Column(name = "name")
    public String name;

    @Column(name = "author")
    public String author;
}
