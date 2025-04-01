package ru.hellforge.telegrambotwithai.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.hellforge.telegrambotwithai.openai.OpenApiService;


public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotProperties botProperties;
    private final OpenApiService openApiService;

    public TelegramBot(DefaultBotOptions options, String botToken, TelegramBotProperties botProperties,
                       OpenApiService openApiService) {
        super(options, botToken);
        System.out.println(botProperties);
        this.botProperties = botProperties;
        this.openApiService = openApiService;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {

            var response = openApiService.getResponseFromAI(update.getMessage().getText());

            var chatId = update.getMessage().getChatId();
            SendMessage sendMessage = SendMessage.builder().chatId(chatId.toString()).text(response).build();
            sendApiMethod(sendMessage);
        }
    }

    @Override
    public String getBotUsername() {
        return botProperties.getName();
    }
}
