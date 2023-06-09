package by.stepchenko.service;

import by.stepchenko.database.dao.UserDao;
import by.stepchenko.database.entity.User;
import by.stepchenko.database.entity.enam.UserGender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserService {

    public static final long ID = 9223372036854775807L;
    private static final UserService INSTANCE = new UserService();

    private final UserDao userDao = UserDao.getInstance();

    public Optional<User> create(User user) {
        return userDao.create(user);
    }

    public User getById(Long id) {
        return userDao.findById(id)
                .orElse(User.builder()
                        .id(ID)
                        .firstName("User")
                        .lastName("Unknown")
                        .birthdate(LocalDate.EPOCH)
                        .gender(UserGender.MALE)
                        .email("unknown")
                        .password("mL4hYzc0t8wbVdDfQWdo")
                        .registrationDate(LocalDateTime.now())
                        .build());
    }

    public Optional<User> getByEmailAndPassword(String email, String password) {
        return userDao.findByEmailAndPassword(email, password);
    }

    public List<User> findAll() {
        return userDao.findAll();
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
}