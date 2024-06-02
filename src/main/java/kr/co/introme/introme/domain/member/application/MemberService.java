package kr.co.introme.introme.domain.member.application;

import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.card.dto.CardDTO;
import kr.co.introme.introme.domain.card.repository.CardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final CardRepository cardRepository;
    private final TeamRepository teamRepository;

    public List<Member> getOtherMembers(Long memberId) {
        return memberRepository.findByIdNot(memberId);
    }

    public void shareCard(Long ownerId, Long sharedWithId) {
        Optional<Member> owner = memberRepository.findById(ownerId);
        Optional<Member> sharedWith = memberRepository.findById(sharedWithId);

        if (owner.isPresent() && sharedWith.isPresent()) {
            // 소유자의 명함 가져오기
            List<Card> cards = cardRepository.findByOwnerId(ownerId);

            if (cards.isEmpty()) {
                throw new RuntimeException("공유할 명함이 없습니다.");
            }

            // 첫 번째 카드를 공유함으로 설정하고 저장
            Card card = cards.get(0);
            card.setSharedWith(sharedWith.get());
            card.setShared(true);
            cardRepository.save(card);
        } else {
            throw new RuntimeException("Member not found");
        }
    }

    public List<CardDTO> getSharedCards(Long memberId) {
        List<Card> sharedCards = cardRepository.findBySharedWithId(memberId);
        return sharedCards.stream()
                .map(CardDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public List<CardDTO> getTeamMembersCards(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            List<Long> memberIds = team.getMembers().stream().map(Member::getId).collect(Collectors.toList());
            List<Card> cards = cardRepository.findByOwnerIdIn(memberIds);
            return cards.stream()
                    .map(card -> new CardDTO(card))
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Team not found");
        }
    }
}
