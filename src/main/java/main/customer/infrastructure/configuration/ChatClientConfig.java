package main.customer.infrastructure.configuration;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;

import java.util.List;

@Configuration
public class ChatClientConfig {
    private OllamaChatModel ollamaChatModel;

    @Value("${spring.ai.ollama.chat.options.model}")
    private String modelName;

    @Bean
    public OllamaChatModel ollamaChatModel() {
        if (ollamaChatModel == null) {
            ollamaChatModel = new OllamaChatModel(
                new OllamaApi(),
                OllamaOptions.builder().model(modelName).build(),
                null,
                List.of(),
                ObservationRegistry.create(),
                ModelManagementOptions.builder().build()
            );
        }
        return ollamaChatModel;
    }

    @Bean
    public ChatClient chatClient(ChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .build();
    }

    @Bean
    public ChatModel chatModel() {
        return ollamaChatModel();
    }

}
