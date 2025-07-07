package main.infrastructure.web.dto;

import lombok.Data;

@Data
public class ProductSimilarityDTO {
    private final ProductDTO productDto;
    private final float similarity;
}
