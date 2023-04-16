package by.stepchenko.database;

import by.stepchenko.database.entity.User;
import by.stepchenko.database.util.UserGender;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DummyDatabase {

    private static final DummyDatabase INSTANCE = new DummyDatabase();

    private final Map<Long, User> users = new HashMap<>() {{
        put(1L, User.builder()
                .id(1L)
                .firstName("Dmitry")
                .lastName("Stepchenko")
                .birthdate(LocalDate.EPOCH)
                .gender(UserGender.MALE)
                .email("qwerty@gmail.com")
                .password("123123")
                .registrationDate(LocalDate.now())
                .build());
        put(2L, User.builder()
                .id(2L)
                .firstName("Alina")
                .lastName("Stepchenko")
                .birthdate(LocalDate.EPOCH)
                .gender(UserGender.FEMALE)
                .email("qwerty123@gmail.com")
                .password("123123")
                .registrationDate(LocalDate.now())
                .build());
    }};

    public static DummyDatabase getInstance() {
        return INSTANCE;
    }
}
