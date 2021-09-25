package com.taisiia.travelagency.hotel;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@RequiredArgsConstructor
@Component
public class MediaService {
    @Value("${mediaRootLocation}")
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
            mediaRepository.save(new MediaEntity(photo.getOriginalFilename(),extension ));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return destinationFile.toString();
    }
}
