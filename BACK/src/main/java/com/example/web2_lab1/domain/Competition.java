package com.example.web2_lab1.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity(name = "competition")
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "competition_id", nullable = false)
    private Long competitionId;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "link", nullable = false)
    private String link;

    @OneToMany(mappedBy = "competition", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Round> rounds;

    @OneToMany(mappedBy = "competition", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Scoring> scoring;

    @Column(name="user_id", nullable=false)
    private String userId;


    @OneToMany(mappedBy = "competition", cascade=CascadeType.ALL)
    @JsonManagedReference
    private List<Competitor> competitors;

    public Competition() {

    }
    public void setName(String name) {
        this.name = name;
    }

    public void setUserId(String id) {
        this.userId = id;
    }

    public void setRounds(List<Round> rounds) {
        this.rounds = rounds;
    }

    public void setScoring(List<Scoring> allScorings) {
        this.scoring = allScorings;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCompetitors(List<Competitor> competitors) {
        this.competitors = competitors;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }


    @JsonProperty("link")
    public String getLink() {
        return link;
    }
}
