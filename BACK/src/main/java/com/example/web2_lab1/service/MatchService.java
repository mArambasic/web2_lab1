package com.example.web2_lab1.service;

import com.example.web2_lab1.dao.CompetitorRepository;
import com.example.web2_lab1.dao.MatchRepository;
import com.example.web2_lab1.dao.RoundRepository;
import com.example.web2_lab1.domain.*;
import com.example.web2_lab1.domain.request.SaveMatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;

    @Autowired
    private CompetitorRepository competitorRepository;

    public void saveMatchChanges(List<SaveMatchRequest> saveMatchRequest) {
        int i = 0;
        Round round;
        Competition competition = null;

        for (SaveMatchRequest match : saveMatchRequest) {
            String matchId = match.getMatchId();
            String result = match.getResult();

            Match matchPrevious = repository.findById(Long.parseLong(matchId)).orElse(null);

            System.out.println(matchId + "," + result + "," + matchPrevious);

            if(matchPrevious == null) return;

            if(i == 0){
                round = matchPrevious.getRound();

                if (round != null) {
                    competition = round.getCompetition();
                }
            }

            if(!result.equals(matchPrevious.getScoringEnum().name())) {
                matchPrevious.setScoringEnum(ScoringEnum.valueOf(result));
                repository.save(matchPrevious);
            }
            i++;
        }

        if(competition != null) updateScores(saveMatchRequest, competition.getCompetitors(), competition.getScoring());
    }

    private void updateScores(List<SaveMatchRequest> saveMatchRequest, List<Competitor> competitors, List<Scoring> scoring) {
        for(Competitor competitor: competitors)  {
            competitor.setScore(0);
            competitorRepository.save(competitor);
        }

        for (int i = 0; i < saveMatchRequest.size(); i++) {
            String matchId = saveMatchRequest.get(i).getMatchId();

            Match matchPrevious = repository.findById(Long.parseLong(matchId)).orElse(null);
            if(matchPrevious == null) return;

            ScoringEnum scoringEnum = matchPrevious.getScoringEnum();

            Competitor first = null, second = null;
            for(Competitor competitor: competitors)  {
                if(competitor.getName().equals(matchPrevious.getPlayer1())) {
                    first = competitor;
                } else if (competitor.getName().equals(matchPrevious.getPlayer2())) {
                    second = competitor;
                }
            }
            
            if(first != null && second != null) {
                Integer firstScore = first.getScore(), secondScore = second.getScore();
                switch(scoringEnum){
                    case LOSS -> {
                        first.setScore(firstScore + scoring.get(1).getPoints());
                        second.setScore(secondScore + scoring.get(0).getPoints());
                    }
                    case WIN -> {
                        first.setScore(firstScore + scoring.get(0).getPoints());
                        second.setScore(secondScore + scoring.get(1).getPoints());
                    }
                    case DRAW -> {
                        first.setScore(firstScore + scoring.get(2).getPoints());
                        second.setScore(secondScore + scoring.get(2).getPoints());
                    }
                }

                competitorRepository.save(first);
                competitorRepository.save(second);
            }
        }
    }
}
