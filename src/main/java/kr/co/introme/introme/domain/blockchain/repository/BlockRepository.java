package kr.co.introme.introme.domain.blockchain.repository;

import kr.co.introme.introme.domain.blockchain.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlockRepository extends JpaRepository<Block, Long> {
}
