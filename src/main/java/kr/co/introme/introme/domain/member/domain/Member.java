package kr.co.introme.introme.domain.member.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Table
@ToString
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String password;

    public static Member saveToEntity(MemberSignUpRequest memberSignUpRequest) {
        Member member = new Member();
        member.setEmail(memberSignUpRequest.getEmail());
        member.setPassword(memberSignUpRequest.getPassword());
        System.out.println("메소드 실행됨");
        return member;
    }
}
