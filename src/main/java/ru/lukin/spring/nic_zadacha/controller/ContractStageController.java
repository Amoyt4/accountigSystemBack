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
import ru.lukin.spring.nic_zadacha.DTO.ContractStageDTO;
import ru.lukin.spring.nic_zadacha.service.ContractStageService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@RestController
@RequestMapping("/contract-stages")
@AllArgsConstructor
public class ContractStageController {

    private final ContractStageService contractStageService;

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