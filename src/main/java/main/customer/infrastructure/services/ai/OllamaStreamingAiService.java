package main.customer.infrastructure.services.ai;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class OllamaStreamingAiService {

    private final OllamaChatModel chatClient;

    @Autowired
    public OllamaStreamingAiService(OllamaChatModel chatClient) {
        this.chatClient = chatClient;
    }

    public Flux<ChatResponse> streamChat(Prompt prompt) {
        return chatClient.stream(prompt);
    }
}
