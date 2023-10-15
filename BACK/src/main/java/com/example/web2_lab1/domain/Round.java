package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity(name = "round")
public class Round {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "round_id", nullable = false)
    private Long roundId;

    @Column(name = "roundNumber", nullable = false)
    private Integer roundNumber;

    @OneToMany(mappedBy = "round", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Match> matches;

    @ManyToOne
    @JoinColumn(name="competition_id", nullable=false)
    @JsonBackReference
    private Competition competition;
}
