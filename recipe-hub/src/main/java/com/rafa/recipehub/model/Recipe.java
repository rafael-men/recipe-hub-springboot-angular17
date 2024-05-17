package com.rafa.recipehub.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String title;

    @ManyToOne
    private User user;
    private String image;
    private String description;
    private Boolean bagitarian;
    private LocalDateTime createdAt;
    private List<Long> likes = new ArrayList<>();
}
