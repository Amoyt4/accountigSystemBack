package ru.lukin.spring.nic_zadacha.service;

import ru.lukin.spring.nic_zadacha.DTO.ContractStageDTO;
import ru.lukin.spring.nic_zadacha.model.ContractStage;
import ru.lukin.spring.nic_zadacha.repository.ContractStageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContractStageService {

    @Autowired
    private ContractStageRepository contractStageRepository;

    // Получить все стадии (DTO)
    public List<ContractStageDTO> getAllContractStages() {
        return contractStageRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Получить стадию по ID (сущность)
    public Optional<ContractStage> getContractStageById(Long id) {
        return contractStageRepository.findById(id);
    }

    // Получить стадию по ID (DTO)
    public Optional<ContractStageDTO> getContractStageDTOById(Long id) {
        return contractStageRepository.findById(id)
                .map(this::convertToDTO);
    }

    // Создать новую стадию
    public ContractStageDTO createContractStage(ContractStageDTO contractStageDTO) {
        ContractStage contractStage = convertToEntity(contractStageDTO);
        ContractStage savedContractStage = contractStageRepository.save(contractStage);
        return convertToDTO(savedContractStage);
    }

    // Обновить существующую стадию
    public ContractStageDTO updateContractStage(ContractStageDTO contractStageDTO) {
        ContractStage contractStage = convertToEntity(contractStageDTO);
        ContractStage updatedContractStage = contractStageRepository.save(contractStage);
        return convertToDTO(updatedContractStage);
    }

    // Удалить стадию по ID
    public void deleteContractStage(Long id) {
        contractStageRepository.deleteById(id);
    }

    // Преобразование DTO в сущность
    public ContractStage convertToEntity(ContractStageDTO dto) {
        ContractStage contractStage = new ContractStage();
        contractStage.setId(dto.getId());
        contractStage.setName(dto.getName());
        contractStage.setPlannedStartDate(dto.getPlannedStartDate());
        contractStage.setPlannedEndDate(dto.getPlannedEndDate());
        contractStage.setActualStartDate(dto.getActualStartDate());
        contractStage.setActualEndDate(dto.getActualEndDate());
        contractStage.setAmount(dto.getAmount());
        contractStage.setMaterialCostsPlan(dto.getMaterialCostsPlan());
        contractStage.setMaterialCostsActual(dto.getMaterialCostsActual());
        contractStage.setSalaryCostsPlan(dto.getSalaryCostsPlan());
        contractStage.setSalaryCostsActual(dto.getSalaryCostsActual());

        return contractStage;
    }

    // Преобразование сущности в DTO
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