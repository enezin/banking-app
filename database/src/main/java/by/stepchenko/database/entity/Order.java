package by.stepchenko.database.entity;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
@Data
@Builder
@EqualsAndHashCode(of = "id")
public class Order {
    private Long id;
    private Long userId;
    private Double cost;
    private LocalDateTime dateOfRegistration;
    private Boolean isPaid;

}
