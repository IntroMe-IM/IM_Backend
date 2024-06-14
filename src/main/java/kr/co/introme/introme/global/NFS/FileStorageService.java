package kr.co.introme.introme.global.NFS;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class FileStorageService {

    private static final Logger logger = LoggerFactory.getLogger(FileStorageService.class);

    @Value("${file.upload-dir}")
    private String uploadDir;

    public void init() {
        try {
            Path path = Paths.get(uploadDir);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
                logger.info("Upload directory created at: " + path.toAbsolutePath().toString());
            } else {
                logger.info("Upload directory already exists at: " + path.toAbsolutePath().toString());
            }
        } catch (IOException e) {
            logger.error("Could not create upload directory!", e);
            throw new RuntimeException("Could not create upload directory!", e);
        }
    }

    public String storeFile(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path uploadPath = Paths.get(uploadDir);

        if (file.isEmpty()) {
            logger.warn("File is empty: " + fileName);
            throw new IllegalArgumentException("파일이 비어 있습니다: " + fileName);
        }
        if (fileName.contains("..")) {
            logger.warn("Invalid path sequence in file name: " + fileName);
            throw new IllegalArgumentException("파일 이름에 잘못된 경로 시퀀스가 포함되어 있습니다: " + fileName);
        }

        try {
            Path targetLocation = uploadPath.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
            logger.info("File uploaded successfully: " + targetLocation.toAbsolutePath().toString());
            return fileName;
        } catch (IOException ex) {
            logger.error("Error occurred while uploading file: " + fileName, ex);
            throw new IOException("파일 업로드 중 문제가 발생했습니다: " + fileName, ex);
        }
    }

    public Path loadFile(String fileName) {
        return Paths.get(uploadDir).resolve(fileName);
    }

    public void deleteFile(String fileName) throws IOException {
        Path fileToDelete = Paths.get(uploadDir).resolve(fileName);
        Files.deleteIfExists(fileToDelete);
    }

    public boolean fileExists(String fileName) {
        Path filePath = loadFile(fileName);
        return Files.exists(filePath);
    }
}