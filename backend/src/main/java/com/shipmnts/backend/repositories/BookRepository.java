package com.shipmnts.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shipmnts.backend.entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    public Book findBookByTitle(String title);
    
}
