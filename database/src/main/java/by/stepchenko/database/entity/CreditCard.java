package by.stepchenko.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class CreditCard {
    private Long id;
    private Long cardNumber;
    private Double creditLimit;
    private LocalDate registrationDate = LocalDate.now();
    private Long bankAccountId;
    private Boolean isActive;
}
