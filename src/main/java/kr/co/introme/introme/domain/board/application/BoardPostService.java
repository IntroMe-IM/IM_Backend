package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.dto.BoardPostRequest;
import kr.co.introme.introme.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardPostService {
    private final BoardRepository boardRepository;

    public void save(BoardPostRequest boardPostRequest) {
        boardRepository.save(Board.saveToEntity(boardPostRequest));
    }

    public String hit(Long boardId) {
        Board board = boardRepository.findById(boardId).get();

        Integer count = board.getHit() + 1;
        board.setHit(count);
        boardRepository.save(board);
        return count.toString();
    }
}
