package kr.co.introme.introme.domain.card.dto;

import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String name;
    private String phoneNumber;
    private String company;

    public CardDTO(Card card) {
        this.id = card.getId();
        Member owner = card.getOwner();
        this.name = owner.getName();
        this.phoneNumber = owner.getPhoneNumber();
        this.company = owner.getOrganization();
    }
}
