package kr.co.introme.introme.domain.board.dto;

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
    private Long author;
    //연결된 게시판
    private Long board;
}
