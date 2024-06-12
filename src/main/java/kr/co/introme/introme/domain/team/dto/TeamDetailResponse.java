package kr.co.introme.introme.domain.team.dto;

import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.team.domain.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class TeamDetailResponse {
    private Long id;
    private String name;
    private String description;
    private String project;
    private String image;
    private LocalDate createdDate;
    private LocalDate terminateDate;
    private Long ownerId;
    private List<String> members;

    public TeamDetailResponse(Team team){
        this.setId(team.getId());
        this.setName(team.getName());
        this.setDescription(team.getDescription());
        this.setProject(team.getProject());
        this.setImage(team.getImage());
        this.setCreatedDate(team.getCreatedDate());
        this.setTerminateDate(team.getTerminateDate());
        this.setOwnerId(team.getOwnerId());
        this.setMembers(
                team.getMembers().stream().map(member -> getName()).toList()
        );
    }
}
