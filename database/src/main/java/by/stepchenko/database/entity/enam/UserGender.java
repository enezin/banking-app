package by.stepchenko.database.entity.enam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserGender {
    MALE("Мужской"),
    FEMALE("Женский"),
    OTHER("Другой");

    private final String title;
}
