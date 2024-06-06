package kr.co.introme.introme.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class MemberResponse {

    @Schema(description = "회원 ID", example = "1")
    private Long id;

    @Schema(description = "회원 이메일", example = "testuser1@introme.kr")
    private String email;

    @Schema(description = "회원 이름", example = "John Doe")
    private String name;

    @Schema(description = "회원 전화번호", example = "010-1234-5678")
    private String phoneNumber;

    @Schema(description = "회원 URL", example = "http://example.com")
    private String url;

    @Schema(description = "회원 생일", example = "1990-01-01")
    private LocalDate birthday;

    @Schema(description = "회원 나이", example = "30")
    private int age;

    @Schema(description = "회원 MBTI", example = "INFJ")
    private String mbti;

    @Schema(description = "회원 조직", example = "Introme")
    private String organization;

    public MemberResponse(Member member) {
        this.id = member.getId();
        this.email = member.getEmail();
        this.name = member.getName();
        this.phoneNumber = member.getPhoneNumber();
        this.url = member.getUrl();
        this.birthday = member.getBirthday();
        this.age = member.getAge();
        this.mbti = member.getMbti();
        this.organization = member.getOrganization();
    }
}
