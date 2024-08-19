package kr.co.introme.introme.domain.board.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import kr.co.introme.introme.domain.board.application.NoticeService;
import kr.co.introme.introme.domain.board.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "공지사항 API", description = "공지사항 페이지네이션, 작성, 조회")
@Controller
@RequestMapping("/v1/notice")
@RequiredArgsConstructor
public class NoticeApi {
    private final NoticeService noticeService;

    @Operation(summary = "공지사항 작성", description = "공지사항 작성 정보를 저장합니다.")
    @PostMapping("/")
    public ResponseEntity<String> saveNoticeCP(@RequestBody NoticePostRequest request){
        boolean result = noticeService.save(request);
        if(result){
            return ResponseEntity.ok("작성 완료");
        } else {
            return ResponseEntity.ok("비밀번호를 확인하세요");
        }
    }

    @Operation(summary = "공지사항 업데이트", description = "공지사항의 정보를 업데이트합니다,")
    @PutMapping("/")
    public ResponseEntity<String> updateNoticeCP(@RequestBody NoticeUpdateRequest request){
        String response = noticeService.update(request);
        if(response == null){
            return ResponseEntity.ok("업데이트 실패");
        }else {
            return ResponseEntity.ok(response);
        }
    }

    @Operation(summary = "공지사항 삭제", description = "공지사항을 삭제합니다.")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteNoticeCP(@PathVariable Long id, @RequestBody NoticeDeleteRequest request){
        String result = noticeService.delete(id, request);
        if(result.equals("true")){
            return ResponseEntity.ok("삭제 완료");
        } else {
            return ResponseEntity.ok(result);
        }
    }

    @Operation(summary = "공지사항 조회", description = "공지사항 작성 정보를 가져옵니다.")
    @GetMapping("/{id}")
    public ResponseEntity<NoticePostResponse> getNotice(@PathVariable Long id){
        noticeService.hit(id);
        NoticePostResponse response = noticeService.getOne(id);
        if(response != null){
            return ResponseEntity.ok(response);
        } else {
            return null;
        }
    }

    @Operation(summary = "공자사항 페이지네이션", description = "공지사항 전체를 가져옵니다.")
    @PostMapping("/page")
    public ResponseEntity<NoticePageResponse<NoticePostResponse>> getNoticePage(@RequestBody NoticePageRequest noticePageRequest){
        NoticePageResponse<NoticePostResponse> response = noticeService.getNoticeList(noticePageRequest);
        return ResponseEntity.ok(response);
    }

}
