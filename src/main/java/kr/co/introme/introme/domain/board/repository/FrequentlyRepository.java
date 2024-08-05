package kr.co.introme.introme.domain.board.repository;

import kr.co.introme.introme.domain.board.domain.Frequently;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FrequentlyRepository extends JpaRepository<Frequently, Long> {
}
