package kr.co.introme.introme.domain.board.dto;


import kr.co.introme.introme.domain.board.domain.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class NoticePostRequest {
    private String title;

    private String contents;

    private String img;

    private String code;

    public static Notice saveToEntity(NoticePostRequest request) {
        Notice notice = new Notice();
        notice.setTitle(request.getTitle());
        notice.setContents(request.getContents());
        notice.setImg(request.getImg());
        return notice;
    }
}