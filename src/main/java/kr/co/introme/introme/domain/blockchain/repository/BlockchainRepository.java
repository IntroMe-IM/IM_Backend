package kr.co.introme.introme.domain.blockchain.repository;

import kr.co.introme.introme.domain.blockchain.domain.Blockchain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockchainRepository extends JpaRepository<Blockchain, Long> {
}
