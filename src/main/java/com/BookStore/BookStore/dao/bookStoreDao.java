package com.BookStore.BookStore.dao;
import com.BookStore.BookStore.entity.Book;

import java.util.List;

public interface bookStoreDao {

    void createBook(Book book);

    Book findById(Integer id);

    List<Book> findByAuthor(String name);

    List<Book> findAllBooks();

    void updateBook(Book book);




}
