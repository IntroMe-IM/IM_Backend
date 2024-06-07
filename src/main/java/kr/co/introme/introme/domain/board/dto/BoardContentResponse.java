package kr.co.introme.introme.domain.board.dto;

import kr.co.introme.introme.domain.board.domain.Board;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BoardContentResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate createAt;
    private LocalDate updateAt;
    private Integer hit;
    private String imgUrl;
    private Long authorId;
    private String author;

    public static BoardContentResponse boardLists(Long id, String title, LocalDate createAt, LocalDate updateAt, Integer hit, Long authorId, String author) {
        BoardContentResponse content = new BoardContentResponse();
        content.setId(id);
        content.setTitle(title);
        content.setCreateAt(createAt);
        content.setUpdateAt(updateAt);
        content.setHit(hit);
        content.setAuthorId(authorId);
        content.setAuthor(author);
        return content;
    }


    public static BoardContentResponse saveToDTO(Board board) {
        BoardContentResponse boardContentResponse = new BoardContentResponse();
        boardContentResponse.setId(board.getId());
        boardContentResponse.setTitle(board.getTitle());
        boardContentResponse.setContent(board.getContent());
        boardContentResponse.setCreateAt(board.getCreateAt());
        boardContentResponse.setUpdateAt(board.getUpdateAt());
        boardContentResponse.setHit(board.getHit());
        boardContentResponse.setImgUrl(board.getImgUrl());
        boardContentResponse.setAuthorId(board.getAuthor().getId());
        boardContentResponse.setAuthor(board.getAuthor().getName());
        return boardContentResponse;
    }
}
