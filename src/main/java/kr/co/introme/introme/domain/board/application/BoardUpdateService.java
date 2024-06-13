package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.dto.BoardUpdateRequest;
import kr.co.introme.introme.domain.board.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardUpdateService {
    private final BoardRepository boardRepository;

    public String updateBoard(Long boardId, BoardUpdateRequest boardUpdateRequest) {
        Board board = boardRepository.findById(boardId).get();
        if(board != null){
            board.setTitle(boardUpdateRequest.getTitle());
            board.setContent(boardUpdateRequest.getContent());
            board.setUpdateAt(boardUpdateRequest.getUpdateAt());
            board.setImgUrl(boardUpdateRequest.getImgUrl());
            boardRepository.save(board);
            return "수정완료";
        } else {
            return "없는 게시판입니다.";
        }
    }
}
