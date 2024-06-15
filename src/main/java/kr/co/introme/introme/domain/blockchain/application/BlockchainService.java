package kr.co.introme.introme.domain.blockchain.application;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import kr.co.introme.introme.domain.blockchain.dto.BlockResponse;
import kr.co.introme.introme.domain.blockchain.domain.Block;
import kr.co.introme.introme.domain.blockchain.repository.BlockRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BlockchainService {
    private final BlockRepository blockRepository;

    @PersistenceContext
    private EntityManager em;

    public List<BlockResponse> getBlockchainByMemberId(Long memberId) {
        List<Block> blockList = blockRepository.findByMemberId(memberId);
        List<BlockResponse> responese = blockList.stream().map(block -> new BlockResponse(
                block.getId(),
                block.getPreviousHash(),
                block.getMember().getId(),
                block.getContribution(),
                block.getTimeStamp(),
                block.getHash()
        )).toList();
        return responese;
    }

    @Transactional
    public void addBlock(Long memberId, int contribution) {
        // Member 엔티티 조회 (유효성 검증을 위해)
        Member member = em.find(Member.class, memberId);
        if (member == null) {
            throw new IllegalArgumentException("Member not found with ID: " + memberId);
        }

        // 블록체인 가져오기
        List<Block> blockchain = blockRepository.findAll();
        Block newBlock;

        Block latestBlock = blockchain.get(blockchain.size() - 1);
        newBlock = new Block(latestBlock.getHash(), member, contribution);

        // Block 엔티티 영속성 설정 및 저장
        em.persist(newBlock);
    }

    @Transactional
    public boolean isBlockchainValid(Long memberId) {
        List<Block> blockchain = blockRepository.findAll();
        List<Block> blockList = blockRepository.findByMemberId(memberId);
        List<Integer> memberList = blockList.stream().map(block -> block.getId().intValue()).toList();

        if (blockList.isEmpty()) {
            System.out.println("Don't have a Block");
            return false;
        }
        System.out.println("blockList's size = " + blockList.size());
        for (int i = 0; i <= blockList.size(); i++) {
            Block currentBlock = blockchain.get(memberList.get(i)-1);
            Block previousBlock = blockchain.get(memberList.get(i)-2);
            System.out.println("this blockList's index = " + memberList.get(i));
            System.out.println("Block's index = " + blockchain.get(memberList.get(i)-1).getId());

            if (!currentBlock.getHash().equals(currentBlock.calculateHash())) {
                System.out.println("this is now block-------------------------");
                System.out.println("currentBlock Hash = " + currentBlock.getHash());
                System.out.println("calculate Hash result = " + currentBlock.calculateHash());
                System.out.println("------------------------------------------");
                return false;
            }

            if (!currentBlock.getPreviousHash().equals(previousBlock.getHash())) {
                System.out.println("this is previous block---------------------");
                System.out.println("currentBlock Hash = " + previousBlock.getHash());
                System.out.println("calculate Hash result = " + previousBlock.calculateHash());
                System.out.println("------------------------------------------");
                return false;
            }
        }

        return true;
    }
}
