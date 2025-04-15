package ru.hellforge.telegrambotwithai.bot;

import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.hellforge.telegrambotwithai.openai.OpenApiService;
import ru.hellforge.telegrambotwithai.service.UserService;

@Configuration
@DependsOn(value = {"telegramBotProperties", "openApiService", "userService"})
public class TelegramBotConfiguration {
    private final TelegramBotProperties botProperties;

    public TelegramBotConfiguration(TelegramBotProperties botProperties) {
        this.botProperties = botProperties;
    }

    @SneakyThrows
    @Bean
    public TelegramBot telegramBot(TelegramBotsApi telegramBotsApi, OpenApiService openApiService, UserService userService) {
        var options = new DefaultBotOptions();
        var telegramBot = new TelegramBot(options, botProperties.getToken(), botProperties, openApiService, userService);
        telegramBotsApi.registerBot(telegramBot);

        return telegramBot;
    }

    @Bean
    @SneakyThrows
    public TelegramBotsApi telegramBotsApi() {
        return new TelegramBotsApi(DefaultBotSession.class);
    }
}
