package kr.co.introme.introme.domain.member.application;

import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.card.repository.CardRepository;
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
    private final CardRepository cardRepository;

    // private final PasswordEncoder passwordEncoder; // PasswordEncoder 주입

    @Transactional
    public void signUp(MemberSignUpRequest memberSignUpRequest) {
        // 비밀번호 암호화
        // String encodedPassword = passwordEncoder.encode(memberSignUpRequest.getPassword());
        // System.out.println("memberSignUpRequest = " + memberSignUpRequest.toString());
        // 암호화된 비밀번호를 포함한 Member 엔티티 생성
        Member member = Member.saveToEntity(memberSignUpRequest);
        // member.setPassword(encodedPassword); // 암호화된 비밀번호 설정

        memberRepository.save(member);
        // 회원 가입 후 Card 생성
        Card card = new Card();
        card.setOwner(member);
        card.setName(member.getName() + "'s Card");  // 기본 카드 이름 설정
        card.setDescription("Default description");  // 기본 설명 설정
        card.setSharedWith(member);  // 임시로 owner와 동일하게 설정
        cardRepository.save(card);
    }
}
