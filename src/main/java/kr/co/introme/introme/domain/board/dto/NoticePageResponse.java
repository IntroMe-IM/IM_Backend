package kr.co.introme.introme.domain.board.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class NoticePageResponse<T> {
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private List<T> content;
    private boolean first;
    private boolean last;
}
