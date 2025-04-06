package ru.lukin.spring.nic_zadacha.DTO;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class ContractStageDTO {
    private Long id;
    private String name;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;
    private BigDecimal amount;
    private BigDecimal materialCostsPlan;
    private BigDecimal materialCostsActual;
    private BigDecimal salaryCostsPlan;
    private BigDecimal salaryCostsActual;
}