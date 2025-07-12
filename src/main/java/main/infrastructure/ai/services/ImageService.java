package main.infrastructure.ai.services;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.ai.image.Image;
import org.springframework.ai.image.ImageModel;
import org.springframework.ai.image.ImagePrompt;
import org.springframework.ai.image.ImageResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Log4j2
@AllArgsConstructor
@Service
public class ImageService {
    private final ImageModel imageModel;
    private final Path outputDir = Paths.get("generated-images");

    @PostConstruct
    public void init() {
        try {
            Files.createDirectories(outputDir);
        } catch (IOException e) {
            throw new RuntimeException("Could not create output directory", e);
        }
    }

    public String generateImage(String prompt) {
        log.info("Generating image for prompt: {}", prompt);
        ImagePrompt imagePrompt = new ImagePrompt(prompt);
        ImageResponse imageResponse = imageModel.call(imagePrompt);
        Image image = imageResponse.getResult().getOutput();
        return saveImageLocal(image, prompt);
    }

    private String saveImageLocal(Image image, String name) {
        try {
            byte[] imageBytes;
            if (image.getB64Json() != null) {
                imageBytes = Base64.getDecoder().decode(image.getB64Json());
            } else {
                imageBytes = WebClient.create()
                        .get()
                        .uri(image.getUrl())
                        .retrieve()
                        .bodyToMono(byte[].class)
                        .block();
            }

            String fileName = "img_" + name + System.currentTimeMillis() + ".png";
            assert imageBytes != null;
            Files.write(outputDir.resolve(fileName), imageBytes);
            log.info("Image saved as: {}", fileName);
            return fileName;
        } catch (IOException e) {
            throw new RuntimeException("Error saving image", e);
        }
    }
}
