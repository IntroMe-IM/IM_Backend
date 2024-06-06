package kr.co.introme.introme.domain.blockchain.application;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.blockchain.domain.Block;
import kr.co.introme.introme.domain.blockchain.domain.Blockchain;
import kr.co.introme.introme.domain.blockchain.repository.BlockRepository;
import kr.co.introme.introme.domain.blockchain.repository.BlockchainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
@RequiredArgsConstructor
public class BlockchainService {
    private final BlockchainRepository blockchainRepository;
    private final BlockRepository blockRepository;

    public Blockchain getBlockchain(Long id) {
        return blockchainRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Blockchain not found"));
    }

    public Blockchain createBlockchain() {
        Blockchain blockchain = new Blockchain();
        return blockchainRepository.save(blockchain);
    }

    @Transactional
    public void addBlock(Long blockchainId, Long memberId, int contribution) {
        Blockchain blockchain = getBlockchain(blockchainId);
        Block newBlock = new Block(blockchain.getLatestBlock().getHash(), memberId, contribution);
        blockchain.addBlock(newBlock);
        blockchainRepository.save(blockchain);
    }

    @Transactional
    public boolean isBlockchainValid(Long id) {
        Blockchain blockchain = getBlockchain(id);
        return blockchain.isChainValid();
    }
}
