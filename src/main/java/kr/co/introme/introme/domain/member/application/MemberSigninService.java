package kr.co.introme.introme.domain.member.application;

import kr.co.introme.introme.domain.member.dto.MemberResponse;
import kr.co.introme.introme.domain.member.dto.MemberSignInRequest;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.domain.member.exception.CustomExceptions.MemberNotFoundException;
import kr.co.introme.introme.domain.member.exception.CustomExceptions.InvalidPasswordException;

import kr.co.introme.introme.global.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MemberSigninService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public Map<String, Object> signIn(MemberSignInRequest memberSignInRequest) {
        Member member = memberRepository.findByEmail(memberSignInRequest.getEmail());
        if (member == null) {
            throw new MemberNotFoundException("회원 정보가 없습니다. 이메일을 확인해 주세요.");
        }

        if (!passwordEncoder.matches(memberSignInRequest.getPassword(), member.getPassword())) {
            throw new InvalidPasswordException("입력하신 비밀번호가 올바르지 않습니다.");
        }

        String token = jwtTokenProvider.generateToken(member);

        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("member", new MemberResponse(member));
        return response;
    }

    public MemberResponse findMember(Long id){
        Member member = memberRepository.findById(id).get();
        if(member != null){
            MemberResponse memberResponse = new MemberResponse(member);
            return memberResponse;
        }
        return null;
    }
}
