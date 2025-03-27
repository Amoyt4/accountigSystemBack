package ru.lukin.spring.nic_zadacha.service;

import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.CounterpartyDTO;
import ru.lukin.spring.nic_zadacha.model.Counterparty;
import ru.lukin.spring.nic_zadacha.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CounterpartyService {

    private final CounterpartyRepository counterpartyRepository;

    public List<CounterpartyDTO> getAllCounterparties() {
        return counterpartyRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<Counterparty> getCounterpartyById(Long id) {
        return counterpartyRepository.findById(id);
    }

    public Optional<CounterpartyDTO> getCounterpartyDTOById(Long id) {
        return counterpartyRepository.findById(id)
                .map(this::convertToDTO);
    }

    public CounterpartyDTO createCounterparty(CounterpartyDTO counterpartyDTO) {
        Counterparty counterparty = convertToEntity(counterpartyDTO);
        Counterparty savedCounterparty = counterpartyRepository.save(counterparty);
        return convertToDTO(savedCounterparty);
    }

    public CounterpartyDTO updateCounterparty(CounterpartyDTO counterpartyDTO) {
        Counterparty counterparty = convertToEntity(counterpartyDTO);
        Counterparty updatedCounterparty = counterpartyRepository.save(counterparty);
        return convertToDTO(updatedCounterparty);
    }

    public void deleteCounterparty(Long id) {
        counterpartyRepository.deleteById(id);
    }

    public Counterparty convertToEntity(CounterpartyDTO dto) {
        Counterparty counterparty = new Counterparty();
        counterparty.setId(dto.getId());
        counterparty.setName(dto.getName());
        counterparty.setAddress(dto.getAddress());
        counterparty.setInn(dto.getInn());

        return counterparty;
    }

    public CounterpartyDTO convertToDTO(Counterparty counterparty) {
        CounterpartyDTO dto = new CounterpartyDTO();
        dto.setId(counterparty.getId());
        dto.setName(counterparty.getName());
        dto.setAddress(counterparty.getAddress());
        dto.setInn(counterparty.getInn());

        return dto;
    }
}