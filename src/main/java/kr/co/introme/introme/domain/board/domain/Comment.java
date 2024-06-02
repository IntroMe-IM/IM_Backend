package kr.co.introme.introme.domain.board.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.board.dto.CommentPostRequest;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String content;

    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    @Column
    private LocalDate createAt;

    @Column
    private LocalDate updateAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Member author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    public static Comment saveToEntity(CommentPostRequest commentPostRequest, Member member, Board board) {
        Comment comment = new Comment();
        comment.setContent(commentPostRequest.getContent());
        comment.setAuthor(member);
        comment.setBoard(board);
        return comment;
    }
}
