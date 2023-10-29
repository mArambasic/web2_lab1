package com.example.web2_lab1.domain.request;

public class SaveMatchRequest {
    private String matchId;
    private String result;

    public SaveMatchRequest(String matchId, String result) {
        this.matchId = matchId;
        this.result = result;
    }

    public String getMatchId() {
        return matchId;
    }

    public String getResult() {
        return result;
    }
}
