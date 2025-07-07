package main.infrastructure.ai.configuration;

import io.micrometer.observation.ObservationRegistry;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.ai.ollama.management.ModelManagementOptions;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.ollama.OllamaEmbeddingModel;
import org.springframework.ai.ollama.api.OllamaApi;
import org.springframework.ai.ollama.api.OllamaOptions;
import org.springframework.context.annotation.Primary;

import java.util.List;

@Configuration
public class ChatClientConfig {
    private OllamaChatModel ollamaChatModel;
    // NOTE: "text-embedding-ada-002" is an OpenAI model.
    // For Ollama, use a model like "nomic-embed-text" or another embedding model you have pulled.
    private static final String EMBEDDING_MODEL_NAME = "nomic-embed-text";

    @Value("${spring.ai.ollama.chat.options.model}")
    private String modelName;

    @Primary
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

    /**
     *  generate vector representations of text (for similarity search, RAG, clustering, etc.)
     * @return OllamaChatModel configured for embeddings
     */
    @Bean
    public EmbeddingModel embeddingModel() {
        return new OllamaEmbeddingModel(
                new OllamaApi(),
                OllamaOptions.builder().model(EMBEDDING_MODEL_NAME).build(),
                ObservationRegistry.create(),
                ModelManagementOptions.builder().build()
        );
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
