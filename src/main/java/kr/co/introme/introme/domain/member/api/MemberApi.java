package kr.co.introme.introme.domain.member.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import kr.co.introme.introme.domain.member.application.MemberSigninService;
import kr.co.introme.introme.domain.member.application.MemberSignupService;
import kr.co.introme.introme.domain.member.application.VerificationService;
import kr.co.introme.introme.domain.member.dto.*;
import kr.co.introme.introme.global.config.SmsProperties;
import lombok.RequiredArgsConstructor;
import net.nurigo.sdk.message.exception.NurigoMessageNotReceivedException;
import net.nurigo.sdk.message.model.Message;
import net.nurigo.sdk.message.service.DefaultMessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Path;

import java.util.Map;

@RestController
@Tag(name = "회원 API", description = "회원 가입, 로그인 API")
@RequestMapping("/v1/member")
@RequiredArgsConstructor
public class MemberApi {

    private final MemberSignupService memberSignupService;
    private final MemberSigninService memberSigninService;
    private final VerificationService verificationService;
    private final SmsProperties smsProperties;
    private final DefaultMessageService messageService;

    @Operation(summary = "회원가입", description = "dto로 받은 회원가입 정보를 저장합니다.")
    @PostMapping("/signup")
    public ResponseEntity<String> save(@Valid @RequestBody MemberSignUpRequest memberSignUpRequest, @RequestParam String verificationCode) {
        if (!verificationService.verifyCode(memberSignUpRequest.getPhoneNumber(), verificationCode)) {
            return ResponseEntity.badRequest().body("인증번호가 일치하지 않습니다.");
        }
        memberSignupService.signUp(memberSignUpRequest);
        return ResponseEntity.ok("회원가입 완료!");
    }

    @Operation(summary = "로그인", description = "dto로 받은 로그인 정보를 검증합니다.")
    @PostMapping("/signin")
    public ResponseEntity<?> signIn(@Valid @RequestBody MemberSignInRequest memberSignInRequest) {
        try {
            Map<String, Object> response = memberSigninService.signIn(memberSignInRequest);
            return ResponseEntity.ok().header("Authorization", "Bearer " + response.get("token")).body(response);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("로그인 실패: " + e.getMessage());
        }
    }

    @Operation(summary = "전화번호 인증 요청", description = "회원가입 전에 전화번호 인증을 요청합니다.")
    @PostMapping("/verify-phone")
    public ResponseEntity<PhoneVerificationResponse> verifyPhone(@Valid @RequestBody PhoneVerificationRequest phoneVerificationRequest) {
        String verificationCode = verificationService.generateVerificationCode();
        verificationService.saveVerificationCode(phoneVerificationRequest.getPhoneNumber(), verificationCode);

        Message message = new Message();
        message.setFrom(smsProperties.getFromNumber());
        message.setTo(phoneVerificationRequest.getPhoneNumber());
        message.setText("인증번호: " + verificationCode);

        try {
            messageService.send(message);
            return ResponseEntity.ok(new PhoneVerificationResponse("인증번호 발송 완료!"));
        } catch (NurigoMessageNotReceivedException exception) {
            return ResponseEntity.badRequest().body(new PhoneVerificationResponse("인증번호 발송 실패: " + exception.getMessage()));
        } catch (Exception exception) {
            return ResponseEntity.badRequest().body(new PhoneVerificationResponse("오류 발생: " + exception.getMessage()));
        }
    }

    @Operation(summary = "회원 정보 반환", description = "회원의 정보를 반환합니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<MemberResponse> memberReturn(@PathVariable Long memberId){
        MemberResponse result = memberSigninService.findMember(memberId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "회원 id 반환", description = "이메일을 기반으로 회원의 id를 제공")
    @GetMapping("/{email}")
    public ResponseEntity<String> returnId(@PathVariable String email){
        Long result = memberSigninService.findMemberByEmail(email);
        if(result != null){
            return ResponseEntity.ok(result.toString());
        }else {
            return ResponseEntity.ok("does not exist");
        }

    }

    @Operation(summary = "회원 정보 수정", description = "회원의 정보를 수정합니다.")
    @PutMapping("/{memberId}")
    public ResponseEntity<String> updateMember(@PathVariable Long memberId, @RequestBody MemberUpdateRequest memberUpdateRequest){
        Boolean result = memberSigninService.updating(memberId, memberUpdateRequest);
        if(result){
            return ResponseEntity.ok("수정 완료!");
        } else {
            return ResponseEntity.ok("입력값을 확인하세요!");
        }
    }
}
