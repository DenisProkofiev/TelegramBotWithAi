package ru.hellforge.telegrambotwithai.openai;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.ai.openai")
public class OpenAIConfigurationProperties {
    private String baseUrl;
    private String apiKey;
}
