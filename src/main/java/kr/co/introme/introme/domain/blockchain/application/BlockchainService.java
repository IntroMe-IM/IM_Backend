package kr.co.introme.introme.domain.blockchain.application;

import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.blockchain.domain.Block;
import kr.co.introme.introme.domain.blockchain.repository.BlockRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockchainService {
    private final BlockRepository blockRepository;
    private final MemberRepository memberRepository;

    public List<Block> getBlockchainByMemberId(Long memberId) {
        return blockRepository.findByMemberId(memberId);
    }

    @Transactional
    public void addBlock(Long memberId, int contribution) {
        List<Block> blockchain = getBlockchainByMemberId(memberId);
        Member member = memberRepository.findById(memberId).get();
        Block newBlock;
        if (blockchain.isEmpty()) {
            newBlock = new Block("0", member, contribution);
        } else {
            Block latestBlock = blockchain.get(blockchain.size() - 1);
            newBlock = new Block(latestBlock.getHash(), member, contribution);
        }
        blockRepository.save(newBlock);
    }

    @Transactional
    public boolean isBlockchainValid(Long memberId) {
        List<Block> blockchain = getBlockchainByMemberId(memberId);
        for (int i = 1; i < blockchain.size(); i++) {
            Block currentBlock = blockchain.get(i);
            Block previousBlock = blockchain.get(i - 1);

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                return false;
            }
        }
        return true;
    }
}
