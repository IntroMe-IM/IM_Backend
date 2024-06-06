package kr.co.introme.introme.global.config;

import kr.co.introme.introme.domain.blockchain.domain.Block;
import kr.co.introme.introme.domain.blockchain.repository.BlockRepository;
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
    private final BlockRepository blockRepository;

    @Bean
    public CommandLineRunner initDatabase() {
        return args -> {

            if (!memberRepository.existsByEmail("admin@introme.co.kr")) {
                Member adminUser = new Member();
                adminUser.setEmail("admin@introme.co.kr");
                adminUser.setName("Admin User");
                adminUser.setPhoneNumber("01099157740");
                adminUser.setPassword(passwordEncoder.encode("adminpassword"));
                memberRepository.save(adminUser);

                Card adminUserCard = new Card();
                adminUserCard.setOwner(adminUser);
                adminUserCard.setDescription("Default description");
                cardRepository.save(adminUserCard);
            }

            if (!memberRepository.existsByEmail("user1@introme.kr")) {
                Member testUser = new Member();
                testUser.setEmail("user1@introme.kr");
                testUser.setName("User1");
                testUser.setPhoneNumber("01012345678");
                testUser.setPassword(passwordEncoder.encode("password"));
                memberRepository.save(testUser);

                Card testUserCard = new Card();
                testUserCard.setOwner(testUser);
                testUserCard.setDescription("Default description");
                cardRepository.save(testUserCard);
            }

            if (!memberRepository.existsByEmail("user2@introme.kr")) {
                Member testUser = new Member();
                testUser.setEmail("user2@introme.kr");
                testUser.setName("User2");
                testUser.setPhoneNumber("010-1234-5670");
                testUser.setPassword(passwordEncoder.encode("password"));
                memberRepository.save(testUser);

                Card testUserCard = new Card();
                testUserCard.setOwner(testUser);
                testUserCard.setDescription("Default description");
                cardRepository.save(testUserCard);
            }

            if (!memberRepository.existsByEmail("user3@introme.kr")) {
                Member testUser = new Member();
                testUser.setEmail("user3@introme.kr");
                testUser.setName("User3");
                testUser.setPhoneNumber("010-1234-5671");
                testUser.setPassword(passwordEncoder.encode("password"));
                memberRepository.save(testUser);

                Card testUserCard = new Card();
                testUserCard.setOwner(testUser);
                testUserCard.setDescription("Default description");
                cardRepository.save(testUserCard);
            }

            if (blockRepository.count() == 0) {
                Block genesisBlock = new Block("0", "initial_hash_value");
                blockRepository.save(genesisBlock);
            }
        };
    }
}
