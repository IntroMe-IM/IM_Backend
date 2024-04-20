package kr.co.introme.introme.domain.team.api;

import kr.co.introme.introme.domain.team.application.TeamBuildService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/team")
@RequiredArgsConstructor
public class TeamApi {
    private final TeamBuildService teamBuildService;

    @PostMapping("/build")
    public void build() {

    }


    @GetMapping("/invite/@{code}")
    public String invite(@PathVariable String code) {
        return "null";
    }
}
