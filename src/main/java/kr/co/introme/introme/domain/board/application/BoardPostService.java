package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.dto.BoardPostRequest;
import kr.co.introme.introme.domain.board.repository.BoardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardPostService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public String save(BoardPostRequest boardPostRequest) {

        Member member = memberRepository.findById(boardPostRequest.getAuthor()).get();
        if(member != null){
            boardRepository.save(Board.saveToEntity(boardPostRequest, member));
            return "ok";
        } else {
            return "Not Found Member";
        }
    }

    public String hit(Long boardId) {
        Board board = boardRepository.findById(boardId).get();

        Integer count = board.getHit() + 1;
        board.setHit(count);
        boardRepository.save(board);
        return count.toString();
    }
}
