package kr.co.introme.introme.domain.board.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.board.dto.BoardPostRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "board")
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private String content;

    @Column
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private LocalDate createAt;

    @Column
    private LocalDate updateAt;

    @Column
    private Integer hit;

    @Column
    private String img_url;

    @Column(name = "author_id", nullable = false)
    private Long author;

    public static Board saveToEntity(BoardPostRequest boardPostRequest) {
        Board board = new Board();
        board.setAuthor(boardPostRequest.getAuthor());
        board.setTitle(boardPostRequest.getTitle());
        board.setContent(boardPostRequest.getContent());
        return board;
    }
}
