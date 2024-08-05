package kr.co.introme.introme.domain.board.repository;

import kr.co.introme.introme.domain.board.domain.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
}
