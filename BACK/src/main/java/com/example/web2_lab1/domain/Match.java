package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity(name = "match")
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "match_id", nullable = false)
    private Long matchId;

    @Column(name = "player1", nullable = false)
    private String player1;
    @Column(name = "player2", nullable = false)
    private String player2;
    @Column(name = "scoringEnum", nullable = false)
    private ScoringEnum scoringEnum;

    @ManyToOne
    @JoinColumn(name="round_id", nullable=false)
    @JsonBackReference
    private Round round;
}
