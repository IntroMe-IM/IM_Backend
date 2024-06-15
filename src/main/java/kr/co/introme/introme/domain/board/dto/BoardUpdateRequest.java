package kr.co.introme.introme.domain.board.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class BoardUpdateRequest {
    private String title;
    private String content;
    private LocalDate updateAt;
    private String imgUrl;
    private Long authorId;
}
