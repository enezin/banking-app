package by.stepchenko.database.dao;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BankAccountDao {
    private static final BankAccountDao INSTANCE = new BankAccountDao();

    public static BankAccountDao getInstance() {
        return INSTANCE;
    }
}
