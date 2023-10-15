package com.example.web2_lab1.dao;

import com.example.web2_lab1.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
}

