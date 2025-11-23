package com.rideer.backend.controller;

import com.rideer.backend.dto.UploadResponse;
import com.rideer.backend.model.DocumentType;
import com.rideer.backend.service.S3StorageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.net.URL;
import java.time.Duration;

@RestController
@RequestMapping("/api/driver")
@CrossOrigin
public class DriverDocumentController {

    private final S3StorageService s3Service;

    public DriverDocumentController(S3StorageService s3Service) {
        this.s3Service = s3Service;
    }

    // ==========================
    // 📌 Upload document API
    // ==========================
    @PostMapping("/{driverId}/upload")
    public ResponseEntity<UploadResponse> upload(
            @PathVariable String driverId,
            @RequestParam("file") MultipartFile file,
            @RequestParam("type") String type
    ) {
        try {
            DocumentType docType = DocumentType.valueOf(type.toUpperCase());
            String key = s3Service.uploadDocument(file, driverId, docType);
            URL url = s3Service.getPresignedUrl(key, Duration.ofMinutes(15));

            return ResponseEntity.ok(
                    new UploadResponse(true, key, url.toString(), "Uploaded Successfully")
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new UploadResponse(false, null, null, e.getMessage()));
        }
    }

    // ==========================
    // 📌 Fetch document API
    // ==========================
    @GetMapping("/{driverId}/document")
    public ResponseEntity<UploadResponse> getDocument(
            @PathVariable String driverId,
            @RequestParam("type") String type
    ) {
        try {
            DocumentType docType = DocumentType.valueOf(type.toUpperCase());
            String key = "drivers/" + driverId + "/" + docType.toFileName() + ".jpg";

            URL url = s3Service.getPresignedUrl(key, Duration.ofMinutes(15));

            return ResponseEntity.ok(
                    new UploadResponse(true, key, url.toString(), "Fetched Successfully")
            );

        } catch (Exception e) {
            return ResponseEntity.badRequest()
                    .body(new UploadResponse(false, null, null, e.getMessage()));
        }
    }
}
