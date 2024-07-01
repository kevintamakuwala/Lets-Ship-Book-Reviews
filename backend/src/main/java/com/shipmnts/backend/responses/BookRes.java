package com.shipmnts.backend.responses;

import java.util.List;

import com.shipmnts.backend.entities.Review;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookRes {
    private String id;

    private String title;
    private String author;
    private String publishedYear;
    private String editions;
}
