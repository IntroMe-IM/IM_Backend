package kr.co.introme.introme.domain.member.application;

import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberSignupService {
    private final MemberRepository memberRepository;

    public void signUp(MemberSignUpRequest memberSignUpRequest) {
        Member member = Member.saveToEntity(memberSignUpRequest);
        memberRepository.save(member);
    }
}
