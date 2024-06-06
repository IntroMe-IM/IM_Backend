package kr.co.introme.introme.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PhoneVerificationRequest {

    @Schema(description = "수신자 전화번호", example = "01012345678")
    @NotBlank(message = "전화번호는 필수 항목입니다.")
    private String phoneNumber;
}
