package main.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import main.infrastructure.ai.services.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/images")
@RequiredArgsConstructor
public class ImageController {
    private final ImageService imageService;

    @GetMapping
    public ResponseEntity<String> generate(@RequestParam String prompt) {
        return ResponseEntity.ok(imageService.generateImage(prompt));
    }
}