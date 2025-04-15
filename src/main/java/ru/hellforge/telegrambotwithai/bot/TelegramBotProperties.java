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
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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
    @Getter
    private List<TelegramUser> allowedUsers;

    @PostConstruct
    public void init() {
        if (Files.notExists(Path.of(classpathAllowedUsers))) {
            throw new UserlistNotFoundException(classpathAllowedUsers);
        }
        try {
            File file = new File(classpathAllowedUsers);
            this.allowedUsers = OBJECT_MAPPER.readValue(file, new TypeReference<List<TelegramUser>>() {});
            log.info("Allowed users: {}", allowedUsers);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}