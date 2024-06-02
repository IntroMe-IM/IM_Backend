package kr.co.introme.introme.domain.card.dto;

import kr.co.introme.introme.domain.card.domain.Card;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CardDTO {
    private Long id;
    private String name;
    private String description;
    private String ownerName;
    private boolean isShared;

    public CardDTO(Card card) {
        this.id = card.getId();
        this.name = card.getName();
        this.description = card.getDescription();
        this.ownerName = card.getOwner().getName();
        this.isShared = card.isShared();
    }
}
