package kr.co.introme.introme.domain.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamInviteRequest {
    private String teamName;

    private String email;

}
