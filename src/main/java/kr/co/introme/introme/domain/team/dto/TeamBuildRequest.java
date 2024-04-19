package kr.co.introme.introme.domain.team.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TeamBuildRequest {

    @NotBlank(message = "팀 이름은 필수 항목입니다.")
    private String teamName;

    private String email;

}
