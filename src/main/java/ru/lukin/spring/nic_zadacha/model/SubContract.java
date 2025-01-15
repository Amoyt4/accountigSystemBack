package ru.lukin.spring.nic_zadacha.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "sub_contract")
public class SubContract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String contractType;
    private Integer amount;
    private LocalDate plannedStartDate;
    private LocalDate plannedEndDate;
    private LocalDate actualStartDate;
    private LocalDate actualEndDate;

    @ManyToOne
    @JoinColumn(name = "counterparty_id", nullable = false)
    private Counterparty counterparty;
}