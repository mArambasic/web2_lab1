package com.example.web2_lab1.dao;

import com.example.web2_lab1.domain.Scoring;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScoringRepository extends JpaRepository<Scoring, Long> {
}
