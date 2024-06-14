package kr.co.introme.introme.domain.team.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.domain.team.domain.Team;
import kr.co.introme.introme.domain.team.dto.TeamBuildRequest;
import kr.co.introme.introme.domain.team.repository.TeamRepository;
import kr.co.introme.introme.global.NFS.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TeamBuildService {
    private final TeamRepository teamRepository;
    private final MemberRepository memberRepository;
    private final FileStorageService fileStorageService;

    @Transactional
    public void teamBuild(TeamBuildRequest teamBuildRequest, MultipartFile file) {
        Long userId = teamBuildRequest.getOwner();
        Optional<Member> memberOptional = memberRepository.findById(userId);
        if (memberOptional.isEmpty()) {
            throw new EntityNotFoundException("Member not found for ID: " + userId);
        }
        Member member = memberOptional.get();
        Team team = Team.saveToEntity(teamBuildRequest);
        team.setOwnerId(userId);

        if (file != null && !file.isEmpty()) {
            try {
                String filePath = fileStorageService.storeFile(file, "team");
                team.setImage(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file", e);
            }
        }

        teamRepository.save(team);
        team.addMember(member);
        teamRepository.save(team);
    }
}