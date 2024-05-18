package kr.co.introme.introme.domain.team.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.team.dto.TeamBuildRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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
    @Column(nullable = true)
    private String description;

    //프로젝트 이름
    @Column(nullable = false)
    private String project;

    //이미지 경로
    @Column(nullable = true)
    private String image;

    //팀 repo
    @Column
    private String url;

    //생성 날짜
    @Column
    @Temporal(TemporalType.DATE)
    @CreationTimestamp
    private LocalDate createdDate;

    //종료 날짜
    @Column
    private LocalDate terminateDate;

    //fk
    @Column
    private Long ownerId;

    @ManyToMany(mappedBy = "teams", cascade = CascadeType.PERSIST)
    private Set<Member> members = new HashSet<>();


    public static Team saveToEntity(TeamBuildRequest teamBuildRequest) {
        Team team = new Team();
        team.setName(teamBuildRequest.getName());
        team.setDescription(teamBuildRequest.getDescription());
        team.setImage(teamBuildRequest.getImage());
        team.setProject(teamBuildRequest.getProject());
        return team;
    }

    public void addMember(Member member) {
        members.add(member); // Team에 Member 추가
        member.getTeams().add(this); // Member에도 Team 추가
    }

    public boolean isOwner(Long id){
        if(this.getOwnerId().equals(id)){
            return true;
        } else {
            return false;
        }
    }
}
