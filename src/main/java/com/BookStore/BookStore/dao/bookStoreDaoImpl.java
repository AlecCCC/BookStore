package com.BookStore.BookStore.dao;


import com.BookStore.BookStore.entity.Book;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
