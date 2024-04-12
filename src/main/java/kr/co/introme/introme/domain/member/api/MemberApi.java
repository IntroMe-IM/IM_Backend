package kr.co.introme.introme.domain.member.api;

import kr.co.introme.introme.domain.member.application.MemberSignupService;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class MemberApi {
    private final MemberSignupService memberSignupService;

    @PostMapping("/member/join")
    public ResponseEntity<String> save1 (@RequestBody MemberSignUpRequest memberSignUpRequest) throws IOException {
        memberSignupService.signUp(memberSignUpRequest);
        return ResponseEntity.ok("회원가입 완료!");
    }
}
