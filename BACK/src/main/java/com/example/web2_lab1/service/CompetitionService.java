package com.example.web2_lab1.service;

import com.example.web2_lab1.dao.*;
import com.example.web2_lab1.domain.*;
import com.example.web2_lab1.domain.request.NewCompetitionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Service
public class CompetitionService {

    @Autowired
    private CompetitionRepository competitionRepository;
    @Autowired
    private ScoringRepository scoringRepository;
    @Autowired
    private RoundRepository roundRepository;
    @Autowired
    private MatchRepository matchRepository;

    @Autowired
    private CompetitorRepository competitorRepository;

    public boolean addCompetition(NewCompetitionRequest competitionRequest) {
        Competition competition = new Competition();

        competition.setName(competitionRequest.getCompetitionName());
        competition.setUserId(competitionRequest.getUserId());
        competition.setLink(generateString());

        competitionRepository.save(competition);

        setCompetitors(competition, competitionRequest.getCompetitors());
        generateRounds(competition, competitionRequest.getCompetitors());
        addScoring(competition, competitionRequest.getPointDistribution());

        return true;
    }

    private void setCompetitors(Competition competition, String competitors) {
        String[] competitorsSplit = competitors.split(",");
        List<Competitor> competitorList = new ArrayList<>();

        for(int i = 0; i < competitorsSplit.length; i++) {
            Competitor competitor = new Competitor(competitorsSplit[i], 0, competition);
            competitorRepository.save(competitor);

            competitorList.add(competitor);
        }

        competition.setCompetitors(competitorList);
    }

    private void addScoring(Competition competition, String pointDistribution) {
        List<Scoring> allScoring = new ArrayList<>();
        String[] pointValues = pointDistribution.split("/");
        int i = 0;

        for(ScoringEnum value: ScoringEnum.values()) {
            int score = 0;

            if(i != 3) score = Integer.parseInt(pointValues[i]);

            Scoring scoring = new Scoring(
                    value,
                    score,
                    competition
            );
            scoringRepository.save(scoring);

            allScoring.add(scoring);
            i++;
        }

        competition.setScoring(allScoring);
    }

    private void generateRounds(Competition competition, String competitors) {
        String[] competitorsSplit = competitors.split(",");

        switch (competitorsSplit.length) {
            case 4 -> generate4Rounds(competition, competitorsSplit);
            case 5 -> generate5Rounds(competition, competitorsSplit);
            case 6 -> generate6Rounds(competition, competitorsSplit);
            case 7 -> generate7Rounds(competition, competitorsSplit);
            case 8 -> generate8Rounds(competition, competitorsSplit);
        }
    }

    private void generate4Rounds(Competition competition, String[] competitor) {
        List<Round> rounds = new ArrayList<>();
        int firstC = 0, secondC = 0, thirdC = 0, fourthC = 0;

        for(int i = 0; i < 3; i++) {
            switch (i) {
                case 0 -> {
                    firstC = 0;
                    secondC = 1;
                    thirdC = 2;
                    fourthC = 3;
                }
                case 1 -> {
                    firstC = 0;
                    secondC = 2;
                    thirdC = 1;
                    fourthC = 3;
                }
                case 2 -> {
                    firstC = 0;
                    secondC = 3;
                    thirdC = 1;
                    fourthC = 2;
                }
            }

            Round round = new Round(
                i + 1,
                Collections.emptyList(),
                competition
            );
            roundRepository.save(round);

            List<Match> matches = new ArrayList<>();

            Match match1 = new Match(competitor[firstC], competitor[secondC], ScoringEnum.NOT_FINISHED, round);
            Match match2 = new Match(competitor[thirdC], competitor[fourthC], ScoringEnum.NOT_FINISHED, round);

            matches.add(match1);
            matches.add(match2);
            matchRepository.save(match1);
            matchRepository.save(match2);

            round.setMatches(matches);
        }

        competition.setRounds(rounds);
    }

    private void generate5Rounds(Competition competition, String[] competitor) {
        List<Round> rounds = new ArrayList<>();
        int firstC = 0, secondC = 0, thirdC = 0, fourthC = 0;

        for(int i = 0; i < 5; i++) {
            switch (i) {
                case 0 -> {
                    firstC = 0;
                    secondC = 1;
                    thirdC = 2;
                    fourthC = 3;
                }
                case 1 -> {
                    firstC = 0;
                    secondC = 2;
                    thirdC = 1;
                    fourthC = 4;
                }
                case 2 -> {
                    firstC = 0;
                    secondC = 4;
                    thirdC = 1;
                    fourthC = 3;
                }
                case 3 -> {
                    firstC = 0;
                    secondC = 3;
                    thirdC = 2;
                    fourthC = 4;
                }
                case 4 -> {
                    firstC = 1;
                    secondC = 2;
                    thirdC = 4;
                    fourthC = 3;
                }
            }

            Round round = new Round(
                    i + 1,
                    Collections.emptyList(),
                    competition
            );
            roundRepository.save(round);

            List<Match> matches = new ArrayList<>();

            Match match1 = new Match(competitor[firstC], competitor[secondC], ScoringEnum.NOT_FINISHED, round);
            Match match2 = new Match(competitor[thirdC], competitor[fourthC], ScoringEnum.NOT_FINISHED, round);

            matches.add(match1);
            matches.add(match2);
            matchRepository.save(match1);
            matchRepository.save(match2);

            round.setMatches(matches);
        }

        competition.setRounds(rounds);
    }
    private void generate6Rounds(Competition competition, String[] competitor) {
        List<Round> rounds = new ArrayList<>();
        int firstC = 0, secondC = 0, thirdC = 0, fourthC = 0, fifthC = 0, sixthC = 0;

        for(int i = 0; i < 5; i++) {
            switch (i) {
                case 0 -> {
                    firstC = 0;
                    secondC = 1;
                    thirdC = 2;
                    fourthC = 3;
                    fifthC = 0;
                    sixthC = 0;
                }
                case 1 -> {
                    firstC = 0;
                    secondC = 2;
                    thirdC = 1;
                    fourthC = 4;
                    fifthC = 0;
                    sixthC = 0;
                }
                case 2 -> {
                    firstC = 0;
                    secondC = 4;
                    thirdC = 1;
                    fourthC = 3;
                    fifthC = 0;
                    sixthC = 0;
                }
                case 3 -> {
                    firstC = 0;
                    secondC = 3;
                    thirdC = 2;
                    fourthC = 4;
                    fifthC = 0;
                    sixthC = 0;
                }
                case 4 -> {
                    firstC = 1;
                    secondC = 2;
                    thirdC = 4;
                    fourthC = 3;
                    fifthC = 0;
                    sixthC = 0;
                }
            }

            Round round = new Round(
                    i + 1,
                    Collections.emptyList(),
                    competition
            );
            roundRepository.save(round);

            List<Match> matches = new ArrayList<>();

            Match match1 = new Match(competitor[firstC], competitor[secondC], ScoringEnum.NOT_FINISHED, round);
            Match match2 = new Match(competitor[thirdC], competitor[fourthC], ScoringEnum.NOT_FINISHED, round);
            Match match3 = new Match(competitor[fifthC], competitor[sixthC], ScoringEnum.NOT_FINISHED, round);

            matches.add(match1);
            matches.add(match2);
            matches.add(match3);
            matchRepository.save(match1);
            matchRepository.save(match2);
            matchRepository.save(match3);

            round.setMatches(matches);
        }

        competition.setRounds(rounds);
    }
    private void generate7Rounds(Competition competition, String[] competitor) {
        List<Round> rounds = new ArrayList<>();
        int firstC = 0, secondC = 0, thirdC = 0, fourthC = 0, fifthC = 0, sixthC = 0;

        for(int i = 0; i < 7; i++) {
            switch (i) {
                case 0 -> {
                    firstC = 0;
                    secondC = 5;
                    thirdC = 1;
                    fourthC = 4;
                    fifthC = 2;
                    sixthC = 3;
                }
                case 1 -> {
                    firstC = 3;
                    secondC = 1;
                    thirdC = 4;
                    fourthC = 0;
                    fifthC = 5;
                    sixthC = 6;
                }
                case 2 -> {
                    firstC = 1;
                    secondC = 6;
                    thirdC = 2;
                    fourthC = 5;
                    fifthC = 3;
                    sixthC = 4;
                }
                case 3 -> {
                    firstC = 4;
                    secondC = 2;
                    thirdC = 5;
                    fourthC = 1;
                    fifthC = 6;
                    sixthC = 0;
                }
                case 4 -> {
                    firstC = 2;
                    secondC = 0;
                    thirdC = 3;
                    fourthC = 6;
                    fifthC = 4;
                    sixthC = 5;
                }
                case 5 -> {
                    firstC = 5;
                    secondC = 3;
                    thirdC = 6;
                    fourthC = 2;
                    fifthC = 0;
                    sixthC = 1;
                }
                case 6 -> {
                    firstC = 6;
                    secondC = 4;
                    thirdC = 0;
                    fourthC = 3;
                    fifthC = 1;
                    sixthC = 2;
                }
            }

            Round round = new Round(
                    i + 1,
                    Collections.emptyList(),
                    competition
            );
            roundRepository.save(round);

            List<Match> matches = new ArrayList<>();

            Match match1 = new Match(competitor[firstC], competitor[secondC], ScoringEnum.NOT_FINISHED, round);
            Match match2 = new Match(competitor[thirdC], competitor[fourthC], ScoringEnum.NOT_FINISHED, round);
            Match match3 = new Match(competitor[fifthC], competitor[sixthC], ScoringEnum.NOT_FINISHED, round);

            matches.add(match1);
            matches.add(match2);
            matches.add(match3);
            matchRepository.save(match1);
            matchRepository.save(match2);
            matchRepository.save(match3);

            round.setMatches(matches);
        }

        competition.setRounds(rounds);
    }
    private void generate8Rounds(Competition competition, String[] competitor) {
        List<Round> rounds = new ArrayList<>();
        int firstC = 0, secondC = 0, thirdC = 0, fourthC = 0,
                fifthC = 0, sixthC = 0, seventhC = 0, eigthC = 0;

        for(int i = 0; i < 7; i++) {
            switch (i) {
                case 0 -> {
                    firstC = 1;
                    secondC = 0;
                    thirdC = 2;
                    fourthC = 7;
                    fifthC = 3;
                    sixthC = 6;
                    seventhC = 4;
                    eigthC = 5;
                }
                case 1 -> {
                    firstC = 2;
                    secondC = 3;
                    thirdC = 0;
                    fourthC = 6;
                    fifthC = 7;
                    sixthC = 5;
                    seventhC = 1;
                    eigthC = 4;
                }
                case 2 -> {
                    firstC = 5;
                    secondC = 1;
                    thirdC = 6;
                    fourthC = 7;
                    fifthC = 3;
                    sixthC = 0;
                    seventhC = 4;
                    eigthC = 2;
                }
                case 3 -> {
                    firstC = 6;
                    secondC = 4;
                    thirdC = 7;
                    fourthC = 3;
                    fifthC = 1;
                    sixthC = 2;
                    seventhC = 5;
                    eigthC = 1;
                }
                case 4 -> {
                    firstC = 0;
                    secondC = 2;
                    thirdC = 3;
                    fourthC = 1;
                    fifthC = 4;
                    sixthC = 7;
                    seventhC = 5;
                    eigthC = 6;
                }
                case 5 -> {
                    firstC = 3;
                    secondC = 4;
                    thirdC = 7;
                    fourthC = 0;
                    fifthC = 1;
                    sixthC = 6;
                    seventhC = 2;
                    eigthC = 5;
                }
                case 6 -> {
                    firstC = 6;
                    secondC = 2;
                    thirdC = 7;
                    fourthC = 1;
                    fifthC = 0;
                    sixthC = 4;
                    seventhC = 5;
                    eigthC = 3;
                }
            }

            Round round = new Round(
                    i + 1,
                    Collections.emptyList(),
                    competition
            );
            roundRepository.save(round);

            List<Match> matches = new ArrayList<>();

            Match match1 = new Match(competitor[firstC], competitor[secondC], ScoringEnum.NOT_FINISHED, round);
            Match match2 = new Match(competitor[thirdC], competitor[fourthC], ScoringEnum.NOT_FINISHED, round);
            Match match3 = new Match(competitor[fifthC], competitor[sixthC], ScoringEnum.NOT_FINISHED, round);
            Match match4 = new Match(competitor[seventhC], competitor[eigthC], ScoringEnum.NOT_FINISHED, round);

            matches.add(match1);
            matches.add(match2);
            matches.add(match3);
            matches.add(match4);
            matchRepository.save(match1);
            matchRepository.save(match2);
            matchRepository.save(match3);
            matchRepository.save(match4);

            round.setMatches(matches);
        }

        competition.setRounds(rounds);
    }

    public static String generateString() {
        return UUID.randomUUID().toString();
    }


    public List<Competition> getAllCompetitions() {
        return competitionRepository.findAll();
    }

    public List<Competition> getCompetitionsByUserId(String userId) {
        return competitionRepository.findByUserId(userId);
    }

    public Competition getCompetitionByLink(String link) {
        return competitionRepository.findByLink(link);
    }
}
