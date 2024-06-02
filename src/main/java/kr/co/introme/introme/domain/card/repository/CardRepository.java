package kr.co.introme.introme.domain.card.repository;

import kr.co.introme.introme.domain.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByOwnerId(Long ownerId);


}
