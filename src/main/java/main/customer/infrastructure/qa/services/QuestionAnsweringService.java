package main.customer.infrastructure.qa.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import main.customer.infrastructure.qa.dto.QuestionAnsweringDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@Service
public class QuestionAnsweringService {
    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    private static final String PROMPT_TEMPLATE = """
            You are an assistant that answers questions only in json format, do not include any other text.:
            Question: {question}
            You MUST only answer following this JSON format: :
            {
                "question": "The question asked",
                "answer": "The answer to the question"
            }
            """;

    public Mono<QuestionAnsweringDTO> generateQuestionAndAnswer(String message) {
        return chatClient.prompt()
                .system(PROMPT_TEMPLATE)
                .user(message)
                .stream()
                .content()
                .log()
                .reduce(new StringBuilder(), StringBuilder::append)
                .map(sb -> {
                    String response = sb.toString().trim();
                    log.info("Received response: {}", response);
                    if (response.startsWith("{") && response.endsWith("}")) {
                        try {
                            return objectMapper.readValue(response, QuestionAnsweringDTO.class);
                        } catch (Exception e) {
                            throw new RuntimeException("Failed to parse JSON: " + response, e);
                        }
                    } else {
                        throw new RuntimeException("Model did not return JSON: " + response);
                    }
                });
    }
}
