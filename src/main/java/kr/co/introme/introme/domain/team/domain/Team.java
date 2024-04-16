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

    @Column(nullable = false)
    private String name;

    @Column
    private String description;

    @Column
    private String project;

    @Column
    private String image;

    @Column
    private String url;

    @Column
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    @Column
    private Long ownerId;


}
