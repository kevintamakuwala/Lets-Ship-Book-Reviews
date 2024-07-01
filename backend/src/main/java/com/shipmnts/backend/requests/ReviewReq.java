package com.shipmnts.backend.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ReviewReq {
    private String bookId;
    private String comment;
    private int rating;
}
