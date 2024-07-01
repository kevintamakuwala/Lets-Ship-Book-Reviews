package com.shipmnts.backend.services;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shipmnts.backend.entities.Book;
import com.shipmnts.backend.repositories.BookRepository;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    String url = "https://openlibrary.org/trending/daily";


    // get paginatted books
    public List<Book> scrapBooks(int page, int size) {

        List<Book> books = new ArrayList<>();
        int offset = (page - 1) * size;
        // searchResultItem is class for a book
        // details > resultTitle > bookTitle > results to get book title
        // details > bookauthor > results to get book author
        // details> resultPublisher > publishedYear classes to get publisher year
        // details> resultPublisher > a tag to get editions

        try {
            Document document = Jsoup.connect(url).get();

            Elements searchResultItems = document.select(".searchResultItem");
            int booksCount = 0;
            for (Element item : searchResultItems) {
                if (booksCount >= offset && booksCount < offset + size) {
                    Book book = new Book();

                    book.setId(java.util.UUID.randomUUID().toString());

                    // Extracting the book title
                    Element title = item.select(".details .resultTitle .bookTitle .results").first();
                    if (title != null) {
                        book.setTitle(title.text());
                    }

                    // Extracting the book author
                    Element author = item.select(".details .bookauthor .results").first();
                    if (author != null) {
                        book.setAuthor(author.text());
                    }

                    // Extracting the publisher year
                    Element publisherYear = item.select(".details .resultPublisher .publishedYear").first();
                    if (publisherYear != null) {
                        book.setPublishedYear(publisherYear.text());
                    }

                    // Extracting the editions
                    Element editionsElement = item.select(".details .resultPublisher a").first();
                    if (editionsElement != null) {
                        book.setEditions(editionsElement.text());
                    }

                    books.add(book);
                    booksCount++;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public void saveBooks(List<Book> books) {
        bookRepository.saveAll(books);
    }

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }
}
