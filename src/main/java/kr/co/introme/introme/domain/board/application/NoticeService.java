package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Notice;
import kr.co.introme.introme.domain.board.dto.NoticePostRequest;
import kr.co.introme.introme.domain.board.dto.NoticePostResponse;
import kr.co.introme.introme.domain.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
        notice.setHit(notice.getHit() + 1);
        noticeRepository.save(notice);
        return NoticePostResponse.saveToDto(notice);
    }
}
