package main.customer.infrastructure.services.ai;

import lombok.AllArgsConstructor;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class AiOllamaService {
    private final OllamaChatModel chatClient;

    public Flux<ChatResponse> streamChat(Prompt prompt) {
        return chatClient.stream(prompt);
    }
}
