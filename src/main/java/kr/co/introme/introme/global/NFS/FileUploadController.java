package kr.co.introme.introme.global.NFS;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag(name = "File API", description = "File 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/files")
public class FileUploadController {

    private final FileStorageService fileStorageService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = fileStorageService.storeFile(file);
            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/files/view/")
                    .path(fileName)
                    .toUriString();
            return ResponseEntity.ok("파일 업로드 성공: " + fileDownloadUri);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패: " + ex.getMessage());
        }
    }

    @DeleteMapping("/delete/{fileName:.+}")
    public ResponseEntity<String> deleteFile(@PathVariable String fileName) {
        try {
            fileStorageService.deleteFile(fileName);
            return ResponseEntity.ok("파일 삭제 성공: " + fileName);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 삭제 실패: " + ex.getMessage());
        }
    }

    @GetMapping("/exists/{fileName:.+}")
    public ResponseEntity<String> fileExists(@PathVariable String fileName) {
        boolean exists = fileStorageService.fileExists(fileName);
        if (exists) {
            return ResponseEntity.ok("파일이 존재합니다: " + fileName);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("파일을 찾을 수 없습니다: " + fileName);
        }
    }

    @GetMapping("/view/{fileName:.+}")
    public ResponseEntity<Resource> viewFile(@PathVariable String fileName, HttpServletRequest request) {
        try {
            Path filePath = fileStorageService.loadFile(fileName);
            Resource resource = new UrlResource(filePath.toUri());

            if (resource.exists() || resource.isReadable()) {
                String contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
                if (contentType == null) {
                    contentType = "application/octet-stream";
                }

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType(contentType))
                        .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}