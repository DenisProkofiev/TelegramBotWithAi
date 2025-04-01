package ru.hellforge.telegrambotwithai.openai;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@DependsOn("openAIConfigurationProperties")
public class OpenApiService {

    private final OpenAIConfigurationProperties aiConfigurationProperties;
    private final ChatClient chatClient;

    public String getResponseFromAI(String message) {
        Prompt prompt = new Prompt(message);

        return chatClient.prompt(prompt).call().content();
    }
}
