package com.shipmnts.backend.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.shipmnts.backend.repositories.BookRepository;
import com.shipmnts.backend.services.BookService;

@Component
public class ScraperScheduler {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookService bookService;

    // Schedule the method to run once every day at 2 AM
    @Scheduled(cron = "0 0 2 * * ?")

    // Schedule the method to run every 7 seconds
    // @Scheduled(fixedRate = 7000)
    public void scheduleScraping() {

        // Delete all existing data
        bookRepository.deleteAll();

        if (bookRepository.count() == 0) {
            // scrp all data aviable
            bookRepository.saveAll(bookService.scrapBooks(1, Integer.MAX_VALUE));
            System.out.println("Scheduling done");
        }
    }
}
