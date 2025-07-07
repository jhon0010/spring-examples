
package main.customer.infrastructure.qa.controllers;

import main.customer.infrastructure.qa.dto.QuestionAnsweringDTO;
import main.customer.infrastructure.qa.services.QuestionAnsweringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/qa")
public class QuestionAnsweringController {

    private final QuestionAnsweringService questionAnsweringService;

    @Autowired
    public QuestionAnsweringController(QuestionAnsweringService questionAnsweringService) {
        this.questionAnsweringService = questionAnsweringService;
    }

    @GetMapping("/question")
    public Mono<QuestionAnsweringDTO> generateQuestionAndAnswer(@RequestBody String message) {
        return questionAnsweringService.generateQuestionAndAnswer(message);
    }
}
