package ru.lukin.spring.nic_zadacha.controller;

import org.springframework.web.bind.annotation.*;
import ru.lukin.spring.nic_zadacha.DTO.ContractStageDTO;
import ru.lukin.spring.nic_zadacha.service.ContractStageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/contract-stages")
public class ContractStageController {

    @Autowired
    private ContractStageService contractStageService;

    @GetMapping
    public List<ContractStageDTO> getAllContractStages() {
        return contractStageService.getAllContractStages();
    }

    @PostMapping
    public ContractStageDTO createContractStage(@RequestBody ContractStageDTO contractStageDTO) {
        return contractStageService.createContractStage(contractStageDTO);
    }

    @PutMapping("/{id}")
    public ContractStageDTO updateContractStage(@PathVariable Long id, @RequestBody ContractStageDTO contractStageDTO) {
        contractStageDTO.setId(id);
        return contractStageService.updateContractStage(contractStageDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteContractStage(@PathVariable Long id) {
        contractStageService.deleteContractStage(id);
    }
}