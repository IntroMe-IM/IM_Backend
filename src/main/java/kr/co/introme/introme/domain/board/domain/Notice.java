package kr.co.introme.introme.domain.board.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.board.dto.NoticePostResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "notice")
@NoArgsConstructor
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String img;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.DATE)
    private LocalDate createAt;

    @Column
    private LocalDate updateAt;

    @Column
    private Integer hit = 0;

}
