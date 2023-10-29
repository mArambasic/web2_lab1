package com.example.web2_lab1.dao;

import com.example.web2_lab1.domain.Competitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompetitorRepository extends JpaRepository<Competitor, Long> {
}
