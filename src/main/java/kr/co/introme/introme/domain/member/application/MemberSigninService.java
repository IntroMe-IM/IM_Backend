package kr.co.introme.introme.domain.member.application;

import kr.co.introme.introme.domain.member.dto.MemberSignInRequest;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSigninService {

    private final MemberRepository memberRepository;

    public boolean signIn(MemberSignInRequest memberSignInRequest) {
        Member member = memberRepository.findByEmail(memberSignInRequest.getEmail());
        if (member == null) {
            throw new RuntimeException("회원 정보가 없습니다.");
        }

        // 평문 비밀번호를 사용하는 간단한 비교
        if (!memberSignInRequest.getPassword().equals(member.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return true; // 인증 성공
    }
}
