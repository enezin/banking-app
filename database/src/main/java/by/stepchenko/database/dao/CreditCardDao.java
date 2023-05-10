package by.stepchenko.database.dao;

import by.stepchenko.database.connection.ConnectionPool;
import by.stepchenko.database.entity.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class CreditCardDao {

    private static final CreditCardDao INSTANCE = new CreditCardDao();

    private static final String INSERT = "INSERT INTO credit_card (card_number, bank_account_id) VALUES (?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM credit_card WHERE id = ?";
    private static final String SELECT_BY_ACCOUNT_ID = "SELECT * FROM credit_card WHERE bank_account_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM credit_card WHERE id =?";

    private static final String CARD_NUMBER = "";
    public static final long MAX_RANDOM = 9999999999999999L;

    private Long generateCardNumber() {
        Random random = new Random();
        return random.nextLong(MAX_RANDOM);
    }

    public Optional<CreditCard> create(Long accountId, CreditCard creditCard) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, CARD_NUMBER.concat(generateCardNumber().toString()));
            preparedStatement.setLong(2, accountId);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                creditCard.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(creditCard);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public Optional<CreditCard> findById(Long id) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(CreditCard.builder()
                        .id(resultSet.getLong("id"))
                        .cardNumber(resultSet.getLong("card_number"))
                        .balance(resultSet.getDouble("balance"))
                        .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                        .isActive(resultSet.getBoolean("is_active"))
                        .build());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    public List<CreditCard> findByAccountId(Long bankAccountId) {
        List<CreditCard> creditCards = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ACCOUNT_ID)) {
            preparedStatement.setLong(1, bankAccountId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                creditCards.add(CreditCard.builder()
                        .id(resultSet.getLong("id"))
                        .cardNumber(resultSet.getLong("card_number"))
                        .balance(resultSet.getDouble("balance"))
                        .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                        .isActive(resultSet.getBoolean("is_active"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return creditCards;
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

    public static CreditCardDao getInstance() {
        return INSTANCE;
    }
}
