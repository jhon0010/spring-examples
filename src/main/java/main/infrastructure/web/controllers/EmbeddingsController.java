package main.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import main.infrastructure.ai.services.EmbeddingsService;
import main.infrastructure.web.dto.ProductSimilarityDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
@Log4j2
@RestController
@RequestMapping("/api/embeddings")
public class EmbeddingsController {
    private EmbeddingsService embeddingsService;

    @GetMapping(value = "/similar-product", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ProductSimilarityDTO> findSimilarProduct(@RequestParam String productName) {
        return embeddingsService.getSimilarProducts(productName);
    }
}
