package ru.lukin.spring.nic_zadacha.controller.excel;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class DateRangeRequest {
    private LocalDate dateStart;
    private LocalDate dateEnd;
}