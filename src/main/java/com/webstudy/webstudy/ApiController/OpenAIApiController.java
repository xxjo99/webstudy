package com.webstudy.webstudy.ApiController;

import com.webstudy.webstudy.dto.ChatGPTRequest;
import com.webstudy.webstudy.dto.ChatGPTResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

@Tag(name = "Open AI api")
@RestController
@RequestMapping("/api/openai")
public class OpenAIApiController {

    @Value("${openai.model}")
    private String model;

    @Value("${openai.api.url}")
    private String apiUrl;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/chat")
    @Operation(summary = "ChatGPT 응답")
    public String chat(@RequestParam("prompt") String prompt) {
        ChatGPTRequest request = new ChatGPTRequest(model, prompt);
        ChatGPTResponse chatGptResponse = restTemplate.postForObject(apiUrl, request, ChatGPTResponse.class);
        return Objects.requireNonNull(chatGptResponse).getChoices().get(0).getMessage().getContent();
    }

}
