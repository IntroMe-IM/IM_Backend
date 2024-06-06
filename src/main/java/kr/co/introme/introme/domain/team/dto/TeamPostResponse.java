package kr.co.introme.introme.domain.team.dto;

import kr.co.introme.introme.domain.member.domain.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class TeamPostResponse {
    private Long id;
    private String name;
    private Long owner;
    private boolean finish;
    private LocalDate create;
    private LocalDate terminate;
    private List<String> members;

    public TeamPostResponse(Long id, String name, Long owner, boolean finish, LocalDate create, LocalDate terminate, List<String> members) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.finish = finish;
        this.create = create;
        this.terminate = terminate;
        this.members = members;
    }
}
