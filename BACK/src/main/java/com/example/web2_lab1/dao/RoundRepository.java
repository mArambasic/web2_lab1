package com.example.web2_lab1.dao;

import com.example.web2_lab1.domain.Round;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoundRepository extends JpaRepository<Round, Long> {
}