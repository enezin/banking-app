package by.stepchenko.database.entity.enam;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Role {

    USER("0"),
    ADMINISTRATOR("1");

    private final String title;
}
