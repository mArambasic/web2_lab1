package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    public Scoring() {

    }

    public Scoring(ScoringEnum scoringEnum, Integer points, Competition competition) {
        this.scoringEnum = scoringEnum;
        this.points = points;
        this.competition = competition;
    }

    @JsonProperty("points")
    public Integer getPoints() {
        return points;
    }

    @JsonProperty("scoringEnum")
    public ScoringEnum getScoringEnum() {
        return scoringEnum;
    }
}
