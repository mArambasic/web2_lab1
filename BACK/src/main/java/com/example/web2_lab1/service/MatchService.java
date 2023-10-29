package com.example.web2_lab1.service;

import com.example.web2_lab1.dao.MatchRepository;
import com.example.web2_lab1.domain.Match;
import com.example.web2_lab1.domain.ScoringEnum;
import com.example.web2_lab1.domain.request.SaveMatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;

    public void saveMatchChanges(List<SaveMatchRequest> saveMatchRequest) {
        for (SaveMatchRequest match : saveMatchRequest) {
            String matchId = match.getMatchId();
            String result = match.getResult();

            Match matchPrevious = repository.findById(Long.parseLong(matchId)).orElse(null);

            System.out.println(matchId + "," + result + "," + matchPrevious);

            if(matchPrevious == null) return;

            if(!result.contentEquals(matchPrevious.getScoringEnum().name())) {
                matchPrevious.setScoringEnum(ScoringEnum.valueOf(result));
            }

        }
    }
}
