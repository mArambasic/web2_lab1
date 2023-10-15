package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "appUser")
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    private String name;
    private String password;

    @OneToMany(mappedBy = "appUser", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Competition> competitions;
}
