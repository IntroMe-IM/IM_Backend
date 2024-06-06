package kr.co.introme.introme.domain.blockchain.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
//@NoArgsConstructor
@Table(name = "blockchain")
public class Blockchain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "blockchain_id")
    private List<Block> chain = new ArrayList<>();

    public Blockchain() {
        // Genesis block
        chain.add(createGenesisBlock());
    }

    private Block createGenesisBlock() {
        return new Block("0", 0L, 0);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }

    public void addBlock(Block newBlock) {
        newBlock = new Block(getLatestBlock().getHash(), newBlock.getMemberId(), newBlock.getContribution());
        chain.add(newBlock);
    }

    public boolean isChainValid() {
        for (int i = 1; i < chain.size(); i++) {
            Block currentBlock = chain.get(i);
            Block previousBlock = chain.get(i - 1);

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
