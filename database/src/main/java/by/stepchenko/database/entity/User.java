package by.stepchenko.database.entity;

import by.stepchenko.database.util.UserGender;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@EqualsAndHashCode(of = "id")
public class User {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthdate;
    private UserGender gender;
    private String email;
    private String password;
    private LocalDate registrationDate;
    private List<BankAccount> bankAccounts;
    private List<Order> orderList;
}