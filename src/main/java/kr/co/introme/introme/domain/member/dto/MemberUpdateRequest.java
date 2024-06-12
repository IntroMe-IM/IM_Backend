package kr.co.introme.introme.domain.member.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberUpdateRequest {
    private String name;
    private String mbti;
    private String organization;
}
