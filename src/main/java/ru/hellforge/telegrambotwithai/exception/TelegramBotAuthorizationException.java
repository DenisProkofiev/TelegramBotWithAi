package ru.hellforge.telegrambotwithai.exception;

import org.telegram.telegrambots.meta.api.objects.User;

public class TelegramBotAuthorizationException extends RuntimeException {
    private static final String TELEGRAM_BOT_AUTHORIZATION_EXCEPTION_MESSAGE = "User with username=%s not allowed to use the bot";

    public TelegramBotAuthorizationException(User user) {
        super(String.format(TELEGRAM_BOT_AUTHORIZATION_EXCEPTION_MESSAGE, user.getUserName()));
    }
}