package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.domain.Comment;
import kr.co.introme.introme.domain.board.dto.CommentPostRequest;
import kr.co.introme.introme.domain.board.repository.BoardRepository;
import kr.co.introme.introme.domain.board.repository.CommentRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentPostService {
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;


    public String save(CommentPostRequest commentPostRequest) {
        Member member = memberRepository.findById(commentPostRequest.getAuthor()).get();
        Board board = boardRepository.findById(commentPostRequest.getBoard()).get();
        if(member != null && board != null){
            commentRepository.save(Comment.saveToEntity(commentPostRequest, member, board));
            return "ok";
        } else {
            return "Can Not Found Member or Board";
        }
    }
}
