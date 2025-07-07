package main.infrastructure.ai.services;

import main.infrastructure.web.dto.ProductDTO;
import main.infrastructure.web.dto.ProductSimilarityDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class SimilarityCalculatorService {

    private float calculateCosineSimilarity(float[] vectorA, float[] vectorB) {
        float dotProduct = 0.0f;
        float normA = 0.0f;
        float normB = 0.0f;

        for (int i = 0; i < vectorA.length; i++) {
            dotProduct += vectorA[i] * vectorB[i];
            normA += (float) Math.pow(vectorA[i], 2);
            normB += (float) Math.pow(vectorB[i], 2);
        }

        return (float) (dotProduct / (Math.sqrt(normA) * Math.sqrt(normB)));
    }

    public Flux<ProductSimilarityDTO> findTopSimilarProducts(
            float[] queryEmbedding,
            Map<Integer, float[]> embeddings,
            List<ProductDTO> products,
            int topN) {

        return Flux.fromIterable(products)
                .map(product -> {
                    float[] productEmbedding = embeddings.get(product.getId());
                    if (productEmbedding != null) {
                        float similarity = calculateCosineSimilarity(queryEmbedding, productEmbedding);
                        return new ProductSimilarityDTO(product, similarity);
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .sort(Comparator.comparing(ProductSimilarityDTO::getSimilarity).reversed())
                .take(topN);
    }
}
