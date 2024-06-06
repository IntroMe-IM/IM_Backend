package kr.co.introme.introme.domain.blockchain.repository;

import kr.co.introme.introme.domain.blockchain.domain.Block;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BlockRepository extends JpaRepository<Block, Long> {
    List<Block> findByMemberId(Long memberId);
}
