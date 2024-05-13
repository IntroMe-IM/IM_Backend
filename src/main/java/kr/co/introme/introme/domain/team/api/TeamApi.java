package kr.co.introme.introme.domain.team.api;

import kr.co.introme.introme.domain.team.application.InviteTeamService;
import kr.co.introme.introme.domain.team.application.TeamBuildService;
import kr.co.introme.introme.domain.team.dto.TeamBuildRequest;
import kr.co.introme.introme.domain.team.dto.TeamInviteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/team")
@RequiredArgsConstructor
public class TeamApi {
    private final TeamBuildService teamBuildService;
    private final InviteTeamService inviteTeamService;

    @PostMapping("/build")
    public ResponseEntity<String> build(@RequestBody TeamBuildRequest teamBuildRequest) {
        teamBuildService.teamBuild(teamBuildRequest);
        return ResponseEntity.ok("빌드 완료");
    }


    @GetMapping("/invite/@{code}")
    public ResponseEntity<String> invite(@PathVariable String code) {
        inviteTeamService.invite(code);
        return ResponseEntity.ok("팀원 초대완료!");
    }
}
