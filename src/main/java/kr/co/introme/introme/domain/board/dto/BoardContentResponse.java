package kr.co.introme.introme.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardContentResponse {
    private Long id;
    private String title;
    private String content;
    private LocalDate createAt;
    private LocalDate updateAt;
    private Integer hit;
    private String imgUrl;
    private Long author;
}
