package kr.co.introme.introme.domain.team.api;

//import kr.co.introme.introme.domain.team.application.TeamBuildService;
import kr.co.introme.introme.domain.team.application.TeamBuildService;
import kr.co.introme.introme.domain.team.dto.TeamBuildRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/team")
@RequiredArgsConstructor
public class TeamApi {
    private final TeamBuildService teamBuildService;

    @PostMapping("/build")
    public ResponseEntity<String> build(@RequestBody TeamBuildRequest teamBuildRequest) {
        teamBuildService.teamBuild(teamBuildRequest);
        return ResponseEntity.ok("빌드 완료");
    }

    //test
    @GetMapping("/invite/@{code}")
    public String invite(@PathVariable String code) {
        return "null";
    }
}
