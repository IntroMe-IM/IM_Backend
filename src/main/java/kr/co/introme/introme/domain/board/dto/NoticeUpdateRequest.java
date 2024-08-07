package kr.co.introme.introme.domain.board.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticeUpdateRequest {
    private Long id;

    private String title;

    private String contents;

    private LocalDate updateAt;

    private String img;

    private String code;
}