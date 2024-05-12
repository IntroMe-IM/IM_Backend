package kr.co.introme.introme.domain.member.application;

import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignupService {

    private final MemberRepository memberRepository;
    //private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    public void signUp(MemberSignUpRequest memberSignUpRequest) {
        // 비밀번호 암호화
        //String encodedPassword = passwordEncoder.encode(memberSignUpRequest.getPassword());
//        System.out.println("memberSignUpRequest = " + memberSignUpRequest.toString());
        // 암호화된 비밀번호를 포함한 Member 엔티티 생성
        Member member = Member.saveToEntity(memberSignUpRequest);
        //member.setPassword(encodedPassword); // 암호화된 비밀번호 설정

        memberRepository.save(member);
    }
}
