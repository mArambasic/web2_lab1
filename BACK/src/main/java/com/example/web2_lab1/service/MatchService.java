package com.example.web2_lab1.service;

import com.example.web2_lab1.dao.CompetitorRepository;
import com.example.web2_lab1.dao.MatchRepository;
import com.example.web2_lab1.dao.RoundRepository;
import com.example.web2_lab1.domain.*;
import com.example.web2_lab1.domain.request.SaveMatchRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchService {

    @Autowired
    private MatchRepository repository;

    @Autowired
    private RoundRepository roundRepository;

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
                round = roundRepository.findById(matchPrevious.getMatchId()).orElse(null);

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

        updateScores(saveMatchRequest, competition.getCompetitors(), competition.getScoring());
    }

    private void updateScores(List<SaveMatchRequest> saveMatchRequest, List<Competitor> competitors, List<Scoring> scoring) {
        int firstIndex, secondIndex;

        for(Competitor competitor: competitors)  {
            competitor.setScore(1);
            competitorRepository.save(competitor);
        }

        for (int i = 0; i < saveMatchRequest.size(); i++) {
            firstIndex = getFirstIndex(saveMatchRequest.size(), i);
            secondIndex = getSecondIndex(saveMatchRequest.size(), i);

            String matchId = saveMatchRequest.get(i).getMatchId();

            Match matchPrevious = repository.findById(Long.parseLong(matchId)).orElse(null);
            if(matchPrevious == null) return;

            ScoringEnum scoringEnum = matchPrevious.getScoringEnum();
            Integer scoringPoints = 0;

            for(Scoring s: scoring) {
                if(s.getScoringEnum() == scoringEnum) {
                    scoringPoints = s.getPoints();
                }
            }

            Competitor first = competitors.get(firstIndex);
            Competitor second = competitors.get(secondIndex);

            Integer firstScore = first.getScore(), secondScore = second.getScore();
            switch(scoringEnum){
                case LOSS -> second.setScore(secondScore + scoringPoints);
                case WIN -> first.setScore(firstScore + scoringPoints);
                case DRAW -> {
                    first.setScore(firstScore + scoringPoints);
                    second.setScore(secondScore + scoringPoints);
                }
            }

            competitorRepository.save(first);
            competitorRepository.save(second);
        }
    }

    private int getFirstIndex(int size, int i) {
        switch(size){
            case 4 -> {
                switch(i){
                    case 0, 2, 4 -> {return 0;}
                    case 1 -> {return 2;}
                    case 3, 5 ->{return 1;}
                }
            }
            case 5 -> {
                switch(i){
                    case 0, 2, 4, 6 -> {return 0;}
                    case 1, 7 -> {return 2;}
                    case 3, 5, 8 -> {return 1;}
                    case 9 -> {return 4;}
                }
            }
            case 6 -> {
                switch(i){
                    case 0, 3, 6, 9, 12 -> {return 0;}
                    case 1, 7 -> {return 2;}
                    case 2, 13 -> {return 4;}
                    case 4, 8, 10, 14 -> {return 1;}
                    case 5, 11 -> {return 5;}
                }
            }
            case 7 -> {
                switch(i){
                    case 0,17,19 -> {return 0;}
                    case 1,6,20 -> {return 1;}
                    case 2,7,12 -> {return 2;}
                    case 3,8,13 -> {return 3;}
                    case 4,9,14 -> {return 4;}
                    case 5,10,15 -> {return 5;}
                    case 11,16,18 -> {return 6;}
                }
            }
            case 8 -> {
                switch(i){
                    case 5, 16, 26 -> {return 0;}
                    case 0, 7, 14, 22 -> {return 1;}
                    case 1, 4, 23 -> {return 2;}
                    case 2, 10, 17, 20 -> {return 3;}
                    case 3, 11, 18 -> {return 4;}
                    case 8, 15, 19, 27 -> {return 5;}
                    case 9, 12, 24 -> {return 6;}
                    case 6, 13, 21, 25 -> {return 7;}
                }

            }
        }
        return 0;
    }

    private int getSecondIndex(int size, int i) {
        switch(size){
            case 4 -> {
                switch(i){
                    case 0 -> {return 1;}
                    case 2, 5 -> {return 2;}
                    case 1, 3, 4 -> {return 3;}
                }
            }
            case 5 -> {
                switch(i){
                    case 0 -> {return 1;}
                    case 2, 8 -> {return 2;}
                    case 1, 5, 6, 9 -> {return 3;}
                    case 3, 4, 7 -> {return 4;}
                }
            }
            case 6 -> {
                switch(i){
                    case 0 -> {return 1;}
                    case 3, 11, 14 -> {return 2;}
                    case 1, 5, 6, 10, 13 -> {return 3;}
                    case 4, 7, 9 -> {return 4;}
                    case 2, 8, 12 -> {return 5;}
                }
            }
            case 7 -> {
                switch(i){
                    case 4, 11, 12 -> {return 0;}
                    case 3, 10, 17 -> {return 1;}
                    case 9, 16, 20 -> {return 2;}
                    case 2, 15, 19 -> {return 3;}
                    case 1, 8, 18 -> {return 4;}
                    case 0, 7, 14 -> {return 5;}
                    case 5, 6, 13 -> {return 6;}
                }
            }
            case 8 -> {
                switch(i){
                    case 0, 10, 15, 21 -> {return 0;}
                    case 8, 17, 25 -> {return 1;}
                    case 11, 14, 16, 24 -> {return 2;}
                    case 4, 13, 27 -> {return 3;}
                    case 7, 12, 20, 26 -> {return 4;}
                    case 3, 6, 23 -> {return 5;}
                    case 2, 5, 19, 22 -> {return 6;}
                    case 1, 9, 18 -> {return 7;}
                }
            }
        }
        return 0;
    }
}
