package kr.co.introme.introme.domain.team.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.introme.introme.domain.team.application.InviteTeamService;
import kr.co.introme.introme.domain.team.application.TeamBuildService;
import kr.co.introme.introme.domain.team.application.TeamPostService;
import kr.co.introme.introme.domain.team.application.TeamUpdateService;
import kr.co.introme.introme.domain.team.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "팀 API", description = "팀 빌드, 수정, 조회 API")
@RestController
@RequestMapping("/v1/team")
@RequiredArgsConstructor
public class TeamApi {
    private final TeamBuildService teamBuildService;
    private final InviteTeamService inviteTeamService;
    private final TeamUpdateService teamUpdateService;
    private final TeamPostService teamPostService;

    @Operation(summary = "팀 생성", description = "팀을 생성합니다.")
    @PostMapping("/build")
    public ResponseEntity<String> build(@RequestBody TeamBuildRequest teamBuildRequest) {
        teamBuildService.teamBuild(teamBuildRequest);
        return ResponseEntity.ok("빌드 완료");
    }

    @Operation(summary = "팀원 초대", description = "팀원을 팀에 초대합니다.")
    @GetMapping("/invite/{hashUrl}/{memberId}")
    public ResponseEntity<String> invite(@PathVariable String hashUrl, @PathVariable Long memberId) {
        inviteTeamService.invite(hashUrl, memberId);
        return ResponseEntity.ok("팀원 초대완료!");
    }

    @Operation(summary = "팀 프로젝트 종료", description = "팀의 프로젝트 종료 기간에 스탬핑합니다.")
    @PostMapping("/terminate")
    public ResponseEntity<String> terminate(@RequestBody TeamTerminateRequest teamTerminateRequest) {
        String result = teamUpdateService.terminate(teamTerminateRequest);
        if (result.isEmpty()) {
            return ResponseEntity.ok("존재하지 않는 팀입니다.");
        }
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "팀 URL 공유", description = "팀에 초대할떄 필요한 Hash-uri를 반환합니다.")
    @GetMapping("/get-url/{teamId}")
    public ResponseEntity<String> share(@PathVariable Long teamId){
        String result = inviteTeamService.sharedUrl(teamId);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "팀 스페이스 목록", description = "자신이 속한 팀이나 운영하고 있는 팀의 목록을 반환합니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<List<TeamPostResponse>> getTeam(@PathVariable Long memberId){
        List<TeamPostResponse> lists = teamPostService.getTeamList(memberId);
        return ResponseEntity.ok(lists);
    }

    @Operation(summary = "팀 수정", description = "설명이나 팀 이름을 수정합니다.")
    @PutMapping("/{teamId}")
    public ResponseEntity<String> updateTeam(@PathVariable Long teamId, @RequestBody TeamUpdateRequest teamUpdateRequest){
        teamUpdateService.update(teamId, teamUpdateRequest);
        return ResponseEntity.ok("수정완료!");
    }

    @Operation(summary = "팀 상세", description = "팀의 상세 정보를 반환합니다.")
    @GetMapping("/d/{teamId}")
    public ResponseEntity<TeamDetailResponse> getOneTeam(@PathVariable Long teamId){
        TeamDetailResponse teamDetailResponse = teamPostService.getTeamOne(teamId);
        return ResponseEntity.ok(teamDetailResponse);
    }

//    @GetMapping("/cards/{teamId}")
//    public ResponseEntity<List<CardDTO>> getTeamMembersCards(@PathVariable Long teamId) {
//        List<CardDTO> cards = memberService.getTeamMembersCards(teamId);
//        return ResponseEntity.ok(cards);
//    }
}
