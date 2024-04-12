package kr.co.introme.introme.domain.member.api;

import kr.co.introme.introme.domain.member.application.MemberSignupService;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberSignupService memberSignupService;

    @PostMapping("/join")
    public ResponseEntity<String> save1 (@RequestBody MemberSignUpRequest memberSignUpRequest){
        memberSignupService.signUp2(memberSignUpRequest);
        return ResponseEntity.ok("회원가입 완료!");
    }

}
