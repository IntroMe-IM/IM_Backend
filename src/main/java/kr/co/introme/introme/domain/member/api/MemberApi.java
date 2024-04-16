package kr.co.introme.introme.domain.member.api;

import jakarta.validation.Valid;
import kr.co.introme.introme.domain.member.application.MemberSigninService;
import kr.co.introme.introme.domain.member.application.MemberSignupService;
import kr.co.introme.introme.domain.member.dto.MemberSignInRequest;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberSignupService memberSignupService;
    private final MemberSigninService memberSigninService;

    @PostMapping("/signup")
    public ResponseEntity<String> save(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {
        memberSignupService.signUp(memberSignUpRequest);
        return ResponseEntity.ok("회원가입 완료!");
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signIn(@Valid @RequestBody MemberSignInRequest memberSignInRequest) {
        if (memberSigninService.signIn(memberSignInRequest)) {
            return ResponseEntity.ok("로그인 성공!");
        } else {
            return ResponseEntity.badRequest().body("로그인 실패: 비밀번호 불일치");
        }
    }
}
