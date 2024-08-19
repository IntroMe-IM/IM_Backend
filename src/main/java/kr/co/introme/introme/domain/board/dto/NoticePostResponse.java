package kr.co.introme.introme.domain.board.dto;

import kr.co.introme.introme.domain.board.domain.Notice;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class NoticePostResponse {
    private Long id;

    private String title;

    private String contents;

    private LocalDate createAt;

    private LocalDate updateAt;

    private String img;

    private Integer hit;

    public static NoticePostResponse saveToDto(Notice notice) {
        NoticePostResponse response = new NoticePostResponse();
        response.title = notice.getTitle();
        response.contents = notice.getContents();
        response.createAt = notice.getCreateAt();
        response.updateAt = notice.getUpdateAt();
        response.img = notice.getImg();
        response.hit = notice.getHit();
        return response;
    }

    public static NoticePostResponse noticePage(Long id, String title, LocalDate createAt, LocalDate updateAt, Integer hit){
        NoticePostResponse contents = new NoticePostResponse();
        contents.setId(id);
        contents.setTitle(title);
        contents.setCreateAt(createAt);
        contents.setUpdateAt(updateAt);
        contents.setHit(hit);
        return contents;
    }
}
