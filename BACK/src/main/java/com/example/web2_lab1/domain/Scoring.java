package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "scoring")
public class Scoring {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "scoring_id", nullable = false)
    private Long scoringId;

    @Column(name = "scoringEnum", nullable = false)
    private ScoringEnum scoringEnum;
    @Column(name = "points", nullable = false)
    private Integer points;

    @ManyToOne
    @JoinColumn(name="competition_id", nullable=false)
    @JsonBackReference
    private Competition competition;
}
