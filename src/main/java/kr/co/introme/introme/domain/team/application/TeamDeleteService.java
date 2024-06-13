package kr.co.introme.introme.domain.team.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamDeleteService {
    private final TeamRepository teamRepository;

    //persistence check
    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public String delete(Long teamId, Long memberId) {
        Team team = teamRepository.findById(teamId).get();
        if(team != null){
            if(team.isOwner(memberId)){
                //Hash Clear
                team.getMembers().clear();
                //JPA ManyToMany(virtual) table query
                Query deleteTeamMembersQuery = entityManager.createNativeQuery("DELETE FROM team_member WHERE team_id = ?");
                deleteTeamMembersQuery.setParameter(1, teamId);
                deleteTeamMembersQuery.executeUpdate();
                //Delete Team
                teamRepository.deleteById(teamId);
                return "삭제 완료";
            } else {
                return "팀장이 아닙니다.";
            }
        } else {
            return "해당팀은 존재하지 않습니다.";
        }
    }
}
