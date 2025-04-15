package ru.hellforge.telegrambotwithai.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilClass {
    public static ObjectMapper OBJECT_MAPPER = new ObjectMapper();
}