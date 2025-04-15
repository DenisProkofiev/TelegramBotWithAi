package ru.hellforge.telegrambotwithai.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import ru.hellforge.telegrambotwithai.bot.TelegramBotProperties;
import ru.hellforge.telegrambotwithai.dto.TelegramUser;
import ru.hellforge.telegrambotwithai.exception.TelegramBotAuthorizationException;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final TelegramBotProperties properties;

    public User getUser(Update update) {
        return update.getMessage().getFrom();
    }

    public void authorizeUser(Update update) {
        var user = getUser(update);

        log.info("Authorize user: {}", user);

        var allowedUserList = properties.getAllowedUsers().stream()
                .map(TelegramUser::getUserName)
                .toList();

        if (!allowedUserList.contains(user.getUserName())) {
            throw new TelegramBotAuthorizationException(user);
        }

    }
}