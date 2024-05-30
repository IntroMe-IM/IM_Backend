package kr.co.introme.introme.domain.board.dto;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommentPostRequest {
    //댓글
    private String content;
    //작성자
    private Member author;
    //연결된 게시판
    private Board board;
}
