package kr.co.introme.introme.domain.member.application;

import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.card.repository.CardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CardRepository cardRepository;

    public List<Member> getOtherMembers(Long memberId) {
        return memberRepository.findByIdNot(memberId);
    }

    public void shareCard(Long ownerId, Long sharedWithId) {
        Optional<Member> owner = memberRepository.findById(ownerId);
        Optional<Member> sharedWith = memberRepository.findById(sharedWithId);

        if (owner.isPresent() && sharedWith.isPresent()) {
            Card card = new Card();
            card.setOwner(owner.get());
            card.setSharedWith(sharedWith.get());
            cardRepository.save(card);
        } else {
            throw new RuntimeException("Member not found");
        }
    }

    public List<Card> getSharedCards(Long memberId) {
        return cardRepository.findBySharedWithId(memberId);
    }
}
