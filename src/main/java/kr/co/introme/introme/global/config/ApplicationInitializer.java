package kr.co.introme.introme.config;

import kr.co.introme.introme.domain.card.domain.Card;
import kr.co.introme.introme.domain.card.repository.CardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationInitializer {

    private final MemberRepository memberRepository;
    private final CardRepository cardRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {
            if (!memberRepository.existsByEmail("user1@introme.kr")) {
                Member testUser = new Member();
                testUser.setEmail("user1@introme.kr");
                testUser.setName("User1");
                testUser.setPhoneNumber("010-1234-5678");
                testUser.setPassword(passwordEncoder.encode("password")); // 암호화된 비밀번호를 사용
                memberRepository.save(testUser);

                Card testUserCard = new Card();
                testUserCard.setOwner(testUser);
                testUserCard.setName(testUser.getName() + "'s Card");
                testUserCard.setDescription("Default description");
                cardRepository.save(testUserCard);
            }

            if (!memberRepository.existsByEmail("user2@introme.kr")) {
                Member testUser = new Member();
                testUser.setEmail("user2@introme.kr");
                testUser.setName("User2");
                testUser.setPhoneNumber("010-1234-5670");
                testUser.setPassword(passwordEncoder.encode("password")); // 암호화된 비밀번호를 사용
                memberRepository.save(testUser);

                Card testUserCard = new Card();
                testUserCard.setOwner(testUser);
                testUserCard.setName(testUser.getName() + "'s Card");
                testUserCard.setDescription("Default description");
                cardRepository.save(testUserCard);
            }

            if (!memberRepository.existsByEmail("user3@introme.kr")) {
                Member testUser = new Member();
                testUser.setEmail("user3@introme.kr");
                testUser.setName("User3");
                testUser.setPhoneNumber("010-1234-5671");
                testUser.setPassword(passwordEncoder.encode("password")); // 암호화된 비밀번호를 사용
                memberRepository.save(testUser);

                Card testUserCard = new Card();
                testUserCard.setOwner(testUser);
                testUserCard.setName(testUser.getName() + "'s Card");
                testUserCard.setDescription("Default description");
                cardRepository.save(testUserCard);
            }

            if (!memberRepository.existsByEmail("admin@introme.kr")) {
                Member adminUser = new Member();
                adminUser.setEmail("admin@introme.kr");
                adminUser.setName("Admin User");
                adminUser.setPhoneNumber("010-9915-7741");
                adminUser.setPassword(passwordEncoder.encode("adminpassword")); // 암호화된 비밀번호를 사용
                memberRepository.save(adminUser);

                Card adminUserCard = new Card();
                adminUserCard.setOwner(adminUser);
                adminUserCard.setName(adminUser.getName() + "'s Card");
                adminUserCard.setDescription("Default description");
                cardRepository.save(adminUserCard);
            }
        };
    }
}
