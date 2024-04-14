package kr.co.introme.introme.domain.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberSignUpRequest {
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @NotBlank(message = "이름은 필수 항목입니다.")
    private String name;

    private String organization;

    @NotBlank(message = "전화번호는 필수 항목입니다.")
    private String phoneNumber;

    private String url;

    private Date birthday;
}
