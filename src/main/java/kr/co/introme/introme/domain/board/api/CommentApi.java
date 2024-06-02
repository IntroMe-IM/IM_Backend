package kr.co.introme.introme.domain.board.api;

import io.swagger.v3.oas.annotations.Operation;
import kr.co.introme.introme.domain.board.application.CommentPostService;
import kr.co.introme.introme.domain.board.dto.CommentPostRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
