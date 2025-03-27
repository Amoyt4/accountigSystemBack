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
    private Integer amount;
    private Integer materialCostsPlan;
    private Integer materialCostsActual;
    private Integer salaryCostsPlan;
    private Integer salaryCostsActual;

    public void setAmount(BigDecimal amount) {
    }

    public void setMaterialCostsPlan(BigDecimal materialCostsPlan) {
    }

    public void setMaterialCostsActual(BigDecimal materialCostsActual) {
    }

    public void setSalaryCostsActual(BigDecimal salaryCostsActual) {
    }

    public void setSalaryCostsPlan(BigDecimal salaryCostsPlan) {
    }
}