package by.stepchenko.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class CreditCard {
    private Long id;
    private Long cardNumber;
    private Double balance;
    private LocalDateTime registrationDate;
    private Boolean isActive;
    private BankAccount bankAccount;
}
