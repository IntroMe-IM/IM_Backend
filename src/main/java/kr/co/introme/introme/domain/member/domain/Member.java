package kr.co.introme.introme.domain.member.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.domain.team.domain.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column
    private String url;

    @Column
    @Temporal(TemporalType.DATE)
    private Date birthday;

    @Column(nullable = false)
    private String password;

    @Column
    private int age;

    @Column
    private String mbti;

    @Column
    private String organization;

    @ManyToMany
    @JoinTable(name = "team_member",
            joinColumns = @JoinColumn(name = "member_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id"))
    private Set<Team> teams = new HashSet<>();

    public static Member saveToEntity(MemberSignUpRequest memberSignUpRequest) {
        Member member = new Member();
        member.setEmail(memberSignUpRequest.getEmail());
        member.setPassword(memberSignUpRequest.getPassword());
        member.setName(memberSignUpRequest.getName());
        member.setOrganization(memberSignUpRequest.getOrganization());
        member.setPhoneNumber(memberSignUpRequest.getPhoneNumber());
        member.setUrl(memberSignUpRequest.getUrl());
        member.setBirthday(memberSignUpRequest.getBirthday());
        return member;
    }
}
