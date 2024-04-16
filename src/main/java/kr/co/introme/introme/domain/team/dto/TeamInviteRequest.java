package kr.co.introme.introme.domain.team.dto;

public class TeamInviteRequest {
    private String teamName;
    private String email;

    public TeamInviteRequest(String teamName, String email) {
        this.teamName = teamName;
        this.email = email;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getEmail() {
        return email;
    }
}
