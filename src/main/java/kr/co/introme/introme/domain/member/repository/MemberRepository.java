package kr.co.introme.introme.domain.member.repository;

import kr.co.introme.introme.domain.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Member findByEmail(String email);

    List<Member> findByIdNot(Long id);
    boolean existsByEmail(String email);

}
