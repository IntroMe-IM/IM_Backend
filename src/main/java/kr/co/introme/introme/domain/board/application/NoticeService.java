package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Notice;
import kr.co.introme.introme.domain.board.dto.*;
import kr.co.introme.introme.domain.board.repository.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NoticeService {
    private final NoticeRepository noticeRepository;

    public boolean save(NoticePostRequest request) {
        if(request.getCode().equals("pass")){
            noticeRepository.save(NoticePostRequest.saveToEntity(request));
            return true;
        } else {
            return false;
        }
    }

    public NoticePostResponse getOne(Long id) {
        Notice notice = noticeRepository.findById(id).get();
        return NoticePostResponse.saveToDto(notice);
    }

    @Transactional
    public void hit(Long id){
        Notice notice = noticeRepository.findById(id).get();
        Integer plus = notice.getHit() + 1;
        notice.setHit(plus);
        noticeRepository.save(notice);
    }

    @Transactional
    public String update(NoticeUpdateRequest request) {
        if(request.getCode().equals("pass")){
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

    public String delete(Long id, NoticeDeleteRequest request) {
        if(noticeRepository.existsById(id)){
            if(request.getCode().equals("pass")){
                noticeRepository.deleteById(id);
                return "true";
            } else {
                return "코드오류";
            }
        } else {
            return "없는 게시물";
        }
    }

    public NoticePageResponse<NoticePostResponse> getNoticeList(NoticePageRequest noticePageRequest) {
        Pageable pageable = PageRequest.of(noticePageRequest.getPage(), noticePageRequest.getSize(), Sort.by(Sort.Order.desc("createAt")));
        Page<Notice> noticePage = noticeRepository.findAll(pageable);
        List<NoticePostResponse> contents = noticePage.getContent().stream().map( notice -> NoticePostResponse.noticePage(
                notice.getId(),
                notice.getTitle(),
                notice.getCreateAt(),
                notice.getUpdateAt(),
                notice.getHit()
        )).collect(Collectors.toList());
        return new NoticePageResponse<>(
                noticePage.getTotalPages(),
                noticePage.getTotalElements(),
                noticePage.getSize(),
                noticePage.getNumber(),
                contents,
                noticePage.isFirst(),
                noticePage.isLast()
        );
    }
}
