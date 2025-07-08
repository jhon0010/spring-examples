package main.infrastructure.ai.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import main.infrastructure.web.dto.ProductDTO;
import main.infrastructure.web.dto.ProductSimilarityDTO;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class in charge of to handle embeddings and similarity search with embeddings products.
 */
@Service
public class EmbeddingsService {
    private static List<ProductDTO> productList = new ArrayList<>();
    private static Map<Integer, float[]> embeddings = new HashMap<>();

    @Autowired
    private EmbeddingModel embeddingModel;
    @Autowired
    private SimilarityCalculatorService similarityCalculator;

    @PostConstruct
    public void initProducts() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jsons/samples.json");
        if (inputStream != null) {
            // map JSON into List<Product>
            productList = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            System.out.println("Products loaded: List size = " + productList.size());

        } else {
            System.out.println("File samples.json not found in resources.");
        }
        embeddings = loadEmbeddingsFromFile();
    }

    public Map<Integer, float[]> loadEmbeddingsFromFile() {
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("jsons/embeddings.json");
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(inputStream, new TypeReference<>() {
            });
        } catch (Exception e) {
            System.err.println("Error loading embeddings from file: " + e.getMessage());
            return null;
        }
    }

    public Flux<ProductSimilarityDTO> getSimilarProducts(String description) {
        return Mono.fromCallable(() -> embeddingModel.embedForResponse(List.of(description)))
                .subscribeOn(Schedulers.boundedElastic())
                .flatMapMany(embeddingResponse ->
                        similarityCalculator.findTopSimilarProducts(
                                embeddingResponse.getResult().getOutput(),
                                embeddings,
                                productList,
                                5
                        )
                )
                .doOnNext(ps -> System.out.printf("Product ID: %d, Name: %s, Description: %s, Similarity: %.4f%n",
                        ps.getProductDto().getId(),
                        ps.getProductDto().getName(),
                        ps.getProductDto().getDescription(),
                        ps.getSimilarity()));
    }
}