package kr.co.introme.introme.domain.board.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.board.dto.BoardPostRequest;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.List;

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
    @UpdateTimestamp
    private LocalDate updateAt;

    @Column
    private Integer hit = 0;

    @Column
    private String imgUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    public static Board saveToEntity(BoardPostRequest boardPostRequest, Member member) {
        Board board = new Board();
        board.setAuthor(member);
        board.setTitle(boardPostRequest.getTitle());
        board.setContent(boardPostRequest.getContent());
        return board;
    }
}
