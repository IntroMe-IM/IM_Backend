package kr.co.introme.introme.domain.board.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class BoardPageResponse<T> {
    private int totalPages;
    private long totalElements;
    private int size;
    private int number;
    private List<T> content;
    private boolean first;
    private boolean last;
}
