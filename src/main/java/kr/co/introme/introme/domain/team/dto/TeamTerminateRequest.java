package kr.co.introme.introme.domain.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@NoArgsConstructor
public class TeamTerminateRequest {
    private Long ownerId;

    private Long teamId;

    private HashMap<Long, Integer> contribute;
}
