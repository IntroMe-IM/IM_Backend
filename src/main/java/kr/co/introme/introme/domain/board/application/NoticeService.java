package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Notice;
import kr.co.introme.introme.domain.board.dto.NoticePostRequest;
import kr.co.introme.introme.domain.board.dto.NoticePostResponse;
import kr.co.introme.introme.domain.board.dto.NoticeUpdateRequest;
import kr.co.introme.introme.domain.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public boolean save(NoticePostRequest request) {
        //debug code
        if(request.getCode().equals("13579")){
            noticeRepository.save(NoticePostRequest.saveToEntity(request));
            return true;
        } else {
            return false;
        }
    }

    public NoticePostResponse getOne(Long id) {
        Notice notice = noticeRepository.findById(id).get();
        Integer plus = notice.getHit() + 1;
        notice.setHit(plus);
        noticeRepository.save(notice);
        return NoticePostResponse.saveToDto(notice);
    }

    @Transactional
    public String update(NoticeUpdateRequest request) {
        if(request.getCode().equals("13579")){
            Notice notice = noticeRepository.findById(request.getId()).get();
            notice.setUpdateAt(request.getUpdateAt());
            notice.setTitle(request.getTitle());
            notice.setContents(request.getContents());
            notice.setImg(request.getImg());
            noticeRepository.save(notice);
            return "업데이트 성공";
        } else {
            return null;
        }
    }
}
