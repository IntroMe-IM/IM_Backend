package kr.co.introme.introme.domain.blockchain.api;

import kr.co.introme.introme.domain.blockchain.application.BlockchainService;
import kr.co.introme.introme.domain.blockchain.domain.Blockchain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/blockchain")
@RequiredArgsConstructor
public class BlockchainController {
    private final BlockchainService blockchainService;

    @GetMapping("/{id}")
    public ResponseEntity<Blockchain> getBlockchain(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.getBlockchain(id));
    }

    @PostMapping
    public ResponseEntity<Blockchain> createBlockchain() {
        Blockchain newBlockchain = blockchainService.createBlockchain();
        return ResponseEntity.ok(newBlockchain);
    }

    @PostMapping("/{id}/add-block")
    public ResponseEntity<Void> addBlock(@PathVariable Long id, @RequestParam Long memberId, @RequestParam int contribution) {
        blockchainService.addBlock(id, memberId, contribution);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}/validate")
    public ResponseEntity<Boolean> isBlockchainValid(@PathVariable Long id) {
        return ResponseEntity.ok(blockchainService.isBlockchainValid(id));
    }
}
