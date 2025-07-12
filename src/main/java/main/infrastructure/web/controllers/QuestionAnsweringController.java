
package main.infrastructure.web.controllers;

import lombok.RequiredArgsConstructor;
import main.infrastructure.web.dto.QuestionAnsweringDTO;
import main.infrastructure.ai.services.QuestionAnsweringService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
@RestController
@RequestMapping("/qa")
public class QuestionAnsweringController {
    private final QuestionAnsweringService questionAnsweringService;

    @GetMapping("/question")
    public Mono<QuestionAnsweringDTO> generateQuestionAndAnswer(@RequestBody String message) {
        return questionAnsweringService.generateQuestionAndAnswer(message);
    }
}
