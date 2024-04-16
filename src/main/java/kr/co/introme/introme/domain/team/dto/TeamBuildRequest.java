package kr.co.introme.introme.domain.team.dto;

public class TeamBuildRequest {
    private String name;
    private String description;

    public TeamBuildRequest(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
