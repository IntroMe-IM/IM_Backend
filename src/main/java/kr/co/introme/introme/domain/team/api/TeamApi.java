package kr.co.introme.introme.domain.team.api;

import kr.co.introme.introme.domain.team.application.TeamBuildService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/team")
@RequiredArgsConstructor
public class TeamApi {
    private final TeamBuildService teamBuildService;

    @PostMapping("/build")
    public void build() {

    }
}
