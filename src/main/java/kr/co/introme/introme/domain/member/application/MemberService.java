package kr.co.introme.introme.domain.member.application;

import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.card.dto.CardDTO;
import kr.co.introme.introme.domain.card.repository.CardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
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

    @Transactional
    public List<CardDTO> getTeamMembersCards(Long teamId) {
        Optional<Team> teamOptional = teamRepository.findById(teamId);
        if (teamOptional.isPresent()) {
            Team team = teamOptional.get();
            List<Long> memberIds = team.getMembers().stream().map(Member::getId).collect(Collectors.toList());
            List<Card> cards = cardRepository.findByOwnerIdIn(memberIds);
            return cards.stream()
                    .map(card -> {
                        CardDTO dto = new CardDTO();
                        dto.setId(card.getId());
                        dto.setName(card.getName());
                        dto.setDescription(card.getDescription());
                        dto.setOwnerName(card.getOwner().getName());
                        return dto;
                    })
                    .collect(Collectors.toList());
        } else {
            throw new RuntimeException("Team not found");
        }
    }

}
