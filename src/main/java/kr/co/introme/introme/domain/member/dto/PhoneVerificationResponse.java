package kr.co.introme.introme.domain.member.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PhoneVerificationResponse {

    @Schema(description = "응답 메시지", example = "인증번호 발송 완료!")
    private String message;
}
