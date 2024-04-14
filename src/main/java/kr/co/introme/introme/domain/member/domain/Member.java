package kr.co.introme.introme.domain.member.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

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
    @Email
    private String email;

    @Column
    private String name;

    @Column
    private String organization;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column
    private String url;

    @Column
    @DateTimeFormat
    private Date birthday;

    @Column
    private String password;

    public static Member saveToEntity(MemberSignUpRequest memberSignUpRequest) {
        Member member = new Member();
        member.setEmail(memberSignUpRequest.getEmail());
        member.setPassword(memberSignUpRequest.getPassword());
        member.setName(memberSignUpRequest.getName());
        member.setOrganization(memberSignUpRequest.getOrganization());
        member.setPhoneNumber(memberSignUpRequest.getPhoneNumber());
        member.setUrl(memberSignUpRequest.getUrl());
        member.setBirthday(memberSignUpRequest.getBirthday());
        System.out.println("saveToEntity: " + member.toString());
        return member;
    }

}
