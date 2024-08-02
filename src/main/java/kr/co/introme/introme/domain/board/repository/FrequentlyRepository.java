package kr.co.introme.introme.domain.board.repository;

import kr.co.introme.introme.domain.board.domain.Frequently;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FrequentlyRepository extends JpaRepository<Long, Frequently> {
}
