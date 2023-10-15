package com.example.web2_lab1.rest;

import com.example.web2_lab1.service.ScoringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/scoring")
public class ScoringController {

    @Autowired
    private ScoringService service;

}