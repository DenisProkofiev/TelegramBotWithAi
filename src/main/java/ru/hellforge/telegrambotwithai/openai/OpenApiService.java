package ru.hellforge.telegrambotwithai.openai;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@DependsOn("openAIConfigurationProperties")
public class OpenApiService {

    private final ChatClient chatClient;

    public String getResponseFromAI(String message) {
        log.info("Request for AI: {}", message);

        Prompt prompt = new Prompt(message);

        var content = chatClient.prompt(prompt).call().content();
        log.info("Response from AI: {}", content);

        return content;
    }
}
