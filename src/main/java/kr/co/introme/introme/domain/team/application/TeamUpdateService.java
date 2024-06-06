package kr.co.introme.introme.domain.team.application;

import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.dto.TeamPostResponse;
import kr.co.introme.introme.domain.team.dto.TeamTerminateRequest;
import kr.co.introme.introme.domain.team.dto.TeamUpdateRequest;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


@Service
@RequiredArgsConstructor
public class TeamUpdateService {
    private final TeamRepository teamRepository;


    public String terminate(TeamTerminateRequest teamTerminateRequest) {
        //Find Team
        Team team = teamRepository.findById(teamTerminateRequest.getTeamId()).get();
        //Change Only The TeamLeader
        if(team.isOwner(teamTerminateRequest.getOwnerId())){
            //Check Already Terminated
            if(null == team.getTerminateDate()) {
                //Stamped
                LocalDate now = LocalDate.now();
                team.setTerminateDate(now);
                teamRepository.save(team);
                return now.toString();
            }else {
                //Already
                return "이미 종료된 프로젝트 입니다.";
            }
        }else {
            return null;
        }
    }

    @Transactional
    public void update(Long teamId, TeamUpdateRequest teamUpdateRequest) {
        Team team = teamRepository.findById(teamId).get();
        if(team != null){
            team.setName(teamUpdateRequest.getName());
            team.setProject(teamUpdateRequest.getProject());
            team.setDescription(teamUpdateRequest.getDescription());
        }
    }
}
