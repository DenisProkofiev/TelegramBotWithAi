package ru.hellforge.telegrambotwithai.bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.hellforge.telegrambotwithai.openai.OpenApiService;
import ru.hellforge.telegrambotwithai.service.UserService;


public class TelegramBot extends TelegramLongPollingBot {
    private final TelegramBotProperties botProperties;
    private final OpenApiService openApiService;
    private final UserService userService;

    public TelegramBot(DefaultBotOptions options, String botToken, TelegramBotProperties botProperties,
                       OpenApiService openApiService, UserService userService) {
        super(options, botToken);
        this.botProperties = botProperties;
        this.openApiService = openApiService;
        this.userService = userService;
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        userService.authorizeUser(update);

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