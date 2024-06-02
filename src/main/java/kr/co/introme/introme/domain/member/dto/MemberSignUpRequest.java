package kr.co.introme.introme.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "회원 비밀번호", example = "password123")
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;

    @Schema(description = "회원 이메일", example = "testuser1@introme.kr")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @Schema(description = "회원 이름", example = "홍길동")
    @NotBlank(message = "이름은 필수 항목입니다.")
    private String name;

    @Schema(description = "회원 조직", example = "Introme")
    private String organization;

    @Schema(description = "회원 전화번호", example = "010-1234-5678")
    @NotBlank(message = "전화번호는 필수 항목입니다.")
    private String phoneNumber;

    @Schema(description = "회원 URL", example = "http://introme.kr")
    private String url;

    @Schema(description = "회원 생일", example = "2000-01-01")
    private Date birthday;
}
