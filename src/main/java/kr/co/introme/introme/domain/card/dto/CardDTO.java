package kr.co.introme.introme.domain.card.dto;

import kr.co.introme.introme.domain.card.domain.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String name;
    private String description;
    private String ownerName;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.description = card.getDescription();
        this.ownerName = card.getOwner().getName();
    }
}
