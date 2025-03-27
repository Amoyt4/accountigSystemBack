package ru.lukin.spring.nic_zadacha.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.SubContractDTO;
import ru.lukin.spring.nic_zadacha.model.SubContract;
import ru.lukin.spring.nic_zadacha.repository.SubContractRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SubContractService {


    private final SubContractRepository subContractRepository;

    private final CounterpartyService counterpartyService;

    public List<SubContractDTO> getAllSubContracts() {
        return subContractRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<SubContract> getSubContractById(Long id) {
        return subContractRepository.findById(id);
    }

    public Optional<SubContractDTO> getSubContractDTOById(Long id) {
        return subContractRepository.findById(id)
                .map(this::convertToDTO);
    }

    public SubContractDTO createSubContract(SubContractDTO subContractDTO) {
        SubContract subContract = convertToEntity(subContractDTO);
        SubContract savedSubContract = subContractRepository.save(subContract);
        return convertToDTO(savedSubContract);
    }

    public SubContractDTO updateSubContract(SubContractDTO subContractDTO) {
        SubContract subContract = convertToEntity(subContractDTO);
        SubContract updatedSubContract = subContractRepository.save(subContract);
        return convertToDTO(updatedSubContract);
    }

    public void deleteSubContract(Long id) {
        subContractRepository.deleteById(id);
    }

    public SubContract convertToEntity(SubContractDTO dto) {
        SubContract subContract = new SubContract();
        subContract.setId(dto.getId());
        subContract.setName(dto.getName());
        subContract.setContractType(dto.getContractType());
        subContract.setAmount(dto.getAmount());
        subContract.setPlannedStartDate(dto.getPlannedStartDate());
        subContract.setPlannedEndDate(dto.getPlannedEndDate());
        subContract.setActualStartDate(dto.getActualStartDate());
        subContract.setActualEndDate(dto.getActualEndDate());

        subContract.setCounterparty(counterpartyService.getCounterpartyById(dto.getCounterpartyId())
                .orElseThrow(() -> new EntityNotFoundException("Counterparty not found")));

        return subContract;
    }

    public SubContractDTO convertToDTO(SubContract subContract) {
        SubContractDTO dto = new SubContractDTO();
        dto.setId(subContract.getId());
        dto.setName(subContract.getName());
        dto.setContractType(subContract.getContractType());
        dto.setAmount(subContract.getAmount());
        dto.setPlannedStartDate(subContract.getPlannedStartDate());
        dto.setPlannedEndDate(subContract.getPlannedEndDate());
        dto.setActualStartDate(subContract.getActualStartDate());
        dto.setActualEndDate(subContract.getActualEndDate());

        dto.setCounterpartyId(subContract.getCounterparty().getId());

        return dto;
    }
}