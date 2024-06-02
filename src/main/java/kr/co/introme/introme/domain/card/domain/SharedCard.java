package kr.co.introme.introme.domain.card.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SharedCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "card_id", nullable = false)
    private Card card;

    @ManyToOne
    @JoinColumn(name = "shared_with_id", nullable = false)
    private Member sharedWith;
}
