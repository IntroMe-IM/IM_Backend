package kr.co.introme.introme.domain.team.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.team.dto.TeamBuildRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "team")
@ToString
@NoArgsConstructor
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //팀 이름
    @Column(nullable = false)
    private String name;

    //프로젝트 설명
    @Column()
    private String description;

    //프로젝트 이름
    @Column
    private String project;

    //이미지 경로
    @Column
    private String image;

    //팀 repo
    @Column
    private String url;

    //생성 날짜
    @Column
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    //fk
    @Column
    private Long ownerId;


}
