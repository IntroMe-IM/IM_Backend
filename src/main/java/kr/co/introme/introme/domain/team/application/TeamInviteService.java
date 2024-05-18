package kr.co.introme.introme.domain.team.application;

import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.dto.TeamInviteRequest;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TeamInviteService {
    private final TeamRepository teamRepository;

    public void invite(String code) {
    }

    public void generate(TeamInviteRequest teamInviteRequest) {
        Team team = teamRepository.findById(teamInviteRequest.getTeam_id()).get();
        if(team.isOwner(teamInviteRequest.getOwner_id())){
            //checking team leader
        }else{
            //retun null
        }
    }
}
