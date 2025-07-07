
package main.customer.infrastructure.qa.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;import main.customer.infrastructure.dto.CustomerDTO;

import java.util.function.Function;

public class QuestionAnsweringDTO {
    @JsonProperty(required = true)
    @JsonPropertyDescription("The question to be answered")
    private String question;

    @JsonProperty(required = true)
    @JsonPropertyDescription("The answer to the question")
    private String answer;

    public QuestionAnsweringDTO() {
    }

    public QuestionAnsweringDTO(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public static class QuestionAnsweringDTOFunction implements Function<QuestionAnsweringDTO, QuestionAnsweringDTO> {
        @Override
        public QuestionAnsweringDTO apply(QuestionAnsweringDTO questionAnsweringDTO) {
            return questionAnsweringDTO;
        }
    }
}
