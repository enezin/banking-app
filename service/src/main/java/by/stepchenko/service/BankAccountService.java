package by.stepchenko.service;

import by.stepchenko.database.dao.BankAccountDao;
import by.stepchenko.database.entity.BankAccount;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BankAccountService {

    private static final BankAccountService INSTANCE = new BankAccountService();

    private final BankAccountDao bankAccountDao = BankAccountDao.getInstance();

    public Optional<BankAccount> create(Long userId, BankAccount bankAccount) {
        return bankAccountDao.create(userId, bankAccount);
    }

    public List<BankAccount> findById(Long id) {
        return bankAccountDao.findById(id);
    }

    public List<BankAccount> findByUserId(Long id) {
        return bankAccountDao.findByUserId(id);
    }

    public boolean delete(Long id) {
        return bankAccountDao.delete(id);
    }

    public boolean replenishment(Long id, String transfer, Double balance, Double creditLimit) {
        Double volume = Double.valueOf(transfer);
        if (volume > creditLimit) {
            return false;
        } else {
            bankAccountDao.update(id, BankAccount.builder()
                    .id(id)
                    .balance(balance + volume)
                    .creditLimit(creditLimit - volume)
                    .build());
            return true;
        }
    }


    public static BankAccountService getInstance() {
        return INSTANCE;
    }
}