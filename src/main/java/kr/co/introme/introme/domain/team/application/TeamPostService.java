package kr.co.introme.introme.domain.team.application;

import kr.co.introme.introme.domain.board.dto.BoardContentResponse;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.dto.TeamPostResponse;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TeamPostService {
    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public List<TeamPostResponse> getTeamList(Long memberId) {
        Member member = memberRepository.findById(memberId).get();
        Set<Team> team = member.getTeams();
        List<TeamPostResponse> teamResponses = team.stream()
                .map(teams -> new TeamPostResponse(
                        teams.getId(),
                        teams.getName(),
                        teams.getOwnerId(),
                        teams.getTerminateDate() != null,
                        teams.getCreatedDate(),
                        teams.getTerminateDate(),
                        teams.getMembers().stream()
                                .map(Member::getName)
                                .collect(Collectors.toList())
                ))
                .collect(Collectors.toList());

        return teamResponses;
    }
}