package kr.co.introme.introme.domain.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardPostRequest {
    //작성자 id
    private Long author;
    //제목
    private String title;
    //내용
    private String content;
}
