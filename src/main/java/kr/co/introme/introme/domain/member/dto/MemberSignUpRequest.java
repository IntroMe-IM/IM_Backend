package kr.co.introme.introme.domain.member.dto;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberSignUpRequest {
    private String password;
    private String email;


}
