package com.example.web2_lab1.dao;

import com.example.web2_lab1.domain.Match;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match, Long> {
}
