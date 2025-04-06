package ru.lukin.spring.nic_zadacha.service;

import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.ContractDTO;
import ru.lukin.spring.nic_zadacha.model.ContractStage;
import ru.lukin.spring.nic_zadacha.model.Contract;
import ru.lukin.spring.nic_zadacha.model.SubContract;
import ru.lukin.spring.nic_zadacha.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractService {

    private final ContractRepository contractRepository;

    private final ContractStageService contractStageService;

    private final SubContractService subContractService;

    public List<ContractDTO> getAllMyContracts() {
        return contractRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<ContractDTO> getMyContractById(Long id) {
        return contractRepository.findById(id)
                .map(this::convertToDTO);
    }

    public ContractDTO createMyContract(ContractDTO contractDTO) {
        Contract contract = convertToEntity(contractDTO);
        Contract savedContract = contractRepository.save(contract);
        return convertToDTO(savedContract);
    }

    public ContractDTO updateMyContract(ContractDTO contractDTO) {
        Contract contract = convertToEntity(contractDTO);
        Contract updatedContract = contractRepository.save(contract);
        return convertToDTO(updatedContract);
    }

    public void deleteMyContract(Long id) {
        contractRepository.deleteById(id);
    }

    private Contract convertToEntity(ContractDTO dto) {
        Contract contract = new Contract();
        contract.setId(dto.getId());
        contract.setName(dto.getName());
        contract.setContractType(dto.getContractType());
        contract.setPlannedStartDate(dto.getPlannedStartDate());
        contract.setPlannedEndDate(dto.getPlannedEndDate());
        contract.setActualStartDate(dto.getActualStartDate());
        contract.setActualEndDate(dto.getActualEndDate());
        contract.setAmount(dto.getAmount());

        List<ContractStage> stages = dto.getStages().stream()
                .map(stageId -> contractStageService.getContractStageById(stageId)
                        .orElseThrow(() -> new RuntimeException("Stage not found")))
                .collect(Collectors.toList());
        contract.setStages(stages);

        List<SubContract> subContracts = dto.getSubContracts().stream()
                .map(subContractId -> subContractService.getSubContractById(subContractId)
                        .orElseThrow(() -> new RuntimeException("SubContract not found")))
                .collect(Collectors.toList());
        contract.setSubContracts(subContracts);

        return contract;
    }

    private ContractDTO convertToDTO(Contract contract) {
        ContractDTO dto = new ContractDTO();
        dto.setId(contract.getId());
        dto.setName(contract.getName());
        dto.setContractType(contract.getContractType());
        dto.setPlannedStartDate(contract.getPlannedStartDate());
        dto.setPlannedEndDate(contract.getPlannedEndDate());
        dto.setActualStartDate(contract.getActualStartDate());
        dto.setActualEndDate(contract.getActualEndDate());
        dto.setAmount(contract.getAmount());

        dto.setStages(contract.getStages().stream()
                .map(ContractStage::getId)
                .collect(Collectors.toList()));

        dto.setSubContracts(contract.getSubContracts().stream()
                .map(SubContract::getId)
                .collect(Collectors.toList()));

        return dto;
    }
}