package kr.co.introme.introme.domain.board.domain;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "FAQ")
@NoArgsConstructor
public class Frequently {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String contents;

    @Column
    private String answer;

    @Column
    private String img;

    @Column
    private LocalDate createAt;

    @Column
    private LocalDate updateAt;

    @Column
    private Integer hit = 0;
}
