package kr.co.introme.introme.domain.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TeamTerminateRequest {
    private Long ownerId;

    private Long teamId;
}
