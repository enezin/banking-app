package by.stepchenko.database.dao;

import by.stepchenko.database.DummyDatabase;
import by.stepchenko.database.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();
    private final DummyDatabase db = DummyDatabase.getInstance();

    public User create(User user) {
        Long dummyID = db.getUsers().keySet().size() + 1L;
        user.setId(dummyID);
        return db.getUsers().put(dummyID, user);
    }

    public Optional<User> getById(Long id) {
        return Optional.ofNullable(db.getUsers().get(id));
    }

    public Optional<User> getByEmailAndPassword(String email, String password) {
        return db.getUsers().values().stream()
                .filter(user -> user.getEmail().equals(email))
                .filter(user -> user.getPassword().equals(password))
                .findAny();
    }

    public List<User> getAll() {
        return new ArrayList<>(db.getUsers().values());
    }

    public User delete(Long id) {
        return Optional.ofNullable(db.getUsers().remove(id))
                .orElseThrow(RuntimeException::new);
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}