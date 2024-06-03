package kr.co.introme.introme.domain.card.repository;

import kr.co.introme.introme.domain.card.domain.SharedCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SharedCardRepository extends JpaRepository<SharedCard, Long> {
    @Query("SELECT sc FROM SharedCard sc JOIN FETCH sc.card WHERE sc.sharedWith.id = :memberId")
    List<SharedCard> findSharedCardsBySharedWithId(@Param("memberId") Long memberId);
}
