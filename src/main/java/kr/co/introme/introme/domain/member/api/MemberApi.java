package kr.co.introme.introme.domain.member.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.introme.introme.domain.member.application.MemberSigninService;
import kr.co.introme.introme.domain.member.application.MemberSignupService;
import kr.co.introme.introme.domain.member.application.MemberService;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.dto.MemberSignInRequest;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.domain.card.dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@Tag(name = "회원 API", description = "회원 가입, 로그인 API")
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberSignupService memberSignupService;
    private final MemberSigninService memberSigninService;
    private final MemberService memberService;

    @Operation(summary = "회원가입", description = "dto로 받은 회원가입 정보를 저장합니다.")
    @PostMapping("/signup")
    public ResponseEntity<String> save(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest) {
        memberSignupService.signUp(memberSignUpRequest);
        return ResponseEntity.ok("회원가입 완료!");
    }

    @Operation(summary = "로그인", description = "dto로 받은 로그인 정보를 검증합니다.")
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody MemberSignInRequest memberSignInRequest) {
        try {
            String jwtToken = memberSigninService.signIn(memberSignInRequest);
            return ResponseEntity.ok().header("Authorization", "Bearer " + jwtToken).body("로그인 성공!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그인 실패: " + e.getMessage());
        }
    }

    @Operation(summary = "다른 회원 조회", description = "현재 로그인한 회원 외의 모든 회원을 조회합니다.")
    @GetMapping("/others/{memberId}")
    public ResponseEntity<List<Member>> getOtherMembers(@PathVariable Long memberId) {
        List<Member> otherMembers = memberService.getOtherMembers(memberId);
        return ResponseEntity.ok(otherMembers);
    }

    @Operation(summary = "명함 공유", description = "명함을 다른 회원과 공유합니다.")
    @PostMapping("/share/{ownerId}/{sharedWithId}")
    public ResponseEntity<String> shareCard(@PathVariable Long ownerId, @PathVariable Long sharedWithId) {
        memberService.shareCard(ownerId, sharedWithId);
        return ResponseEntity.ok("명함 공유 완료!");
    }

    @Operation(summary = "공유 받은 명함 조회", description = "공유 받은 명함을 조회합니다.")
    @GetMapping("/shared-cards/{memberId}")
    public ResponseEntity<List<CardDTO>> getSharedCards(@PathVariable Long memberId) {
        List<CardDTO> sharedCards = memberService.getSharedCards(memberId);
        return ResponseEntity.ok(sharedCards);
    }
}
