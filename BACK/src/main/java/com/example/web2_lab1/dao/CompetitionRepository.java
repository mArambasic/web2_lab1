package com.example.web2_lab1.dao;

import com.example.web2_lab1.domain.Competition;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompetitionRepository extends JpaRepository<Competition, Long> {
    List<Competition> findByUserId(String userId);

    Competition findByLink(String link);
}

