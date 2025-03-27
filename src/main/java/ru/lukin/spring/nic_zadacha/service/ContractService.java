package ru.lukin.spring.nic_zadacha.service;

import lombok.AllArgsConstructor;
import ru.lukin.spring.nic_zadacha.DTO.MyContractDTO;
import ru.lukin.spring.nic_zadacha.model.ContractStage;
import ru.lukin.spring.nic_zadacha.model.MyContract;
import ru.lukin.spring.nic_zadacha.model.SubContract;
import ru.lukin.spring.nic_zadacha.repository.MyContractRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ContractService {

    private final MyContractRepository myContractRepository;

    private final ContractStageService contractStageService;

    private final SubContractService subContractService;

    public List<MyContractDTO> getAllMyContracts() {
        return myContractRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public Optional<MyContractDTO> getMyContractById(Long id) {
        return myContractRepository.findById(id)
                .map(this::convertToDTO);
    }

    public MyContractDTO createMyContract(MyContractDTO myContractDTO) {
        MyContract myContract = convertToEntity(myContractDTO);
        MyContract savedMyContract = myContractRepository.save(myContract);
        return convertToDTO(savedMyContract);
    }

    public MyContractDTO updateMyContract(MyContractDTO myContractDTO) {
        MyContract myContract = convertToEntity(myContractDTO);
        MyContract updatedMyContract = myContractRepository.save(myContract);
        return convertToDTO(updatedMyContract);
    }

    public void deleteMyContract(Long id) {
        myContractRepository.deleteById(id);
    }

    private MyContract convertToEntity(MyContractDTO dto) {
        MyContract myContract = new MyContract();
        myContract.setId(dto.getId());
        myContract.setName(dto.getName());
        myContract.setContractType(dto.getContractType());
        myContract.setPlannedStartDate(dto.getPlannedStartDate());
        myContract.setPlannedEndDate(dto.getPlannedEndDate());
        myContract.setActualStartDate(dto.getActualStartDate());
        myContract.setActualEndDate(dto.getActualEndDate());
        myContract.setAmount(BigDecimal.valueOf(dto.getAmount()));

        List<ContractStage> stages = dto.getStages().stream()
                .map(stageId -> contractStageService.getContractStageById(stageId)
                        .orElseThrow(() -> new RuntimeException("Stage not found")))
                .collect(Collectors.toList());
        myContract.setStages(stages);

        List<SubContract> subContracts = dto.getSubContracts().stream()
                .map(subContractId -> subContractService.getSubContractById(subContractId)
                        .orElseThrow(() -> new RuntimeException("SubContract not found")))
                .collect(Collectors.toList());
        myContract.setSubContracts(subContracts);

        return myContract;
    }

    private MyContractDTO convertToDTO(MyContract myContract) {
        MyContractDTO dto = new MyContractDTO();
        dto.setId(myContract.getId());
        dto.setName(myContract.getName());
        dto.setContractType(myContract.getContractType());
        dto.setPlannedStartDate(myContract.getPlannedStartDate());
        dto.setPlannedEndDate(myContract.getPlannedEndDate());
        dto.setActualStartDate(myContract.getActualStartDate());
        dto.setActualEndDate(myContract.getActualEndDate());
        dto.setAmount(myContract.getAmount());

        dto.setStages(myContract.getStages().stream()
                .map(ContractStage::getId)
                .collect(Collectors.toList()));

        dto.setSubContracts(myContract.getSubContracts().stream()
                .map(SubContract::getId)
                .collect(Collectors.toList()));

        return dto;
    }
}