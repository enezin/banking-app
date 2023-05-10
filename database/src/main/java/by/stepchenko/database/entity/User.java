package by.stepchenko.database.entity;

import by.stepchenko.database.entity.enam.Role;
import by.stepchenko.database.entity.enam.UserGender;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
    private LocalDateTime registrationDate;
    @Builder.Default
    private List<BankAccount> bankAccounts = new ArrayList<>();
    @Builder.Default
    private List<Order> orderList = new ArrayList<>();
    private Role role;
}