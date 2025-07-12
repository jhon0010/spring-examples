
package main.infrastructure.web.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import lombok.Data;

@Data
public class QuestionAnsweringDTO {
    @JsonProperty(required = true)
    @JsonPropertyDescription("The question to be answered")
    private String question;

    @JsonProperty(required = true)
    @JsonPropertyDescription("The answer to the question")
    private String answer;
}
