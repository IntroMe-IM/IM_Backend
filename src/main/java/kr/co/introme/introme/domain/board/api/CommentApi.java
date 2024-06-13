package kr.co.introme.introme.domain.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.introme.introme.domain.board.application.CommentPostService;
import kr.co.introme.introme.domain.board.domain.Comment;
import kr.co.introme.introme.domain.board.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "댓글 API", description = "댓글 조회, 댓글 작성")
@Controller
@RequestMapping("/v1/comment")
@RequiredArgsConstructor
public class CommentApi {
    private final CommentPostService commentPostService;

    @Operation(summary = "댓글 작성", description = "댓글 작성 정보를 저장합니다.")
    @PostMapping("/")
    public ResponseEntity<String> post(@RequestBody CommentPostRequest commentPostRequest){
        String result = commentPostService.save(commentPostRequest);
        if(result.equals("ok")){
            return ResponseEntity.ok("작성완료!");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "댓글 조회", description = "댓글을 전부 가져옵니다.")
    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentContentResponse>> getBoardPage(@PathVariable Long boardId){
        return ResponseEntity.ok(commentPostService.getComment(boardId));
    }
}
