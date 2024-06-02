package kr.co.introme.introme.domain.board.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class CommentContentResponse {
    private Long id;
    private String content;
    private LocalDate createAt;
    private LocalDate updateAt;
    private Long Author;
    private Long Board;
}
