package ru.lukin.spring.nic_zadacha.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class MyContractDTO {
    private Long id;
    private String name;
    private String contractType;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private Integer amount;
    private List<Long> stages; // Список id стадий
    private List<Long> subContracts; // Список id субконтрактов
}