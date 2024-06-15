package kr.co.introme.introme.domain.board.application;

import kr.co.introme.introme.domain.board.domain.Board;
import kr.co.introme.introme.domain.board.dto.BoardContentResponse;
import kr.co.introme.introme.domain.board.dto.BoardPageRequest;
import kr.co.introme.introme.domain.board.dto.BoardPageResponse;
import kr.co.introme.introme.domain.board.dto.BoardPostRequest;
import kr.co.introme.introme.domain.board.repository.BoardRepository;
import kr.co.introme.introme.domain.member.domain.Member;
import kr.co.introme.introme.domain.member.repository.MemberRepository;
import kr.co.introme.introme.global.NFS.FileStorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardPostService {
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final FileStorageService fileStorageService;

    public String save(BoardPostRequest boardPostRequest, MultipartFile file) {

        Optional<Member> memberOptional = memberRepository.findById(boardPostRequest.getAuthor());
        if (memberOptional.isEmpty()) {
            return "Not Found Member";
        }
        Member member = memberOptional.get();
        Board board = Board.saveToEntity(boardPostRequest, member);

        if (file != null && !file.isEmpty()) {
            try {
                String filePath = fileStorageService.storeFile(file, "board");
                board.setImgUrl(filePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to store file", e);
            }
        }

        boardRepository.save(board);
        return "ok";
    }

    public void hit(Long boardId) {
        Board board = boardRepository.findById(boardId).get();
        Integer count = board.getHit() + 1;
        board.setHit(count);
        boardRepository.save(board);
    }

    public BoardPageResponse<BoardContentResponse> getBoards(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Order.desc("createAt")));
        Page<Board> boardPage = boardRepository.findAll(pageable);
        List<BoardContentResponse> content = boardPage.getContent().stream()
                .map(board -> BoardContentResponse.boardLists(
                        board.getId(),
                        board.getTitle(),
                        board.getCreateAt(),
                        board.getUpdateAt(),
                        board.getHit(),
                        board.getAuthor().getId(),
                        board.getAuthor().getName()
                )).collect(Collectors.toList());
        return new BoardPageResponse<>(
                boardPage.getTotalPages(),
                boardPage.getTotalElements(),
                boardPage.getSize(),
                boardPage.getNumber(),
                content,
                boardPage.isFirst(),
                boardPage.isLast()
        );
    }

    public BoardContentResponse getOne(Long boardId) {
        BoardContentResponse response = BoardContentResponse.saveToDTO(boardRepository.findById(boardId).get());
        return response;
    }
}