package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardDeleteService {
    private final BoardRepository boardRepository;

    @Transactional
    public String delete(Long boardId, Long memberId) {
        Board board = boardRepository.findById(boardId).get();
        if(board != null){
            if(board.getAuthor().getId().equals(memberId)){
                boardRepository.deleteById(boardId);
                return "삭제완료.";
            } else {
                return "작성자가 아닙니다.";
            }
        } else {
            return "없는 게시판입니다.";
        }
    }
}
