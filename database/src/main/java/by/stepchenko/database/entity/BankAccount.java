package by.stepchenko.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class BankAccount {
    public static final double CREDIT_LIMIT = 10000d;

    private Long id;
    private Long accountNumber;
    private Double balance;
    private final Double creditLimit = CREDIT_LIMIT;
    private List<CreditCard> cardList;
    private final LocalDate registrationDate = LocalDate.now();
    private Long userId;
    private final Boolean isActive = true;
}