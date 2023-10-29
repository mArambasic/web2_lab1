package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    public Match() {

    }


    public Match(String player1, String player2, ScoringEnum scoringEnum, Round round) {
        this.player1 = player1;
        this.player2 = player2;
        this.scoringEnum = scoringEnum;
        this.round = round;
    }

    @JsonProperty("player1")
    public String getPlayer1() {
        return player1;
    }

    @JsonProperty("player2")
    public String getPlayer2() {
        return player2;
    }

    @JsonProperty("name")
    public String getName() {
        return player2;
    }

    @JsonProperty("scoringEnum")
    public ScoringEnum getScoringEnum() {
        return scoringEnum;
    }
}
