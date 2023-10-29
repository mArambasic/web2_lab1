package com.example.web2_lab1.rest;

import com.example.web2_lab1.domain.request.SaveMatchRequest;
import com.example.web2_lab1.service.MatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "*")
public class MatchController {

    @Autowired
    private MatchService service;

    @PostMapping("/api/saveMatchChanges")
    public void saveMatchChanges(@RequestBody List<SaveMatchRequest> saveMatchRequest) {
        service.saveMatchChanges(saveMatchRequest);
    }
}