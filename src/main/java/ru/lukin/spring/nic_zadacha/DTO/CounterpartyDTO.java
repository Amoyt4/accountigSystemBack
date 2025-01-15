package ru.lukin.spring.nic_zadacha.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounterpartyDTO {
    private Long id;
    private String name;
    private String address;
    private String inn;
}