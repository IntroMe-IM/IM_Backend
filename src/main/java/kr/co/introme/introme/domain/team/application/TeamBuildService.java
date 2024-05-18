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

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        // Generate team URL using hash
        String hashUrl = generateHashUrl(team.getId(), userId);
        team.setUrl(hashUrl);

        // Team save
        teamRepository.save(team);

        // Add member to team
        team.addMember(member);

        // Save team again to update the relationship
        teamRepository.save(team);
    }

    private String generateHashUrl(Long teamId, Long ownerId) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            String text = teamId + ":" + ownerId;
            byte[] hash = digest.digest(text.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash URL", e);
        }
    }
}
