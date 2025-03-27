package ru.lukin.spring.nic_zadacha.service;

import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.ContractStageDTO;
import ru.lukin.spring.nic_zadacha.model.ContractStage;
import ru.lukin.spring.nic_zadacha.repository.ContractStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractStageService {

    private final ContractStageRepository contractStageRepository;

    public List<ContractStageDTO> getAllContractStages() {
        return contractStageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ContractStage> getContractStageById(Long id) {
        return contractStageRepository.findById(id);
    }

    public Optional<ContractStageDTO> getContractStageDTOById(Long id) {
        return contractStageRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ContractStageDTO createContractStage(ContractStageDTO contractStageDTO) {
        ContractStage contractStage = convertToEntity(contractStageDTO);
        ContractStage savedContractStage = contractStageRepository.save(contractStage);
        return convertToDTO(savedContractStage);
    }

    public ContractStageDTO updateContractStage(ContractStageDTO contractStageDTO) {
        ContractStage contractStage = convertToEntity(contractStageDTO);
        ContractStage updatedContractStage = contractStageRepository.save(contractStage);
        return convertToDTO(updatedContractStage);
    }

    public void deleteContractStage(Long id) {
        contractStageRepository.deleteById(id);
    }

    public ContractStage convertToEntity(ContractStageDTO dto) {
        ContractStage contractStage = new ContractStage();
        contractStage.setId(dto.getId());
        contractStage.setName(dto.getName());
        contractStage.setPlannedStartDate(dto.getPlannedStartDate());
        contractStage.setPlannedEndDate(dto.getPlannedEndDate());
        contractStage.setActualStartDate(dto.getActualStartDate());
        contractStage.setActualEndDate(dto.getActualEndDate());
        contractStage.setAmount(BigDecimal.valueOf(dto.getAmount()));
        contractStage.setMaterialCostsPlan(BigDecimal.valueOf(dto.getMaterialCostsPlan()));
        contractStage.setMaterialCostsActual(BigDecimal.valueOf(dto.getMaterialCostsActual()));
        contractStage.setSalaryCostsPlan(BigDecimal.valueOf(dto.getSalaryCostsPlan()));
        contractStage.setSalaryCostsActual(BigDecimal.valueOf(dto.getSalaryCostsActual()));

        return contractStage;
    }

    public ContractStageDTO convertToDTO(ContractStage contractStage) {
        ContractStageDTO dto = new ContractStageDTO();
        dto.setId(contractStage.getId());
        dto.setName(contractStage.getName());
        dto.setPlannedStartDate(contractStage.getPlannedStartDate());
        dto.setPlannedEndDate(contractStage.getPlannedEndDate());
        dto.setActualStartDate(contractStage.getActualStartDate());
        dto.setActualEndDate(contractStage.getActualEndDate());
        dto.setAmount(contractStage.getAmount());
        dto.setMaterialCostsPlan(contractStage.getMaterialCostsPlan());
        dto.setMaterialCostsActual(contractStage.getMaterialCostsActual());
        dto.setSalaryCostsPlan(contractStage.getSalaryCostsPlan());
        dto.setSalaryCostsActual(contractStage.getSalaryCostsActual());
        return dto;
    }
}