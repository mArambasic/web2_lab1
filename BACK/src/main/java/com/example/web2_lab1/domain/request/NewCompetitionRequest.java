package com.example.web2_lab1.domain.request;

public class NewCompetitionRequest {
    private String competitionName;
    private String competitors;
    private String pointDistribution;
    private String userId;

    public NewCompetitionRequest(String competitionName, String competitors, String pointDistribution, String userId) {
        this.competitionName = competitionName;
        this.competitors = competitors;
        this.pointDistribution = pointDistribution;
        this.userId = userId;
    }

    public String getCompetitionName() {
        return competitionName;
    }

    public String getCompetitors() {
        return competitors;
    }

    public String getPointDistribution() {
        return pointDistribution;
    }

    public String getUserId() {
        return userId;
    }

}
