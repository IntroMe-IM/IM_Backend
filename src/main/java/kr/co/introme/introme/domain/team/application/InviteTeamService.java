package kr.co.introme.introme.domain.team.application;

import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InviteTeamService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void invite(String hashUrl, Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) {
            throw new RuntimeException("Member not found");
        }
        Member member = memberOptional.get();

        Optional<Team> teamOptional = teamRepository.findByUrl(hashUrl);
        if (teamOptional.isEmpty()) {
            throw new RuntimeException("Invalid URL or team not found");
        }
        Team team = teamOptional.get();

        team.addMember(member);
        teamRepository.save(team);
    }

    public String sharedUrl(Long teamId) {
        Team team = teamRepository.findById(teamId).get();
        return team.getUrl();
    }
}
