package kr.co.introme.introme.domain.member.domain;

import jakarta.persistence.*;
import kr.co.introme.introme.domain.blockchain.domain.Block;
import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.domain.Comment;
import kr.co.introme.introme.domain.member.dto.MemberSignUpRequest;
import kr.co.introme.introme.domain.team.domain.Team;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashSet;
import java.util.List;
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

    @Column
    private String email;

    @Column
    private String name;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column
    private String url;

    @Column
    @Temporal(TemporalType.DATE)
    private LocalDate birthday;

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

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Board> boards;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Block> blocks;

    public static Member saveToEntity(MemberSignUpRequest memberSignUpRequest) {
        Member member = new Member();
        member.setEmail(memberSignUpRequest.getEmail());
        member.setPassword(memberSignUpRequest.getPassword());
        member.setName(memberSignUpRequest.getName());
        member.setOrganization(memberSignUpRequest.getOrganization());
        member.setPhoneNumber(memberSignUpRequest.getPhoneNumber());
        member.setBirthday(memberSignUpRequest.getBirthday());
        return member;
    }
    public void calculateAge() {
        if (this.birthday != null) {
            this.age = Period.between(this.birthday, LocalDate.now()).getYears();
        }
    }
}
