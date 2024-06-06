package kr.co.introme.introme.domain.blockchain.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.introme.introme.domain.blockchain.application.BlockchainService;
import kr.co.introme.introme.domain.blockchain.domain.Block;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "블록체인 API", description = "블록 추가, 블록유효성 검사")
@RestController
@RequestMapping("/api/blockchain")
@RequiredArgsConstructor
public class BlockchainApi {
    private final BlockchainService blockchainService;

    @Operation(summary = "block 조회", description = "해당 멤버의 블록을 조회합니다.")
    @GetMapping("/{memberId}")
    public ResponseEntity<List<Block>> getBlockchain(@PathVariable Long memberId) {
        return ResponseEntity.ok(blockchainService.getBlockchainByMemberId(memberId));
    }

    @Operation(summary = "block 추가", description = "해당 멤버의 블록을 추가합니다.")
    @PostMapping("/{memberId}/add-block")
    public ResponseEntity<Void> addBlock(@PathVariable Long memberId, @RequestParam int contribution) {
        blockchainService.addBlock(memberId, contribution);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "block 검증", description = "해당 멤버가 가지고 있는 모든 블록의 유효성을 검사합니다.")
    @GetMapping("/{memberId}/validate")
    public ResponseEntity<Boolean> isBlockchainValid(@PathVariable Long memberId) {
        return ResponseEntity.ok(blockchainService.isBlockchainValid(memberId));
    }
}
