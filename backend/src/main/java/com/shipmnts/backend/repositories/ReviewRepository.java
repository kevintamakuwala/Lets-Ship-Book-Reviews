package com.shipmnts.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shipmnts.backend.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, String> {
    
}
