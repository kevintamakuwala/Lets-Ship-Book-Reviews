package com.shipmnts.backend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name = "Test")
public class Hello {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
}
