package kr.co.introme.introme.domain.board.api;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.introme.introme.domain.board.application.BoardPostService;
import kr.co.introme.introme.domain.board.dto.BoardContentResponse;
import kr.co.introme.introme.domain.board.dto.BoardPageRequest;
import kr.co.introme.introme.domain.board.dto.BoardPageResponse;
import kr.co.introme.introme.domain.board.dto.BoardPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/v1/board")
@RequiredArgsConstructor
public class BoardApi {
    private final BoardPostService boardPostService;

    @Operation(summary = "게시글 작성", description = "게시글 작성 정보를 저장합니다.")
    @PostMapping("/")
    public ResponseEntity<String> post(@RequestBody BoardPostRequest boardPostRequest){
        String result = boardPostService.save(boardPostRequest);
        if (result.equals("ok")){
            return ResponseEntity.ok("작성완료!");
        } else {
            return ResponseEntity.ok(result);
        }

    }

    @Operation(summary = "게시글 조회수", description = "조휘수를 카운팅합니다.")
    @GetMapping("/{board_id}")
    public ResponseEntity<String> hit(@PathVariable Long board_id){
        String allHit = boardPostService.hit(board_id);
        return ResponseEntity.ok("총 조회수 = " + allHit + ".");
    }

    @Operation(summary = "게시글 요청", description = "cursor를 이용해 무한 스크롤 방식의 게시판")
    @PostMapping("/page")
    public ResponseEntity<BoardPageResponse<BoardContentResponse>> getBoardPage(@RequestBody BoardPageRequest boardPageRequest){
        return ResponseEntity.ok(boardPostService.getBoards(boardPageRequest.getPage(), boardPageRequest.getSize()));
    }
}
