package com.BookStore.BookStore.dao;


import com.BookStore.BookStore.entity.Book;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class bookStoreDaoImpl implements bookStoreDao {

    EntityManager entityManager;

    @Autowired
    public bookStoreDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    @Transactional
    public void createBook(Book book) {
        entityManager.persist(book);
    }

    @Override
    public Book findById(Integer id) {
        return entityManager.find(Book.class, id);
    }

    @Override
    public List<Book> findByAuthor(String name) {
        TypedQuery<Book> query = entityManager.createQuery("FROM Book WHERE authorName LIKE :search", Book.class);
        query.setParameter("search", "%" + name + "%");
        return query.getResultList();
    }

    @Override
    public List<Book> findAllBooks() {
        TypedQuery<Book> query = entityManager.createQuery("From Book", Book.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        entityManager.merge(book);
    }

    @Override
    @Transactional
    public void deleteBook(Integer id) {
        Book book = entityManager.find(Book.class, id);
        entityManager.remove(book);
    }
}
