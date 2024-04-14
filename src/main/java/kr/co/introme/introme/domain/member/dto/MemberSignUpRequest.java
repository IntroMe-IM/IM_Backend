package kr.co.introme.introme.domain.member.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberSignUpRequest {
    private String password;
    private String email;
    private String name;
    private String organization;
    private String phoneNumber;
    private String url;
    private Date birthday;


}
