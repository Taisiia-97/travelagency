package com.taisiia.travelagency.service;

import com.taisiia.travelagency.domain.dao.MediaEntity;
import com.taisiia.travelagency.repository.MediaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
@Component
@Slf4j
public class MediaService {
    @Value("${file.mediaRootLocation}")
    private String photoUrl;
    private final MediaRepository mediaRepository;

    public String saveFile(MultipartFile photo) {
        Path destinationFile = Paths.get(photoUrl)
                .resolve(Paths.get(photo.getOriginalFilename()))
                .normalize()
                .toAbsolutePath();
        try {
            Files.copy(photo.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);
            String fileName = photo.getOriginalFilename();
            String extension = fileName.substring(fileName.lastIndexOf("."));
            mediaRepository.save(new MediaEntity(photo.getOriginalFilename(), extension));
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return destinationFile.toString();
    }
}
