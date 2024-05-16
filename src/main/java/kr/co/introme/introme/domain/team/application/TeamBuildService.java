package kr.co.introme.introme.domain.team.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.dto.TeamBuildRequest;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamBuildService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void teamBuild(TeamBuildRequest teamBuildRequest) {
        // user's ID
        Long userId = teamBuildRequest.getOwner();

        // Find Member's Entity
        Optional<Member> memberOptional = memberRepository.findById(userId);
        if (memberOptional.isEmpty()) {
            // exception
            throw new EntityNotFoundException("Member not found for ID: " + userId);
        }
        Member member = memberOptional.get();

        // Create Team
        Team team = Team.saveToEntity(teamBuildRequest);
        team.setOwnerId(userId);
        team.setUrl("localhost:8080/invite/" + userId);

        // Team save
        teamRepository.save(team);

        // Add member to team
        team.addMember(member);

        // Save team again to update the relationship
        teamRepository.save(team);
    }


}
