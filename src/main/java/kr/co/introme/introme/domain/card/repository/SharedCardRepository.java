package kr.co.introme.introme.domain.card.repository;

import kr.co.introme.introme.domain.card.domain.SharedCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SharedCardRepository extends JpaRepository<SharedCard, Long> {
    List<SharedCard> findBySharedWithId(Long sharedWithId);
}
