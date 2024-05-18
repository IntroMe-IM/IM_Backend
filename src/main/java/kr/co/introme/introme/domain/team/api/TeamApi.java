package kr.co.introme.introme.domain.team.api;

import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.member.application.MemberService;
import kr.co.introme.introme.domain.team.application.InviteTeamService;
import kr.co.introme.introme.domain.team.application.TeamBuildService;
import kr.co.introme.introme.domain.team.application.TeamUpdateService;
import kr.co.introme.introme.domain.team.dto.TeamBuildRequest;
import kr.co.introme.introme.domain.team.dto.TeamTerminateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/team")
@RequiredArgsConstructor
public class TeamApi {
    private final TeamBuildService teamBuildService;
    private final InviteTeamService inviteTeamService;
    private final TeamUpdateService teamUpdateService;
    private final MemberService memberService;

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

    @PostMapping("/terminate")
    public ResponseEntity<String> terminate(@RequestBody TeamTerminateRequest teamTerminateRequest) {
        String result = teamUpdateService.terminate(teamTerminateRequest);
        if (result.isEmpty()) {
            return ResponseEntity.ok("존재하지 않는 팀입니다.");
        }
        return ResponseEntity.ok(result);
    }

    @GetMapping("/cards/{teamId}")
    public ResponseEntity<List<Card>> getTeamMembersCards(@PathVariable Long teamId) {
        List<Card> cards = memberService.getTeamMembersCards(teamId);
        return ResponseEntity.ok(cards);
    }
}
