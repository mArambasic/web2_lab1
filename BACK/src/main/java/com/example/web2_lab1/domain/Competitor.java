package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity(name = "competitor")
public class Competitor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "competitor_id", nullable = false)
    private Long competitorId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "score", nullable = false)
    private Integer score;

    @ManyToOne
    @JoinColumn(name="competition_id", nullable=false)
    @JsonBackReference
    private Competition competition;

    public Competitor(String name, Integer score, Competition competition) {
        this.name = name;
        this.score = score;
        this.competition = competition;
    }

    public Competitor() {

    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("score")
    public Integer getScore() {
        return score;
    }
}
