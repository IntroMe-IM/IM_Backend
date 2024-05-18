package kr.co.introme.introme.domain.card.repository;

import kr.co.introme.introme.domain.card.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {
    List<Card> findByOwnerId(Long ownerId);
    List<Card> findBySharedWithId(Long sharedWithId);
    List<Card> findByOwnerIdIn(List<Long> ownerIds);
}
