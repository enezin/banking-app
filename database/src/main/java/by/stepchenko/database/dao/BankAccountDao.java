package by.stepchenko.database.dao;

import by.stepchenko.database.connection.ConnectionPool;
import by.stepchenko.database.entity.BankAccount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BankAccountDao {
    private static final BankAccountDao INSTANCE = new BankAccountDao();

    private static final String INSERT = "INSERT INTO bank_account (account_number, user_id) VALUES (?, ?)";
    private static final String SELECT_BY_ID = "SELECT * FROM bank_account WHERE id = ?";
    private static final String SELECT_BY_USER_ID = "SELECT * FROM bank_account WHERE user_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM bank_account WHERE id =?";
    private static final String PREFIX_NAME = "BY01ENZB0000";
    public static final long MAX_RANDOM = 9999999999999999L;

    private Long generateAccountNumber() {
        Random random = new Random();
        return random.nextLong(MAX_RANDOM);
    }

    public static BankAccountDao getInstance() {
        return INSTANCE;
    }

    public Optional<BankAccount> create(Long userId, BankAccount bankAccount) {
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, PREFIX_NAME.concat(generateAccountNumber().toString()));
            preparedStatement.setLong(2, userId);
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                bankAccount.setId(generatedKeys.getLong("id"));
            }
            return Optional.of(bankAccount);
        } catch (SQLException e) {
            e.printStackTrace();
            return Optional.empty();
        }
    }

    public List<BankAccount> findById(Long id) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_ID)) {
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bankAccounts.add(BankAccount.builder()
                        .id(resultSet.getLong("id"))
                        .accountNumber(resultSet.getString("account_number"))
                        .balance(resultSet.getDouble("balance"))
                        .creditLimit(resultSet.getDouble("credit_limit"))
                        .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                        .isActive(resultSet.getBoolean("is_active"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankAccounts;
    }

    public List<BankAccount> findByUserId(Long userId) {
        List<BankAccount> bankAccounts = new ArrayList<>();
        try (Connection connection = ConnectionPool.get();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BY_USER_ID)) {
            preparedStatement.setLong(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                bankAccounts.add(BankAccount.builder()
                        .id(resultSet.getLong("id"))
                        .accountNumber(resultSet.getString("account_number"))
                        .balance(resultSet.getDouble("balance"))
                        .creditLimit(resultSet.getDouble("credit_limit"))
                        .registrationDate(resultSet.getTimestamp("registration_date").toLocalDateTime())
                        .isActive(resultSet.getBoolean("is_active"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bankAccounts;
    }

    public Optional<BankAccount> update(Long id, BankAccount bankAccount) {
        if (bankAccount.getBalance() != null) {
            String update = "UPDATE bank_account SET balance = ?, credit_limit = ? WHERE id = ?";
            try (Connection connection = ConnectionPool.get();
                 PreparedStatement preparedStatement = connection.prepareStatement(update)) {
                preparedStatement.setDouble(1, bankAccount.getBalance());
                preparedStatement.setDouble(2, bankAccount.getCreditLimit());
                preparedStatement.setLong(3, id);
                preparedStatement.executeUpdate();
                return Optional.of(bankAccount);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
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
}