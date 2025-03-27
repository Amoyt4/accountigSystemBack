package ru.lukin.spring.nic_zadacha.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.lukin.spring.nic_zadacha.DTO.CounterpartyDTO;
import ru.lukin.spring.nic_zadacha.service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/counterparties")
@AllArgsConstructor
public class CounterpartyController {

    private final CounterpartyService counterpartyService;

    @GetMapping
    public List<CounterpartyDTO> getAllCounterparties() {
        return counterpartyService.getAllCounterparties();
    }

    @GetMapping("/{id}")
    public CounterpartyDTO getCounterpartyById(@PathVariable Long id) {
        return counterpartyService.getCounterpartyDTOById(id).orElse(null);
    }

    @PostMapping
    public CounterpartyDTO createCounterparty(@RequestBody CounterpartyDTO counterpartyDTO) {
        return counterpartyService.createCounterparty(counterpartyDTO);
    }

    @PutMapping("/{id}")
    public CounterpartyDTO updateCounterparty(@PathVariable Long id, @RequestBody CounterpartyDTO counterpartyDTO) {
        counterpartyDTO.setId(id);
        return counterpartyService.updateCounterparty(counterpartyDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteCounterparty(@PathVariable Long id) {
        counterpartyService.deleteCounterparty(id);
    }
}