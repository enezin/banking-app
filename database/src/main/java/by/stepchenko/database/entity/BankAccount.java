package by.stepchenko.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class BankAccount {

    private Long id;
    private String accountNumber;
    private Double balance;
    private final Double creditLimit;
    @Builder.Default
    private List<CreditCard> cardList = new ArrayList<>();
    private LocalDateTime registrationDate;
    private final Boolean isActive;
    private User user;
}