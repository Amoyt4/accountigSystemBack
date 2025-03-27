package ru.lukin.spring.nic_zadacha.controller.excel;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DateRangeRequest {
    private Date dateStart;
    private Date dateEnd;
}