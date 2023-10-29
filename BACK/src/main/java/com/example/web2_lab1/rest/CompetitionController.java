package com.example.web2_lab1.rest;

import com.example.web2_lab1.domain.Competition;
import com.example.web2_lab1.domain.request.NewCompetitionRequest;
import com.example.web2_lab1.service.CompetitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class CompetitionController {

    @Autowired
    private CompetitionService service;

    @PostMapping("/api/newCompetition")
    public void addCompetition(@RequestBody NewCompetitionRequest competitionRequest) {
        service.addCompetition(competitionRequest);
    }

    @GetMapping
    public List<Competition> getAllCompetitions() {
        return service.getAllCompetitions();
    }

    @GetMapping("/api/getCompetitions/{userId}")
    public ResponseEntity<List<Competition>> getCompetitionsByUserId(@PathVariable String userId) {
        List<Competition> competitions = service.getCompetitionsByUserId(userId);
        return ResponseEntity.ok(competitions);
    }

    @GetMapping("/api/getCompetitionByLink/{link}")
    public ResponseEntity<Competition> getCompetitionByLink(@PathVariable String link) {
        Competition competition = service.getCompetitionByLink(link);
        return ResponseEntity.ok(competition);
    }

}