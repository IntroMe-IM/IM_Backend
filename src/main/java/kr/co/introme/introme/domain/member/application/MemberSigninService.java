package kr.co.introme.introme.domain.member.application;

import kr.co.introme.introme.domain.member.dto.MemberSignInRequest;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.domain.member.exception.CustomExceptions.MemberNotFoundException;
import kr.co.introme.introme.domain.member.exception.CustomExceptions.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSigninService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member signIn(MemberSignInRequest memberSignInRequest) {
        Member member = memberRepository.findByEmail(memberSignInRequest.getEmail());
        if (member == null) {
            throw new MemberNotFoundException("회원 정보가 없습니다. 이메일을 확인해 주세요.");
        }

        if (!passwordEncoder.matches(memberSignInRequest.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException("입력하신 비밀번호가 올바르지 않습니다.");
        }

        return member;
    }
}
