package main.customer.infrastructure.controllers;

import main.customer.infrastructure.services.ai.AiOllamaService;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/streaming")
public class OllamaStreamingController {

    private final AiOllamaService streamingAiService;

    @Autowired
    public OllamaStreamingController(AiOllamaService streamingAiService) {
        this.streamingAiService = streamingAiService;
    }

    @GetMapping(value = "/chat", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> streamChat(@RequestParam(value = "prompt", defaultValue = "Tell me a joke") String prompt) {
        return streamingAiService.streamChat(new Prompt(prompt))
                .map(chatResponse -> chatResponse.getResult().getOutput().toString());
    }
}
