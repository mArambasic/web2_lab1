package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "competition")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "competition_id", nullable = false)
    private Long competitionId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "competition", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Round> rounds;

    @OneToMany(mappedBy = "competition", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Scoring> scoring;

    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    @JsonBackReference
    private AppUser appUser;
}
