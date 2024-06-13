package kr.co.introme.introme.domain.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.introme.introme.domain.board.application.BoardDeleteService;
import kr.co.introme.introme.domain.board.application.BoardPostService;
import kr.co.introme.introme.domain.board.application.BoardUpdateService;
import kr.co.introme.introme.domain.board.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "게시글 API", description = "게시글 페이지네이션, 작성, 조회")
@Controller
@RequestMapping("/v1/board")
@RequiredArgsConstructor
public class BoardApi {
    private final BoardPostService boardPostService;
    private final BoardDeleteService boardDeleteService;
    private final BoardUpdateService boardUpdateService;


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

    @Operation(summary = "게시글 조회", description = "게시글의 상세 정보를 표기하고 조회수를 카운팅합니다.")
    @GetMapping("/{board_id}")
    public ResponseEntity<BoardContentResponse> hit(@PathVariable Long board_id){
        boardPostService.hit(board_id);
        BoardContentResponse response = boardPostService.getOne(board_id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "게시글 수정", description = "게시글을 수정합니다.")
    @PutMapping("/{board_id}")
    public ResponseEntity<String> updating(@PathVariable Long board_id, @RequestBody BoardUpdateRequest boardUpdateRequest){
        String result = boardUpdateService.updateBoard(board_id, boardUpdateRequest);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "게시글 삭제", description = "게시글을 삭제합니다.")
    @DeleteMapping("/{board_id}/{member_id}")
    public ResponseEntity<String> deleteBoard(@PathVariable Long board_id, Long member_id){
        String result = boardDeleteService.delete(board_id, member_id);
        return ResponseEntity.ok(result);
    }

    @Operation(summary = "게시글 페이지네이션", description = "cursor based infinity pagination 입니다.")
    @PostMapping("/page")
    public ResponseEntity<BoardPageResponse<BoardContentResponse>> getBoardPage(@RequestBody BoardPageRequest boardPageRequest){
        return ResponseEntity.ok(boardPostService.getBoards(boardPageRequest.getPage(), boardPageRequest.getSize()));
    }
}
