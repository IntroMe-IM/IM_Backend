package kr.co.introme.introme.domain.member.api;

import jakarta.validation.Valid;
import kr.co.introme.introme.domain.member.application.MemberSigninService;
import kr.co.introme.introme.domain.member.application.MemberSignupService;
import kr.co.introme.introme.domain.member.dto.MemberSignInRequest;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<Member> signIn(@Valid @RequestBody MemberSignInRequest memberSignInRequest) {
        try {
            Member member = memberSigninService.signIn(memberSignInRequest);
            return ResponseEntity.ok(member);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build(); // 상세한 에러 메시지나 유형에 따라 다르게 처리할 수 있습니다.
        }
    }
}
