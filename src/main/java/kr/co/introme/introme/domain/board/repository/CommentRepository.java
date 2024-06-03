package kr.co.introme.introme.domain.board.repository;

import kr.co.introme.introme.domain.board.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List findByBoardId(Long boardId);
}
