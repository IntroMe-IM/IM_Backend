package kr.co.introme.introme.domain.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamInviteRequest {
    private Long team_id;

    private Long owner_id;
}
