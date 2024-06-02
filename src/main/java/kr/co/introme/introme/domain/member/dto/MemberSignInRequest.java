package kr.co.introme.introme.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class MemberSignInRequest {
    @Schema(description = "회원 이메일", example = "testuser1@introme.kr")
    @Email(message = "유효하지 않은 이메일 형식입니다.")
    private String email;

    @Schema(description = "회원 비밀번호", example = "password123")
    @NotBlank(message = "비밀번호는 필수 항목입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상이어야 합니다.")
    private String password;
}
