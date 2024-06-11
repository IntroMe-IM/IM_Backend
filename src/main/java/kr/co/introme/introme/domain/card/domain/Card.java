package kr.co.introme.introme.domain.card.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String description;

    @Column String color;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private Member owner;

    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SharedCard> sharedCards = new HashSet<>();
}
