package kr.co.introme.introme.domain.member.application;

import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.card.repository.CardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class MemberSignupService {

    private final MemberRepository memberRepository;
    private final CardRepository cardRepository;

    @Transactional
    public void signUp(MemberSignUpRequest memberSignUpRequest) {
        Member member = Member.saveToEntity(memberSignUpRequest);
        member.calculateAge();  // 나이를 계산하는 로직 추가
        memberRepository.save(member);

        // URL 생성 및 저장
        String encodedData = Base64.getEncoder().encodeToString(member.getId().toString().getBytes(StandardCharsets.UTF_8));
        String url = "https://introme.co.kr/v1/card/shared-card/" + encodedData;
        member.setUrl(url);
        memberRepository.save(member);

        // 명함 생성
        Card card = new Card();
        card.setOwner(member);
        card.setDescription("Default description");
        cardRepository.save(card);
    }
}
