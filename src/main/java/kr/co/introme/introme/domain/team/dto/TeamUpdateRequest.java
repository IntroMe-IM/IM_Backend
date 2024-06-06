package kr.co.introme.introme.domain.team.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TeamUpdateRequest {
    private String name;
    private String project;
    private String description;
}
