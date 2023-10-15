package com.example.web2_lab1.service;

import com.example.web2_lab1.dao.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;
}
