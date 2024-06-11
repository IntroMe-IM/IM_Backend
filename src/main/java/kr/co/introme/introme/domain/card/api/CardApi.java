package kr.co.introme.introme.domain.card.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.introme.introme.domain.card.application.CardService;
import kr.co.introme.introme.domain.card.dto.CardDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "명함 API", description = "명함 공유 및 조회 API")
@RequestMapping("/v1/card")
@RequiredArgsConstructor
public class CardApi {

    private final CardService cardService;

    @Operation(summary = "명함 공유 URL 조회", description = "명함 공유를 위한 URL을 조회합니다.")
    @GetMapping("/get-url/{memberId}")
    public ResponseEntity<String> getShareUrl(@PathVariable Long memberId) {
        String shareUrl = cardService.getShareUrl(memberId);
        return ResponseEntity.ok(shareUrl);
    }

    @Operation(summary = "명함 공유", description = "해싱된 URL을 통해 명함을 다른 회원과 공유합니다.")
    @GetMapping("/shared-card/{encodedData}")
    public ResponseEntity<String> shareCard(@PathVariable String encodedData, @RequestParam Long sharedWithId) {
        cardService.shareCard(encodedData, sharedWithId);
        return ResponseEntity.ok("명함 공유 완료!");
    }

    @Operation(summary = "공유 받은 명함 조회", description = "공유 받은 명함을 조회합니다.")
    @GetMapping("/shared-cards/{memberId}")
    public ResponseEntity<List<CardDTO>> getSharedCards(@PathVariable Long memberId) {
        List<CardDTO> sharedCards = cardService.getSharedCards(memberId);
        return ResponseEntity.ok(sharedCards);
    }

    @Operation(summary = "명합 업데이트", description = "명함의 정보를 변경합니다.")
    @PutMapping("/card/{memberId}")
    public ResponseEntity<String> updateCard(@PathVariable Long memberId, @RequestBody CardDTO cardDTO){
        Boolean result = cardService.changeCard(memberId, cardDTO);
        if(result){
            return ResponseEntity.ok("업데이트 완료");
        }else {
            return ResponseEntity.ok("입력값을 확인하세요.");
        }
    }

    @Operation(summary = "명합 요청", description = "명함의 정보를 반환합니다.")
    @GetMapping("/card/{memberId}")
    public ResponseEntity<CardDTO> getCard(@PathVariable Long memberId){
        CardDTO cardDTO = cardService.getCardInfo(memberId);
        return ResponseEntity.ok(cardDTO);
    }
}
