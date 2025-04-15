package ru.hellforge.telegrambotwithai.exception;

public class UserlistNotFoundException extends RuntimeException{
    private static final String USERLIST_NOT_FOUND_MESSAGE = "Userlist from classpath=%s not found";

    public UserlistNotFoundException(String classpath) {
        super(String.format(USERLIST_NOT_FOUND_MESSAGE, classpath));
    }
}