package by.stepchenko.database.dao;

import by.stepchenko.database.connection.ConnectionPool;
import by.stepchenko.database.entity.User;
import by.stepchenko.database.entity.enam.Role;
import by.stepchenko.database.entity.enam.UserGender;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserDao {

    private static final UserDao INSTANCE = new UserDao();
    private static final String INSERT = "INSERT INTO users (first_name, last_name, birthdate, gender, email, password) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM users WHERE id = ?";
    private static final String SELECT_BY_EMAIL_AND_PASSWORD = "SELECT * FROM users WHERE email = ? AND password = ?";
    private static final String SELECT_ALL = "SELECT * FROM users";
    public static final String DELETE_BY_ID = "DELETE FROM users WHERE id = ?";

    public Optional<User> create(User user) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getFirstName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setDate(3, Date.valueOf(user.getBirthdate().toString()));
            preparedStatement.setString(4, user.getGender().toString());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setString(6, user.getPassword());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<User> findById(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .birthdate(resultSet.getDate("birthdate").toLocalDate())
                        .gender(UserGender.valueOf(resultSet.getString("gender")))
                        .email(resultSet.getString("email"))
                        .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public Optional<User> findByEmailAndPassword(String email, String password) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_EMAIL_AND_PASSWORD)) {
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .birthdate(resultSet.getDate("birthdate").toLocalDate())
                        .gender(UserGender.valueOf(resultSet.getString("gender")))
                        .email(resultSet.getString("email"))
                        .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<User> findAll() {
        List<User> users = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(SELECT_ALL);
            while (resultSet.next()) {
                users.add(User.builder()
                        .id(resultSet.getLong("id"))
                        .firstName(resultSet.getString("first_name"))
                        .lastName(resultSet.getString("last_name"))
                        .birthdate(resultSet.getDate("birthdate").toLocalDate())
                        .gender(UserGender.valueOf(resultSet.getString("gender")))
                        .email(resultSet.getString("email"))
                        .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                        .role(Role.valueOf(resultSet.getString("role")))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }



    public boolean delete(Long id) {
        try (Connection connection = ConnectionPool.get();
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static UserDao getInstance() {
        return INSTANCE;
    }
}