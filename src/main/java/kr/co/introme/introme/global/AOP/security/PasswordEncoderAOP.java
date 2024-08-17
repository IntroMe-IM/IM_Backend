package kr.co.introme.introme.global.AOP.security;

import kr.co.introme.introme.domain.board.dto.NoticeDeleteRequest;
import kr.co.introme.introme.domain.board.dto.NoticePostRequest;
import kr.co.introme.introme.domain.board.dto.NoticeUpdateRequest;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.global.config.ArgonEncoder;
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
    private final ArgonEncoder codeEncoder;

    @Before("execution(* kr.co.introme.introme.domain.member.api.MemberApi.save(..))")
    public void encPwd(JoinPoint jp) throws Exception {
        Object[] args = jp.getArgs();
        MemberSignUpRequest member = (MemberSignUpRequest) args[0];
        String passwordEnc = passwordEncoder.encode(member.getPassword());
        member.setPassword(passwordEnc);
    }

    @Before("execution(* kr.co.introme.introme.domain.board.api.NoticeApi.*CP(..))")
    public void encCode(JoinPoint jp) throws Exception{
        Object[] args = jp.getArgs();

        for (Object arg : args) {
            if (arg instanceof NoticePostRequest) {
                // NoticePostRequest 처리
                NoticePostRequest request = (NoticePostRequest) arg;
                String origin = request.getCode();
                if(codeEncoder.validateCode(origin)){
                    request.setCode("pass");
                } else {
                    request.setCode("false");
                }
            } else if (arg instanceof NoticeUpdateRequest) {
                // NoticeUpdateRequest 처리
                NoticeUpdateRequest request = (NoticeUpdateRequest) arg;
                String origin = request.getCode();
                if(codeEncoder.validateCode(origin)){
                    request.setCode("pass");
                } else {
                    request.setCode("false");
                }
            } else if (arg instanceof NoticeDeleteRequest) {
                // String 처리
                NoticeDeleteRequest request = (NoticeDeleteRequest) arg;
                String origin = request.getCode();
                if(codeEncoder.validateCode(origin)){
                    request.setCode("pass");
                } else {
                    request.setCode("false");
                }
            } else {

            }
        }
    }

}
