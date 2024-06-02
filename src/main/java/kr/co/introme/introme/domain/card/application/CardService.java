package kr.co.introme.introme.domain.card.application;

import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.card.domain.SharedCard;
import kr.co.introme.introme.domain.card.dto.CardDTO;
import kr.co.introme.introme.domain.card.repository.CardRepository;
import kr.co.introme.introme.domain.card.repository.SharedCardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CardService {
    private final MemberRepository memberRepository;
    private final CardRepository cardRepository;
    private final SharedCardRepository sharedCardRepository;

    public String getShareUrl(Long memberId) {
        Optional<Member> member = memberRepository.findById(memberId);
        if (member.isPresent()) {
            return member.get().getUrl();
        } else {
            throw new RuntimeException("Member not found");
        }
    }

    @Transactional
    public void shareCard(String encodedData, Long sharedWithId) {
        // 해싱된 데이터 디코딩
        Long ownerId = Long.parseLong(new String(Base64.getDecoder().decode(encodedData), StandardCharsets.UTF_8));

        Optional<Member> owner = memberRepository.findById(ownerId);
        Optional<Member> sharedWith = memberRepository.findById(sharedWithId);

        if (owner.isPresent() && sharedWith.isPresent()) {
            List<Card> cards = cardRepository.findByOwnerId(ownerId);

            if (cards.isEmpty()) {
                throw new RuntimeException("공유할 명함이 없습니다.");
            }

            Card card = cards.get(0);
            SharedCard sharedCard = new SharedCard();
            sharedCard.setCard(card);
            sharedCard.setSharedWith(sharedWith.get());
            sharedCardRepository.save(sharedCard);
        } else {
            throw new RuntimeException("Member not found");
        }
    }

    public List<CardDTO> getSharedCards(Long memberId) {
        List<SharedCard> sharedCards = sharedCardRepository.findBySharedWithId(memberId);
        return sharedCards.stream()
                .map(sharedCard -> new CardDTO(sharedCard.getCard()))
                .collect(Collectors.toList());
    }
}
