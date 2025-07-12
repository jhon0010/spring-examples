package main.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import main.infrastructure.ai.services.AiOllamaService;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * This controller provides a streaming endpoint (SSE) service side events for chat responses using the Ollama AI service.
 * It allows clients to receive real-time chat responses as a stream of text events.
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/streaming")
public class OllamaStreamingController {
    private final AiOllamaService streamingAiService;

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestParam(value = "prompt", defaultValue = "Tell me a joke") String prompt) {
        return streamingAiService.streamChat(new Prompt(prompt))
                .map(chatResponse -> chatResponse.getResult().getOutput().toString());
    }
}
