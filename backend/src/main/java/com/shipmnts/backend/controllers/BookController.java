package com.shipmnts.backend.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.shipmnts.backend.entities.Book;
import com.shipmnts.backend.responses.BookRes;
import com.shipmnts.backend.services.BookService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    // GET /books?page=1&size=10
    @GetMapping("/books")
    public ResponseEntity<List<BookRes>> getBooks(int page, int size) {

        List<Book> books = bookService.getBooks(page, size);

        // convert book into bookRes
        List<BookRes> bookRes = convertBookToBookRes(books);

        return new ResponseEntity<>(bookRes,
                bookRes.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK);
    }

    // mapper methods
    private List<BookRes> convertBookToBookRes(List<Book> books) {

        return books.stream()
                .map(book -> new BookRes(book.getId(), book.getTitle(), book.getAuthor(), book.getPublishedYear(),
                        book.getEditions()))
                .collect(Collectors.toList());

    }

}
