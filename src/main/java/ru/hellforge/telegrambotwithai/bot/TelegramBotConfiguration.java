package ru.hellforge.telegrambotwithai.bot;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.hellforge.telegrambotwithai.openai.OpenApiService;

@Configuration
@DependsOn(value = "telegramBotProperties")
public class TelegramBotConfiguration {
    private final TelegramBotProperties botProperties;

    public TelegramBotConfiguration(TelegramBotProperties botProperties) {
        this.botProperties = botProperties;
    }

    @SneakyThrows
    @Bean
    public TelegramBot telegramBot(TelegramBotsApi telegramBotsApi, OpenApiService openApiService) {
        var options = new DefaultBotOptions();
        var telegramBot = new TelegramBot(options, botProperties.getToken(), botProperties, openApiService);
        telegramBotsApi.registerBot(telegramBot);

        return telegramBot;
    }

    @Bean
    @SneakyThrows
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi(DefaultBotSession.class);
    }
}
