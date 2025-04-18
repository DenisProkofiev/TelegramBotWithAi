package ru.hellforge.telegrambotwithai.bot;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.hellforge.telegrambotwithai.dto.TelegramUser;
import ru.hellforge.telegrambotwithai.exception.UserlistNotFoundException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;
import static ru.hellforge.telegrambotwithai.util.UtilClass.OBJECT_MAPPER;

@Slf4j
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "telegram.bot")
public class TelegramBotProperties {
    private String token;
    private String name;
    private String classpathAllowedUsers;
    private List<TelegramUser> allowedUsers;

    @PostConstruct
    public void init() {
        var usersFile = new File(classpathAllowedUsers);

        if (!usersFile.exists()) {
            throw new UserlistNotFoundException(classpathAllowedUsers);
        }

        try {
            allowedUsers = OBJECT_MAPPER.readValue(usersFile, new TypeReference<List<TelegramUser>>() {});

            log.info("Allowed users: {}", allowedUsers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}