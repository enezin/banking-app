package by.stepchenko.service;

import by.stepchenko.database.dao.CreditCardDao;
import by.stepchenko.database.entity.CreditCard;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Optional;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CreditCardService {

    public static final Long ID = 9223372036854775807L;
    public static final Long CARD_NUMBER = 9999999999999999L;
    private static final CreditCardService INSTANCE = new CreditCardService();

    private final CreditCardDao creditCardDao = CreditCardDao.getInstance();

    public Optional<CreditCard> create(Long accountId, CreditCard creditCard) {
        return creditCardDao.create(accountId, creditCard);
    }

    public CreditCard findById(Long id) {
        return creditCardDao.findById(id)
                .orElse(CreditCard.builder()
                        .id(ID)
                        .cardNumber(CARD_NUMBER)
                        .balance(0d)
                        .isActive(false)
                        .build());
    }

    public List<CreditCard> findByAccountId(Long id) {
        return creditCardDao.findByAccountId(id);
    }

    public boolean delete(Long id) {
        return creditCardDao.delete(id);
    }

    public static CreditCardService getInstance() {
        return INSTANCE;
    }
}