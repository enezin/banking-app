package by.stepchenko.database.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public enum UserGender {
    MALE("Мужской"),
    FEMALE("Женский"),
    OTHER("Другой");

    private final String title;
}
