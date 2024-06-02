package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.dto.BoardContentResponse;
import kr.co.introme.introme.domain.board.dto.BoardPageRequest;
import kr.co.introme.introme.domain.board.dto.BoardPageResponse;
import kr.co.introme.introme.domain.board.dto.BoardPostRequest;
import kr.co.introme.introme.domain.board.repository.BoardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public BoardPageResponse<BoardContentResponse> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Board> boardPage = boardRepository.findAll(pageable);
        List<BoardContentResponse> content = boardPage.getContent().stream()
                .map(board -> new BoardContentResponse(
                        board.getId(),
                        board.getTitle(),
                        board.getContent(),
                        board.getCreateAt(),
                        board.getUpdateAt(),
                        board.getHit(),
                        board.getImgUrl(),
                        board.getAuthor().getId()
                )).collect(Collectors.toList());
        return new BoardPageResponse<>(
                boardPage.getTotalPages(),
                boardPage.getTotalElements(),
                boardPage.getSize(),
                boardPage.getNumber(),
                content,
                boardPage.isFirst(),
                boardPage.isLast()
        );
    }
}
