package kr.co.introme.introme.global.AOP.security;

import kr.co.introme.introme.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class PasswordEncoderAOP {

    private final PasswordEncoder passwordEncoder;

    @Before("execution(* kr.co.introme.introme.domain.member.api.MemberApi.save(..))")
    public void encPwd(JoinPoint jp) throws Exception {
        Object[] args = jp.getArgs();
        Member member = (Member) args[0];
        String passwordEnc = passwordEncoder.encode(member.getPassword());
        member.setPassword(passwordEnc);
    }
}
